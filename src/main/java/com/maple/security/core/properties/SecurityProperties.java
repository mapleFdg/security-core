package com.maple.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * 配置文件
 * 
 * @author hzc
 *
 */
@ConfigurationProperties(prefix = "maple.security")
public class SecurityProperties {

	/**
	 * 浏览器配置
	 */
	private BrowserProperties browser = new BrowserProperties();
	
	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	/**
	 * 第三方登录配置
	 */
	private SocialProperties social = new SocialProperties();
	
	/**
	 * OAuth2相关配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();
	
	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}

	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	public void setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
	}

}
