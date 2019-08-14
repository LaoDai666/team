package com.crazyBird.model.enums;

/**
 * @Type MessageTypeEnum.java
 * @Desc 
 * @author luogm
 * @date 2016年11月22日 下午4:04:17
 * @Version V1.0
 */
public enum MessageTypeEnum {

	PERSONAL(0, "个人消息"),
	GROUP(1, "群消息"),
	SYSTEM(2, "系统消息");
	
	private MessageTypeEnum(Integer code, String desc) {
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
