package com.maple.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import com.maple.security.core.social.qq.api.QQ;
import com.maple.security.core.social.qq.api.QQImpl;

/**
 * 
 * @author hzc
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


	private String appId;
	
	/**
	 * 跳转的地址
	 */
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

	/**
	 * 获取token的地址
	 */
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	/**
	 * 新建 oauth2Template
	 * 
	 * @param appId
	 * @param appSecret
	 */
	public QQServiceProvider(String appId,String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}
	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
