package com.maple.security.core.social.qq.connet;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class QQOAuth2Template extends OAuth2Template {

	private static final Logger log = LoggerFactory.getLogger(QQOAuth2Template.class);

	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}

	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {

		/**
		 * QQ返回的格式
		 * 
		 * access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
		 */
		String responseString = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

		log.info("获取access_token的响应为：" + responseString);

		String result[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseString, "&");

		String accessToken = StringUtils.substringAfterLast(result[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(result[1], "="));
		String refreshToken = StringUtils.substringAfterLast(result[2], "=");
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}

	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
