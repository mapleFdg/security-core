package com.maple.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 
 * 继承默认的社交登录配置，加入自定义的后处理逻辑
 * 
 * @author hzc
 *
 */
public class MapleSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	public MapleSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);

		filter.setFilterProcessesUrl(getFilterProcessesUrl());

		// 后处理
		if (getSocialAuthenticationFilterPostProcessor() != null) {
			getSocialAuthenticationFilterPostProcessor().process(filter);
		}

		return (T) filter;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
		return socialAuthenticationFilterPostProcessor;
	}

	public void setSocialAuthenticationFilterPostProcessor(
			SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
		this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
	}

}
