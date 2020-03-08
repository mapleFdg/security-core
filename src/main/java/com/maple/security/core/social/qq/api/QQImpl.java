package com.maple.security.core.social.qq.api;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * QQ API 实现类
 * 
 * @author hzc
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private static final Logger log = LoggerFactory.getLogger(QQImpl.class);
	
	/**
	 * openid获取地址
	 */
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	/**
	 * 用户信息获取
	 */
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	/**
	 * QQ Appid
	 */
	private String appId;

	private String openId;

	private ObjectMapper objectMapper = new ObjectMapper();

	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;

		String url = String.format(URL_GET_OPENID, accessToken);

		String result = getRestTemplate().getForObject(url, String.class);

		log.info("获取openid的响应：" + result);

		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");

	}

	@Override
	public QQUserInfo getQQUserInfo() {

		String url = String.format(URL_GET_USERINFO, appId, openId);

		String result = getRestTemplate().getForObject(url, String.class);

		log.info("获取QQ用户信息的响应：" + result);

		try {
			QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);

			userInfo.setOpenId(openId);

			return userInfo;
		} catch (IOException e) {
			throw new RuntimeException("获取用户信息失败");
		}
	}

}
