package com.maple.security.core.validate.code.sms;

/**
 * 
 * 手机短信发送方式
 * 
 * @author hzc
 *
 */
public interface SmsCodeSender {
	
	public void send(String mobile, String code);

}
