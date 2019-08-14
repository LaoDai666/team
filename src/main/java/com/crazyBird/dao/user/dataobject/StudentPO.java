package com.crazyBird.dao.user.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class StudentPO extends PageQueryDO {
	private String sname;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
