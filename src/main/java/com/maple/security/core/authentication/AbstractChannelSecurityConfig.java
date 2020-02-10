/**
 * 
 */
package com.maple.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.maple.security.core.properties.SecurityConstants;

/**
 * 
 * 基础表单检验配置类
 * 
 * @author hzc
 *
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler mapleAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler mapleAuthenticationFailureHandler;
	
	/**
	 * 配置基础的表单登录
	 * 
	 * @param http
	 * @throws Exception
	 */
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 自定义登录页面
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM) // 配置登录的处理页面
				.successHandler(mapleAuthenticationSuccessHandler) // 自定义登录成功处理
				.failureHandler(mapleAuthenticationFailureHandler); // 自定义登录失败处理
	}
}
