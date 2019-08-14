package com.crazyBird.controller.user.param;

import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;

public class test {
	private String one;
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	private String two;
	private List<test2> test2;
	public List<test2> getTest2() {
		return test2;
	}
	public void setTest2(List<test2> test2) {
		this.test2 = test2;
	}
	
}
