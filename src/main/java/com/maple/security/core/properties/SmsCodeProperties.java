package com.maple.security.core.properties;

/**
 * 
 * 图片验证码
 * 
 * @author hzc
 *
 */
public class SmsCodeProperties {

	private int length = 6;
	private int expireIn = 60;

	// 哪些请求需要验证验证码 /user,/user/*
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
