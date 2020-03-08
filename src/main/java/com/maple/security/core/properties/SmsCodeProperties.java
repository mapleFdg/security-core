package com.maple.security.core.properties;

/**
 * 
 * 图片验证码
 * 
 * @author hzc
 *
 */
public class SmsCodeProperties {

	/**
	 * 验证码长度
	 */
	private int length = 6;
	
	/**
	 * 验证码超时时间，秒
	 */
	private int expireIn = 60;

	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url = "";

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
