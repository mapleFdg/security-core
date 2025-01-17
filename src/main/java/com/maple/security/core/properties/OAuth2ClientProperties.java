package com.maple.security.core.properties;

/**
 * 
 * 认证服务器注册的第三方应用配置项
 * 
 * @author hzc
 *
 */
public class OAuth2ClientProperties {

	private String clientId;

	private String clientSecret;

	private Integer accessTokenValiditySeconds = 3600;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

}
