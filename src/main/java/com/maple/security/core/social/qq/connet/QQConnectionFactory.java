package com.maple.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.maple.security.core.social.qq.api.QQ;

/**
 * 
 * 
 * @author hzc
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * 新建Adapter、和provider
	 * 
	 * @param providerId
	 * @param appId
	 * @param appSecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
