package com.maple.security.core.properties;

/**
 * 
 * 浏览器配置文件
 * 
 * @author hzc
 *
 */
public class BrowserProperties {
	
	/**
	 * session配置信息类
	 */
	private SessionProperties session = new SessionProperties();
	
	/**
	 * 注册页面
	 */
	private String signUpUrl = "/maple-signUp.html";

	/**
	 * 登录页面
	 */
	private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
	
	/**
	 * 登录的类型 json 还是转发
	 */
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

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

}
