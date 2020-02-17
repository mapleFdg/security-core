package com.maple.security.core.properties;

/**
 * 第三方登录配置文件
 * 
 * @author hzc
 *
 */
public class SocialProperties {

	/**
	 * QQ配置文件
	 */
	private QQProperties qq;

	/**
	 * 微信配置文件
	 */
	private WeixinProperties weixin;
	
	/**
	 * 社交登录功能拦截的url
	 */
	private String filterProcessesUrl = "/auth";

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public WeixinProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}
	
}
