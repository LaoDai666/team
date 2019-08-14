package com.crazyBird.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.RestModelView;
import com.crazyBird.dao.colla.dataobject.LogAnalysisDO;
import com.crazyBird.exception.ParamException;
import com.crazyBird.model.reqinfo.ReqParam;
import com.crazyBird.service.colla.LogAnalysisService;
import com.crazyBird.utils.JsonUtils;
import com.crazyBird.utils.PropertiesUtils;
import com.crazyBird.utils.RestLogUtils;
import com.crazyBird.validate.ValidateProcess;
import com.crazyBird.validate.common.ValidateResult;
import com.google.common.base.Splitter;

/**
 * @Type ParamValidateInterceptor
 * @Desc 参数验证拦截器
 */
public class ParamValidateInterceptor implements MethodInterceptor {
	
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogAnalysisService logAnalysisService;
    
    @Value("${log.analysis.urls}")
	private String LOG_ANALYSIS_URLS;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        
        Class<?> clazz = method.getDeclaringClass();
        if (AnnotationUtils.findAnnotation(clazz, Controller.class) != null
                && AnnotationUtils.findAnnotation(method, ResponseBody.class) != null) {
            // 接口参数验证
            Object[] objectArray = invocation.getArguments();
            Object param = null;
            if (objectArray.length == 1 || objectArray.length == 2) {
                param = objectArray[0];
                // 支付回调使用objectArray[1]
                if (objectArray.length == 2) {
                    param = objectArray[1];
                }
                ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
                reqParam.setFormParam(JsonUtils.toJSON(param));
                // 获得Controller对象的传入参数
                Class<?> paramType = param.getClass();
                // 判断是否有验证注解信息
                if (ValidateProcess.hasAnnotation(paramType)) {
                    ValidateResult verifyResult = ValidateProcess.validateObject(param);
                    if (!verifyResult.isSuccess()) {
                        throw new ParamException(verifyResult.getErrorMsg());
                    }
                }
            }

            // 执行操作
            Object result = invocation.proceed();

            // 返回结果
            Object resultObject = null;
            if (result instanceof RestModelView) {
                resultObject = ((RestModelView) result).getModelObject();
            } else {
                resultObject = result;
            }

            // 数据格式化处理
            PropertiesUtils.setNullProperties(resultObject);

            // 日志输出:仅配置需要输出内容列表的才会输出
            LogAnalysisDO logAnalysis = RestLogUtils.writeRestLogByInfo(request, resultObject);
            if(logAnalysis != null) {
            	try {
            		if(StringUtils.isNotBlank(LOG_ANALYSIS_URLS)) {
            			List<String> urls = Splitter.on(",").trimResults().splitToList(LOG_ANALYSIS_URLS);
            			for(String url : urls) {
            				logAnalysis.setUrl(logAnalysis.getUrl() + "#" + method.getName());
            				if(StringUtils.isNotBlank(url) && url.equals(logAnalysis.getUrl())) {
            					logAnalysisService.addLogAnalysis(logAnalysis);
            					break ;
            				}
            			}
            		}
				} catch (Exception e) {
					// 不处理，避免影响系统
				}
            }
            return resultObject;
        }
        return invocation.proceed();
    }
}
