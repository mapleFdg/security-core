/**
 * 
 */
package com.maple.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.maple.security.core.social.qq.api.QQ;
import com.maple.security.core.social.qq.api.QQUserInfo;

/**
 * @author hzc
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试API是否连通
	 * 
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * 
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		
		QQUserInfo userInfo = api.getQQUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// do nothing
		
	}

}
