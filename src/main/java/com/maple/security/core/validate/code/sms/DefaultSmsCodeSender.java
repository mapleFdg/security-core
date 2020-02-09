/**
 * 
 */
package com.maple.security.core.validate.code.sms;

/**
 * @author hzc
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.maple.security.core.validate.code.SmsCodeSender#send(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机" + mobile + "发送短信验证码" + code);

	}

}
