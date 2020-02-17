package com.maple.security.core.social.weixin.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maple.security.core.social.weixin.LanguageType;

public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

	private static final Logger log = LoggerFactory.getLogger(WeixinImpl.class);

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private LanguageType lang;

	private String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s&lang=%s";

	public WeixinImpl(String accessToken,LanguageType lang) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.lang = lang;
	}
	
//	@Override
//	protected List<HttpMessageConverter<?>> getMessageConverters() {
//		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
//		messageConverters.remove(0);
//		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		return super.getMessageConverters();
//	}
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

	@Override
	public WeixinUserInfo getWeixinUserInfo(String openid) {

		String url = String.format(URL_GET_USERINFO, openid, lang.name());

		String response = this.getRestTemplate().getForObject(url, String.class);

		log.info("获取微信用户信息的响应：" + response);

		try {
			WeixinUserInfo userInfo = objectMapper.readValue(response, WeixinUserInfo.class);
			return userInfo;
		} catch (IOException e) {
			throw new RuntimeException("获取用户信息失败");
		}
	}

}
