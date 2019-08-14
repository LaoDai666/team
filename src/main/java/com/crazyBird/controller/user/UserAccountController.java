package com.crazyBird.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.EmptyModel;
import com.crazyBird.controller.user.model.UserModel;
import com.crazyBird.controller.user.model.kongModel;
import com.crazyBird.controller.user.model.studentRecord;
import com.crazyBird.controller.user.param.LoginParam;
import com.crazyBird.controller.user.param.UserLoginParam;
import com.crazyBird.controller.user.param.lgParam;
import com.crazyBird.controller.user.param.logParam;
import com.crazyBird.controller.user.param.signinParam;
import com.crazyBird.controller.user.param.studentParam;
import com.crazyBird.controller.user.param.updateParam;
import com.mysql.fabric.xmlrpc.base.Array;




/**
 *
 * @author zhengzc
 *
 */
@Controller
@RequestMapping("/user")
public class UserAccountController {
	@Autowired
	private UserAccountProcess userAccountProcess;
	
	/**
	 * 登陆
	* @param test
	* @return
//	*/
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	@ResponseBody
//	public UserModel login(@RequestBody UserLoginParam param) {
//	    	return userAccountProcess.login(param);
//	}
//	
	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	@ResponseBody
	public kongModel login1(@RequestBody lgParam param) {
	    	return userAccountProcess.login1(param);
	}
	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/logint1", method = RequestMethod.POST)
	@ResponseBody
	public kongModel logint1(@RequestBody logParam param) {
		System.out.println("111");
	    	return userAccountProcess.logint1(param);
	}
	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/queryStudent", method = RequestMethod.POST)
	@ResponseBody
	public studentRecord queryStudent(@RequestBody studentParam param) {
	    	return userAccountProcess.queryStudent(param);
	}
	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/queryStudent1", method = RequestMethod.GET)
	@ResponseBody
	public studentParam list() {
		studentParam param=new studentParam();
		param.setSname("");
		param.setPageNo(1);
		param.setPageSize(5);
	    	return param;
	}

	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@ResponseBody
	public kongModel signin(@RequestBody signinParam param) {//去掉了RequestBOdy
		System.out.println("Provess1111"+param);
	    	return userAccountProcess.signin(param);
	}

	/* 激活账号 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	@ResponseBody
	public EmptyModel active(String code) {//去掉了RequestBOdy
	    	return userAccountProcess.active(code);
	}


	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public kongModel update(@RequestBody updateParam param) {
	    	return userAccountProcess.update(param);
	}
	/**
	 * 登陆
	* @param test
	* @return
	*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public EmptyModel login(@RequestBody LoginParam param) {
	    	return userAccountProcess.login(param);
	}
	
}
