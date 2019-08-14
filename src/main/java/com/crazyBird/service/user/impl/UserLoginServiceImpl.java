package com.crazyBird.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.model.enums.UserLoginStatusEnum;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.user.UserLoginService;
import com.crazyBird.utils.DeviceUtils;
import com.crazyBird.utils.TokenUtils;

/**
 * @author luogm
 *
 */
@Component("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	@Override
	public ResponseDO<UserLoginDO> getUserLogin(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
