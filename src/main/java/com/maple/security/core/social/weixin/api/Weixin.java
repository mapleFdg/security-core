package com.maple.security.core.social.weixin.api;

/**
 * 微信API调用接口
 * 
 * @author hzc
 *
 */
public interface Weixin {

	public WeixinUserInfo getWeixinUserInfo(String openid);

}
