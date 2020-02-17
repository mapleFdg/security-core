package com.maple.security.core.social.weixin.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.maple.security.core.social.weixin.LanguageType;
import com.maple.security.core.social.weixin.ScopeType;
import com.maple.security.core.social.weixin.api.Weixin;
import com.maple.security.core.social.weixin.api.WeixinImpl;

public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {

	/**
	 * 微信获取授权码的url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	private LanguageType lang;

	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeixinServiceProvider(String appId, String appSecret, LanguageType lang,ScopeType scope) {
		super(new WeixinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN,scope));
		this.lang = lang;
	}

	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinImpl(accessToken,lang);
	}

}
