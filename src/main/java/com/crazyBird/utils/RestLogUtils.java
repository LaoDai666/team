package com.crazyBird.utils;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.dao.colla.dataobject.LogAnalysisDO;
import com.crazyBird.model.constant.UrlRightConstant;
import com.crazyBird.model.reqinfo.ReqInfo;
import com.crazyBird.model.reqinfo.ReqParam;
import com.google.common.base.Splitter;

public class RestLogUtils {
	
    private static final Logger log = LoggerFactory.getLogger("rest");

    public static void writeExceptionResolverByInfo(HttpServletRequest request, Exception ex, Logger logger) {
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        logger.info("reqParam={},errmsg={}", JsonUtils.toJSON(reqParam), ex.getMessage(), ex);
    }

    public static void writeExceptionResolverByError(HttpServletRequest request, Exception ex, Logger logger) {
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        logger.error("reqParam={},errmsg={}", JsonUtils.toJSON(reqParam), ex.getMessage(), ex);
    }

    public static LogAnalysisDO writeRestLogByInfo(HttpServletRequest request, Object resultObject) {
    	
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        Date reqTime = (Date) request.getAttribute("req_time") != null ? (Date) request.getAttribute("req_time"): new Date();
        
        boolean logResultObject = false; // 日志是否需要打印返回结果对象
        if(reqParam != null && StringUtils.isNotBlank(reqParam.getUrl())) {
        	// 打印接口返回对象信息
        	String url = reqParam.getUrl();
            for (String logUrl : UrlRightConstant.logUrlList) {
                if (url.indexOf(logUrl) != -1) {
                	logResultObject = true;
                	break ;
                }
            }
        }
        
        if(logResultObject) {
        	return RestLogUtils.writeRestLogByInfo(reqParam, resultObject, reqTime, false);
        } else {
        	if (resultObject instanceof AbstractFlagModel) {
        		SimpleFlagModel simpleFlagModel = new SimpleFlagModel();
        		simpleFlagModel.setCode(((AbstractFlagModel) resultObject).getCode());
        		simpleFlagModel.setMessage(((AbstractFlagModel) resultObject).getMessage());
        		if (AbstractFlagModel.SUCCESS.equals(simpleFlagModel.getCode())) {
        			return RestLogUtils.writeRestLogByInfo(reqParam, simpleFlagModel, reqTime, false); // 成功，日志不打印具体对象结果
        		} else {
        			return RestLogUtils.writeRestLogByInfo(reqParam, resultObject, reqTime, false);
        		}
        	} else {
        		return RestLogUtils.writeRestLogByInfo(reqParam, resultObject, reqTime, false);
        	}
        }
    }

    private static LogAnalysisDO writeRestLogByInfo(ReqParam reqParam, Object resultObject, Date reqTime,
            Boolean jsonFormatter) {
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setReqParam(reqParam);
        reqInfo.setRespBody(resultObject);
        reqInfo.setRespTime(DateUtil.getMillisecond(reqTime, new Date()));
        if (jsonFormatter) {
            log.info(JsonUtils.toJSONFormatter(reqInfo));
        } else {
            log.info(JsonUtils.toJSON(reqInfo));
        }
        
        return convertLogAnalysis(reqParam, resultObject, reqInfo.getRespTime());
    }

	private static LogAnalysisDO convertLogAnalysis(ReqParam reqParam, Object resultObject, Long respTime) {
		if(reqParam == null) {
			return null;
		}
		LogAnalysisDO logAnalysis = new LogAnalysisDO();
        logAnalysis.setIp(reqParam.getIp());
        String _url = "";
        List<String> urlPathList = Splitter.on("/").trimResults().splitToList(reqParam.getUrl());
        for(String path : urlPathList) {
        	if(StringUtils.isBlank(path)) {
        		continue ;
        	}
        	if(StringUtils.isNumeric(path)) {
        		path = "${id}";
        	}
        	_url = _url + "/" + path;
        }
        logAnalysis.setUrl(_url);
        if(StringUtils.isNotBlank(reqParam.getUserId()) && StringUtils.isNumeric(reqParam.getUserId())) {
        	logAnalysis.setUserId(Long.valueOf(reqParam.getUserId()));
        }
        if(resultObject instanceof AbstractFlagModel) {
        	logAnalysis.setRespCode(((AbstractFlagModel) resultObject).getCode());
        }
        logAnalysis.setRespTime(respTime);
		return logAnalysis;
	}
}
