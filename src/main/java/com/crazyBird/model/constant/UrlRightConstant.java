package com.crazyBird.model.constant;

import java.util.ArrayList;
import java.util.List;

public class UrlRightConstant {
    // 需要登录
    public static List<String> loginVerifyUrlList = new ArrayList<String>();
    // 非必须登录
    public static List<String> loginNotNecessaryUrlList = new ArrayList<String>();
    // 忽略所有验证
    public static List<String> notVerifyUrlList = new ArrayList<String>();
    // 返回结果集数据需要加密的url,暂时没用
    public static List<String> dataSignUrlList = new ArrayList<String>();
    // 线上需要打印返回结果体的日志的url
    public static List<String> logUrlList = new ArrayList<String>();
    
    static {
    	
    /*	notVerifyUrlList.add("/user/account/platform/login");
    	notVerifyUrlList.add("/user/account/weixinapp");
    	notVerifyUrlList.add("/user/account/weixinapp/login");
    	notVerifyUrlList.add("/user/account/weixinapp/bind");
        notVerifyUrlList.add("/user/account/login");
        notVerifyUrlList.add("/user/account/register");
        notVerifyUrlList.add("/user/account/checkCode");
        notVerifyUrlList.add("/user/account/mobileAvailable");
        notVerifyUrlList.add("/user/account/password");
        notVerifyUrlList.add("/colla/region");
        
        loginNotNecessaryUrlList.add("/user/account/public");
        loginNotNecessaryUrlList.add("/circle/factories");
        
        
        loginVerifyUrlList.add("/user/account");
        loginVerifyUrlList.add("/message");
        loginVerifyUrlList.add("/upload");
        loginVerifyUrlList.add("/circle");
        
        dataSignUrlList.add("/");
        
        logUrlList.add("/user/account");
        logUrlList.add("/message");
        logUrlList.add("/upload");
        logUrlList.add("/circle");*/
    }

}