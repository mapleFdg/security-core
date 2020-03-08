package com.maple.security.core.social;

import org.springframework.social.connect.Connection;

import com.maple.security.core.social.support.SocialUserInfo;

/**
 * 社交登录controller
 * 
 * @author hzc
 *
 */
public abstract class SocialController {
	
	/**
	 * 根据Connection信息构建SocialUserInfo
	 * 
	 * @param connection
	 * @return
	 */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickName(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
	
	
}
