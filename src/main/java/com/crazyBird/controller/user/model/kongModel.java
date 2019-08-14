package com.crazyBird.controller.user.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class kongModel extends AbstractFlagModel{
	private String aname;
	private String apass;
	private String aemail;
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApass() {
		return apass;
	}
	public void setApass(String apass) {
		this.apass = apass;
	}
	public String getAemail() {
		return aemail;
	}
	public void setAemail(String aemail) {
		this.aemail = aemail;
	}
	
}
