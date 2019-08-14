package com.crazyBird.dao.user.dataobject;

public class LoginDO {
	private String userName;
	private String passWord;
	private String power;
	
	public LoginDO(String userName, String passWord, String power) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.power = power;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	
}
