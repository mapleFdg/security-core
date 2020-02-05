package com.maple.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 * 校验码处理器，封装不同校验码的处理逻辑
 * 
 * 
 * @author hzc
 *
 */
public interface ValidateCodeProcessor {
	
	/**
	 * 创建校验码
	 * 
	 * ServletWebRequest 请求封装类，即可放request，也可放response，也可同时放
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void create(ServletWebRequest request) throws Exception;
	
	/**
	 * 校验验证码
	 * 
	 * @param request
	 */
	public void validate(ServletWebRequest request);
	
}
