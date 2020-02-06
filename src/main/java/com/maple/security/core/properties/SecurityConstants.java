package com.maple.security.core.properties;


public interface SecurityConstants {
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 * 
	 * @see SecurityController
	 */
	String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * 默认登录页面
	 * 
	 * @see SecurityController
	 */
	String DEFAULT_SIGN_IN_PAGE_URL = "/maple-signIn.html";
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
	
	/**
	 * 默认的处理验证码的url前缀
	 */
	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
	
	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
}
