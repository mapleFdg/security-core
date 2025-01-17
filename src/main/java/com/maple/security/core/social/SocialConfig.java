package com.maple.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.maple.security.core.properties.SecurityProperties;
import com.maple.security.core.social.support.MapleSpringSocialConfigurer;
import com.maple.security.core.social.support.SocialAuthenticationFilterPostProcessor;

/**
 * 社交登录配置主类
 * 
 * @author hzc
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	/**
	 * 
	 * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
	 * 
	 * @return
	 */
	@Bean
	public SpringSocialConfigurer mapleSocialSecurityConfig() {
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		MapleSpringSocialConfigurer socialConfigurer = new MapleSpringSocialConfigurer(filterProcessesUrl);
		socialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		socialConfigurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return socialConfigurer;
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		// 可修改表的前缀，设置表前缀
		repository.setTablePrefix("");

		if (connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}

		return repository;
	}

	/**
	 * 
	 * 用来处理注册流程的工具类
	 * 
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator));
	}

}
