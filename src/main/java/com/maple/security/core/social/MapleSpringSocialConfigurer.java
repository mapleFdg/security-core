package com.maple.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class MapleSpringSocialConfigurer extends SpringSocialConfigurer {
	
	private String filterProcessesUrl;
	
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;
	
	public MapleSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		
		filter.setFilterProcessesUrl(getFilterProcessesUrl());
		
		// 后处理
		if(getSocialAuthenticationFilterPostProcessor() != null) {
			getSocialAuthenticationFilterPostProcessor().process(filter);
		}
		
		return super.postProcess(object);
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


	public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
		this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
	}
	
}
