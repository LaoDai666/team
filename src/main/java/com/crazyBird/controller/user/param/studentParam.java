package com.crazyBird.controller.user.param;

import com.crazyBird.controller.base.AbstractPageParam;

public class studentParam extends AbstractPageParam {
	private String sname;
	public studentParam(){
		this.setPageNo(1);
		this.setPageSize(5);
	}
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
}
