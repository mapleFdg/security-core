/**
 * 
 */
package com.maple.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送器
 * 
 * @author hzc
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.maple.security.core.validate.code.SmsCodeSender#send(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void send(String mobile, String code) {
		logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		
		System.out.println("向手机" + mobile + "发送短信验证码" + code);

	}

}
