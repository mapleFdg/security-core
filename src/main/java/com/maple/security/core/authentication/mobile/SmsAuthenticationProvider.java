/**
 * 
 */
package com.maple.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author hzc
 *
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
		
		UserDetails user = getUserDetailsService().loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
		
		if(user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		SmsAuthenticationToken tokenResult = new SmsAuthenticationToken(user, user.getAuthorities());
		
		return tokenResult;
	}

	/**
	 * 判断传进来的是否为SmsCodeAuthenticationToken类型
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
