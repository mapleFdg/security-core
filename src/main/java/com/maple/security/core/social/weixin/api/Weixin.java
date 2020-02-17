package com.maple.security.core.social.weixin.api;

import com.maple.security.core.social.weixin.LanguageType;

public interface Weixin {

	public WeixinUserInfo getWeixinUserInfo(String openid);

}
