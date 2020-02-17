package com.maple.security.core.social.weixin.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.maple.security.core.social.weixin.api.Weixin;
import com.maple.security.core.social.weixin.api.WeixinUserInfo;

public class WeixinAdapter implements ApiAdapter<Weixin> {

	private String openid;

	public WeixinAdapter() {
	}

	public WeixinAdapter(String openId) {
		this.openid = openId;
	}

	@Override
	public boolean test(Weixin api) {
		return true;
	}

	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo profile = api.getWeixinUserInfo(openid);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Weixin api, String message) {
		// TODO Auto-generated method stub

	}

}
