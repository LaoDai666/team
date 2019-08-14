package com.crazyBird.dao.user.dataobject;

import java.util.Date;

/**
 * @Type UserLoginDO
 * @Desc 
 * @author zzc
 * @date 2016年8月20日
 * @Version V1.0
 */
public class UserLoginDO {
	
    private String loginAccount;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;


    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }
}