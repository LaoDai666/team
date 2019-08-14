package com.crazyBird.controller.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.RestModelView;
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
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.StudentPO;
import com.crazyBird.dao.user.dataobject.UserDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.adminDO;
import com.crazyBird.dao.user.dataobject.adminSignDO;
import com.crazyBird.dao.user.dataobject.lgDO;
import com.crazyBird.dao.user.dataobject.logDO;
import com.crazyBird.dao.user.dataobject.studentDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.user.UserAccountService;
import com.crazyBird.utils.MailUtils;
import com.crazyBird.utils.UuidUtil;
@Component
public class UserAccountProcess {
	@Autowired
	private UserAccountService userAccountService;
//	public UserModel login(UserLoginParam param) {
//		UserModel model = new UserModel();
//		if(StringUtils.isBlank(param.getLoginAccount())||StringUtils.isBlank(param.getPassword())) {
//			model.setCode(HttpCodeEnum.ERROR.getCode());
//			model.setCode("账号或密码不能为空");
//		}
//		UserLoginDO loginDO = new UserLoginDO();
//		loginDO.setLoginAccount(param.getLoginAccount());
//		loginDO.setPassword(param.getPassword());
//		ResponseDO<UserDO> response = userAccountService.getUserLogin(loginDO);
//		if(!response.isSuccess()) {
//			model.setCode(HttpCodeEnum.ERROR.getCode());
//			model.setMessage("账号密码错误");
//		}
//		else {
//			model.setUserId(response.getDataResult().getId());
//			model.setUserName(response.getDataResult().getUserName());
//			model.setLoginAccount(response.getDataResult().getLoginAccount());
//		}
//		 return model;
//	}
//	
	public EmptyModel login(LoginParam param) {
		EmptyModel model=new EmptyModel();
		if(StringUtils.isBlank(param.getPassWord())||StringUtils.isBlank(param.getUserName()){
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("账号或密码不能为空");
			return model;
		}
		LoginDO loginDO=new LoginDO(param.getUserName(),
				param.getPassWord(),param.getPower());
		ResponseDO<LoginDO> response=userAccountService.getUserlg(loginDO);
		return model;
	}
	public kongModel login1(lgParam param) {
		// TODO Auto-generated method stub
		kongModel model=new kongModel();
		lgDO lgDO=new lgDO();
		lgDO.setAc(param.getAc());
		lgDO.setPs(param.getPs());
		ResponseDO<lgDO> response=userAccountService.lg(lgDO);//service.user.UserAccountService
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	public kongModel logint1(logParam param) {
		// TODO Auto-generated method stub
		kongModel model=new kongModel();
		logDO logDO=new logDO();
		logDO.setAname(param.getAname());
		logDO.setApass(param.getApass());
		ResponseDO<adminDO> response=userAccountService.login(logDO);//service.user.UserAccountService
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		else {
			model.setAemail(response.getDataResult().getAemail());
			model.setAname(response.getDataResult().getAname());
			model.setApass(response.getDataResult().getApass());
		}
		model.setMessage(response.getMessage());
		
		return model;
	}
	public studentRecord queryStudent(studentParam param) {
		// TODO Auto-generated method stub
		System.out.println(param.getSname());
		StudentPO student=new StudentPO();
		student.setPageIndex(param.getPageNo()-1);
		student.setPageSize(param.getPageSize());
		student.setSname(param.getSname());
		ResponsePageQueryDO<List<studentDO>> response=userAccountService.queryStudent(student);
		studentRecord model=new studentRecord();
		if(response.isSuccess()) {
			model.setVoteList(response.getDataResult());
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	public kongModel signin(signinParam param) {
		// TODO Auto-generated method stub
		kongModel model=new kongModel();
		adminSignDO admin=new adminSignDO();
		admin.setAemail(param.getAemail());
		if(param.getAname()==null||param.getAname().equalsIgnoreCase("")||param.getApass()==null
				||param.getApass().equalsIgnoreCase("")||param.getAsafepass()==null||
				param.getAsafepass().equalsIgnoreCase("")) {
			model.setMessage("密码或安全码或用户名不可为空.");
			model.setCode(ResponseCode.ERROR);
			return model;
		}
		admin.setAname(param.getAname());
		ResponseDO<Integer> response=userAccountService.findName(admin.getAname());
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		admin.setStatus('N');
		admin.setCode(UuidUtil.getUuid());
		admin.setApass(param.getApass());
		admin.setAsafepass(param.getAsafepass());
		String context="请点击<a href='http://192.168.0.42:8080/user/active?code="+admin.getCode()+"'>激活</a>";
		MailUtils.sendMail(param.getAemail(), context, "激活邮件");
		System.out.println("Provess1111"+param);
		response=userAccountService.signin(admin);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		
		return model;
	}
	public kongModel update(updateParam param) {
		// TODO Auto-generated method stub
		kongModel model=new kongModel();
		adminSignDO admin=new adminSignDO();
		admin.setAname(param.getAname());
		admin.setAsafepass(param.getAsafepass());
		if(param.getStatus().equalsIgnoreCase("check")) {
			System.out.println("Provess1111"+param);
			ResponseDO<Integer> response=userAccountService.check(admin);
			if(!response.isSuccess()) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
			}
			model.setMessage(response.getMessage());
		}
		else if(param.getStatus().equalsIgnoreCase("update")) {
			
			admin.setId(param.getId());//
			admin.setApass(param.getApass());
			System.out.println("Provess---update"+param);
			ResponseDO<Integer> response=userAccountService.resetPass(admin);
			if(!response.isSuccess()) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
			}
			model.setMessage(response.getMessage());
		}
		
		return model;
	}


	public EmptyModel active(String code) {
		// TODO Auto-generated method stub
		EmptyModel model=new EmptyModel();
		ResponseDO<Integer> response=userAccountService.updateStatus(code);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		
		model.setMessage(response.getMessage());
		return model;
	}
}
