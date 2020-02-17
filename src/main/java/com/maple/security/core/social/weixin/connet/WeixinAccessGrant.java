package com.maple.security.core.social.weixin.connet;

import org.springframework.social.oauth2.AccessGrant;

/**
 * 
 * @author hzc
 *
 */
public class WeixinAccessGrant extends AccessGrant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8284697642011836807L;

	private String openid;

	public WeixinAccessGrant(){
		super("");
	}
	
	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
