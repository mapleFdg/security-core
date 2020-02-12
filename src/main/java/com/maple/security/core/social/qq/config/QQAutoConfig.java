package com.maple.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.maple.security.core.properties.QQProperties;
import com.maple.security.core.properties.SecurityProperties;
import com.maple.security.core.social.qq.connet.QQConnectionFactory;

/**
 * 
 * 
 * @author hzc
 *
 *
 * @ConditionalOnProperty 只有当配置了maple.security.social.qq.app-id时，此配置才生效
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "maple.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {

		QQProperties qqProperties = securityProperties.getSocial().getQq();

		return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(),
				qqProperties.getAppSecret());
	}

}
