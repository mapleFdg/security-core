package com.maple.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.maple.security.core.properties.SecurityProperties;

/**
 * 
 * @author hzc
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

	/**
	 * 声明加密方法
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncod() {
		return new BCryptPasswordEncoder();
	}
}
