package com.crazyBird.dao.user;


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


public interface UserLoginDao {
	UserDO getLoginAccount(UserLoginDO userLoginDO);

	Integer select(lgDO lgDO);
	adminDO login(logDO logDO);
	List<studentDO> queryStudent(StudentPO sname);
	Integer signin(adminSignDO adminSignDO);
	Integer count(String string);
	Integer check(adminSignDO admin);
	Integer resetPass(adminSignDO admin);
	Integer findName(String aname);
	Integer updateStatus(String code);
	
	String  getUserlg(LoginDO lg);
}
