package com.maple.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.maple.security.core.properties.SecurityConstants;

/**
 * 表单登录配置
 * 
 * @author hzc
 *
 */
@Component
public class FormAuthenticationConfig {
	@Autowired
	protected AuthenticationSuccessHandler mapleAuthenticationSuccessHandler;

	@Autowired
	protected AuthenticationFailureHandler mapleAuthenticationFailureHandler;

	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				.successHandler(mapleAuthenticationSuccessHandler)
				.failureHandler(mapleAuthenticationFailureHandler);
	}
}
