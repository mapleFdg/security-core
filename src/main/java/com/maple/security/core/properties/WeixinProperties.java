package com.maple.security.core.properties;

import com.maple.security.core.social.weixin.LanguageType;
import com.maple.security.core.social.weixin.ScopeType;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 
 * 第三方登录，微信的配置信息类
 * 
 * @author hzc
 *
 */
public class WeixinProperties extends SocialProperties {

	/**
	 * 第三方ID，用来决定发起第三方登录的url，默认是weixin
	 */
	private String providerId = "weixin";

	private LanguageType lang = LanguageType.zh_CN;

	private ScopeType scope = ScopeType.snsapi_base;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public LanguageType getLang() {
		return lang;
	}

	public void setLang(LanguageType lang) {
		this.lang = lang;
	}

	public ScopeType getScope() {
		return scope;
	}

	public void setScope(ScopeType scope) {
		this.scope = scope;
	}

}
