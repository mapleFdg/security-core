package com.maple.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 
 * 第三方登录QQ配置文件
 * 
 * @author hzc
 *
 */
public class QQProperties extends SocialProperties {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 */
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

}
