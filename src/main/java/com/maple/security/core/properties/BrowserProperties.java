package com.maple.security.core.properties;

/**
 * 
 * 浏览器配置文件
 * 
 * @author hzc
 *
 */
public class BrowserProperties {
	
	private String signUpUrl = "/maple-signUp.html";

	private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
	
	private LoginType loginType = LoginType.REDIRECT;
	
	/**
	 * 记住我的超时时间
	 */
	private int rememberMeSeconds = 3600;

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

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

}
