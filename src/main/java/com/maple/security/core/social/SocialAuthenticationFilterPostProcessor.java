package com.maple.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

public interface SocialAuthenticationFilterPostProcessor {

	public void process(SocialAuthenticationFilter filter);

}
