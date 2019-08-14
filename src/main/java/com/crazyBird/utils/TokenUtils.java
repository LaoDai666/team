package com.crazyBird.utils;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @author luogm
 *
 */
public class TokenUtils {
	
	private static final String TOKEN_AES_KEY = "M03trp$J7yo+Eu8S"; // 秘钥
	
	/**
	 * 创建用户token
	 * @param userId
	 * @return
	 */
	public static String creatAesStr(Long id) {
		if(id == null) {
			return "";
		}
		UUID uuid = UUID.randomUUID();
		String data = uuid.toString() + "|" + id;
		String key = new String(Base64.encodeBase64(TOKEN_AES_KEY.getBytes()));
		return AesUtils.encrypt(data, key);
	}
	
	/**
	 * 根据token获取用户ID
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public static Long getIdFromAesStr(String str) throws Exception {
		if(StringUtils.isBlank(str)) {
			return null;
		}
		String key = new String(Base64.encodeBase64(TOKEN_AES_KEY.getBytes()));
		String originStr;
		originStr = AesUtils.decrypt(str, key);
		String id = originStr.split("\\|")[1];
		if(StringUtils.isBlank(id)) {
			return null;
		}
		return Long.parseLong(id);
	}
	
	/**
	 * 创建公司邀请码
	 * @param inviteId
	 * @param checkCode
	 * @return
	 */
	public static String createInvitationCode(Long inviteId,String checkCode) {
		if(inviteId == null) {
			return "";
		}
		String data = inviteId + "|" + checkCode;
		String key = new String(Base64.encodeBase64(TOKEN_AES_KEY.getBytes()));
		return AesUtils.encrypt(data, key);
	}
	
	/**
	 * 解码
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	public static String[] getCodeInfo(String code) throws Exception{
		if (code == null || StringUtils.isBlank(code)) {
			return null;
		}
		String key = new String(Base64.encodeBase64(TOKEN_AES_KEY.getBytes()));
		String originDate = AesUtils.decrypt(code, key);
		if (originDate == null || StringUtils.isBlank(originDate)) {
			return null;
		}
		String[] result = originDate.split("\\|");
		return result;
	}
}
