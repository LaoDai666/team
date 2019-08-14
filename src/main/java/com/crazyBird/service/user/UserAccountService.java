package com.crazyBird.service.user;


import java.util.List;

import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.StudentPO;
import com.crazyBird.dao.user.dataobject.UserDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.adminDO;
import com.crazyBird.dao.user.dataobject.adminSignDO;
import com.crazyBird.dao.user.dataobject.lgDO;
import com.crazyBird.dao.user.dataobject.logDO;
import com.crazyBird.dao.user.dataobject.studentDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;


public interface UserAccountService {
	
	ResponseDO<UserDO> getUserLogin(UserLoginDO loginDO);
	
	ResponseDO<lgDO> lg(lgDO lgDO);
	ResponseDO<adminDO> login(logDO logDO);
	ResponsePageQueryDO<List<studentDO>> queryStudent(StudentPO student);
	ResponseDO<Integer> signin(adminSignDO adminSignDO);

	ResponseDO<Integer> check(adminSignDO admin);
	ResponseDO<Integer> findName(String aname);
	ResponseDO<Integer> resetPass(adminSignDO admin);
	ResponseDO<Integer> updateStatus(String code);
	//---------------------------------------------------
	ResponseDO<LoginDO> getUserlg(LoginDO loginDO);
}
