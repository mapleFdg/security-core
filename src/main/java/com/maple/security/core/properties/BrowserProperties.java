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
	 * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
	 */
	private String signOutUrl;
	
	/**
	 * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
	 * 
	 * 只在signInResponseType为REDIRECT时生效
	 */
	private String singInSuccessUrl;

	/**
	 * 登录页面
	 */
	private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
	
	/**
	 * 登录的类型 json 还是转发
	 */
	private LoginResponseType loginType = LoginResponseType.REDIRECT;
	
	/**
	 * 记住我的超时时间,默认1小时
	 */
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
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

	public String getSignOutUrl() {
		return signOutUrl;
	}

	public void setSignOutUrl(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	public String getSingInSuccessUrl() {
		return singInSuccessUrl;
	}

	public void setSingInSuccessUrl(String singInSuccessUrl) {
		this.singInSuccessUrl = singInSuccessUrl;
	}

}
