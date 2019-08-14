package com.crazyBird.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.user.UserLoginDao;
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.StudentPO;
import com.crazyBird.dao.user.dataobject.UserDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.adminDO;
import com.crazyBird.dao.user.dataobject.adminSignDO;
import com.crazyBird.dao.user.dataobject.lgDO;
import com.crazyBird.dao.user.dataobject.logDO;
import com.crazyBird.dao.user.dataobject.studentDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.user.UserAccountService;

/**
 * @author zzc
 *
 */
@Component("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserLoginDao loginDao;

	@Override
	public ResponseDO<UserDO> getUserLogin(UserLoginDO loginDO) {
		// TODO Auto-generated method stub
		ResponseDO<UserDO> response = new ResponseDO<>();
		response.setDataResult(loginDao.getLoginAccount(loginDO));
		if(response.getDataResult()==null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("账号或密码错误");
		}
		return response;
	}

	@Override
	public ResponseDO<lgDO> lg(lgDO lgDO) {
		// TODO Auto-generated method stub
		ResponseDO<lgDO> response=new  ResponseDO<>();
		Integer aa=loginDao.select(lgDO);
		if(!aa.equals("")&& aa!=null) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("登陆成功");
		}else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("登陆失败");
		}
		return response;
	}
	@Override
	public ResponseDO<adminDO> login(logDO logDO){
		ResponseDO<adminDO> response=new ResponseDO<>();
		response.setDataResult(loginDao.login(logDO));
		if(response.getDataResult()==null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("登陆失败");
		}
		else {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("login success");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<studentDO>> queryStudent(StudentPO student) {
		// TODO Auto-generated method stub
		ResponsePageQueryDO<List<studentDO>> response=new ResponsePageQueryDO<>();
		response.setPageIndex(student.getPageIndex());
		response.setPageSize(student.getPageSize());
		response.setTotal(loginDao.count(student.getSname()));
		System.out.println(loginDao.queryStudent(student));
		if(response.getTotal()>0&&response.getTotalPage()>response.getPageIndex()) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("query select success");
			response.setDataResult(loginDao.queryStudent(student));
		}
		else {
			System.out.println(response.getDataResult());
			response.setCode(ResponseCode.ERROR);
			response.setMessage("查询失败");
		}
		return response;

	}
	@Override
	public ResponseDO<Integer> signin(adminSignDO adminSignDO) {
		// TODO Auto-generated method stub
		ResponseDO<Integer> response=new  ResponseDO<>();
		System.out.println(adminSignDO.getAname()+"1111");
		int aa=0;
		try {
			aa = loginDao.signin(adminSignDO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(aa==1) {
			
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("注册成功"+adminSignDO.getId());
		}
		else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("注册失败");
		}
		return response;
	}
	@Override
	public ResponseDO<Integer> check(adminSignDO admin) {
		// TODO Auto-generated method stub
		ResponseDO<Integer> response=new  ResponseDO<>();
		Integer aa=loginDao.check(admin);
		if(!aa.equals("")&& aa!=null) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("安全密码正确！");
		}else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("安全密码错误！");
		}
		return response;
	}
	@Override
	public ResponseDO<Integer> resetPass(adminSignDO admin) {
		// TODO Auto-generated method stub
		ResponseDO<Integer> response=new  ResponseDO<>();
		Integer aa=loginDao.resetPass(admin);
		if(aa!=0) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("修改成功！");
		}else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("修改失败！");
		}
		return response;
	}

	@Override
	public ResponseDO<Integer> findName(String aname) {
		// TODO Auto-generated method stub
		ResponseDO<Integer> response=new ResponseDO<>();
		Integer aa=loginDao.findName(aname);
		System.out.println("aa"+aa);
		if(aa!=null&&aa==1) {
			response.setMessage("用户名已存在!");
			response.setCode(ResponseCode.ERROR);
		}else {
			response.setCode(ResponseCode.SUCCESS);
		}
		return response;
	}

	@Override
	public ResponseDO<Integer> updateStatus(String code) {
		ResponseDO<Integer> response=new ResponseDO<>();
		Integer aa=loginDao.updateStatus(code);
		System.out.println(aa);
		System.out.println(code);
		if(aa!=null&&aa==1) {
			response.setMessage("激活成功，请</<a href='http://127.0.0.1:8080/user/active'>登陆</a>");
			response.setCode(ResponseCode.SUCCESS);
		}else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("请不要胡乱输入激活码。或者已激活。");
		}
		return response;
	}

	@Override
	public ResponseDO<LoginDO> getUserlg(LoginDO loginDO) {
		ResponseDO<LoginDO> response=new ResponseDO<>();
		Integer aa=loginDao.select(lgDO);//变量名与int有什么区别返回的多项值用result?
		if(!aa.equals("")&& aa!=null) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("登陆成功");
		}else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("登陆失败");
		}
		return response;
		return null;
	}


}
