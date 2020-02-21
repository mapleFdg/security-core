package com.maple.security.core.authentication;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService{

	private final static Logger log = LoggerFactory.getLogger(MyUserDetailService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("登录名：" + username);
		
		String password = passwordEncoder.encode("123456");
		
		log.info("密码：" + password);
		
		return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("登录名：" + userId);
		
		String password = passwordEncoder.encode("123456");
		
		log.info("密码：" + password);
		
		return new SocialUser(userId, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
	}

}
