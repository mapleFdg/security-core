package com.maple.security.core.social.weixin.connet;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maple.security.core.social.weixin.ScopeType;

public class WeixinOAuth2Template extends OAuth2Template {

	private static final Logger log = LoggerFactory.getLogger(WeixinOAuth2Template.class);

	/**
	 * 应用授权作用域 snsapi_base snsapi_userinfo
	 */
	private ScopeType scope;

	private String clientId;

	private String clientSecret;

	private String accessTokenUrl;

	private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl,ScopeType scope) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
		this.scope = scope;
	}

	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> additionalParameters) {

		StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);

		accessTokenRequestUrl.append("?appid=" + this.clientId);
		accessTokenRequestUrl.append("&secret=" + this.clientSecret);
		accessTokenRequestUrl.append("&code=" + authorizationCode);
		accessTokenRequestUrl.append("&grant_type=" + "authorization_code");
		accessTokenRequestUrl.append("&redirect_uri=" + redirectUri);

		return getAccessToken(accessTokenRequestUrl.toString());
	}
	
	@Override
	public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
		StringBuilder accessTokenRequestUrl = new StringBuilder(REFRESH_TOKEN_URL);

		accessTokenRequestUrl.append("?appid=" + this.clientId);
		accessTokenRequestUrl.append("&refresh_token=" + refreshToken);
		accessTokenRequestUrl.append("&grant_type=" + "refresh_token");

		return getAccessToken(accessTokenRequestUrl.toString());
	}

	@SuppressWarnings("unchecked")
	public AccessGrant getAccessToken(String accessTokenRequestUrl) {

		log.info("获取accessToken的url为：" + accessTokenRequestUrl);

		String response = getRestTemplate().getForObject(accessTokenRequestUrl, String.class);

		log.info("获取accessToken的响应为：" + response);

		Map<String, Object> result = null;
		try {
			result = new ObjectMapper().readValue(response, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回错误码时直接返回空
		if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
			String errcode = MapUtils.getString(result, "errcode");
			String errmsg = MapUtils.getString(result, "errmsg");
			throw new RuntimeException("获取access token失败, errcode:" + errcode + ", errmsg:" + errmsg);
		}

		WeixinAccessGrant accessToken = new WeixinAccessGrant(MapUtils.getString(result, "access_token"),
				MapUtils.getString(result, "scope"), MapUtils.getString(result, "refresh_token"),
				MapUtils.getLong(result, "expires_in"));

		accessToken.setOpenid(MapUtils.getString(result, "openid"));

		return accessToken;
	}

	/**
	 * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
	 */
	@Override
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		parameters.add("appid", this.clientId);
		parameters.add("scope", scope.name());
		String url = super.buildAuthenticateUrl(parameters);
		url = url + "&#wechat_redirect";
		return url;
	}

	@Override
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		return buildAuthenticateUrl(parameters);
	}

	/**
	 * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
