package com.maple.security.core.properties;

/**
 * 
 * 浏览器配置文件
 * 
 * @author hzc
 *
 */
public class BrowserProperties {

	private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
	
	private LoginType loginType = LoginType.REDIRECT;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

}