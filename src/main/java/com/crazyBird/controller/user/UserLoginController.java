package com.crazyBird.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.param.test;
import com.crazyBird.controller.user.param.test2;
import com.mysql.fabric.xmlrpc.base.Array;





@Controller
@RequestMapping("/test")
public class UserLoginController {
		@RequestMapping(value = "/login", method = RequestMethod.POST)
	    @ResponseBody
	    public SimpleFlagModel register(@RequestBody test test) {
			SimpleFlagModel model= new SimpleFlagModel();
			System.out.println(test.getTest2().get(0).getFour());
	    	return model;
	    }
}
