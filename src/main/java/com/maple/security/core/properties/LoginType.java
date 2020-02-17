package com.maple.security.core.properties;

/**
 * 
 * 登录返回类型
 * 
 * @author hzc
 *
 */
public enum LoginType {
	
	/**
	 * 转发到登录页
	 */
	REDIRECT,
	/**
	 * 以json的格式返回
	 */
	JSON;
}
