package com.crazyBird.model.enums;

/**
 * @author luogm
 *
 */
public enum PlatTypeEnum {
	
	WEIXIN(1, "微信"),
	QQ(2, "QQ"),
	EMAIL(3, "email");
	
	private PlatTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;

	private String desc;

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
