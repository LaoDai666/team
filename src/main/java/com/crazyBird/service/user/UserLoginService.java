package com.crazyBird.service.user;


import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.service.base.ResponseDO;


public interface UserLoginService {
	
	ResponseDO<UserLoginDO> getUserLogin(Long userId);
//	ResponseDO<UserLoginDO> getUserLogin(Long userId, Integer deviceType);
//	ResponseDO<UserLoginDTO> doPasswordLogin(PasswordLoginPO loginPO);
	
//	ResponseDO<UserLoginDTO> doPlatLogin(PlatLoginPO platLoginPO);
	
//	void doLoginOut(UserLoginPO userLoginPO);

	//ResponseDO<UserLoginDTO> login(UserAccountDO user, LoginPO loginPO);
	
	//String updateAccessToken(Long userId, String account, String agent);
}
