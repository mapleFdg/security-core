package com.maple.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author hzc
 *
 */
@ConfigurationProperties(prefix = "maple.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();
	
	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

}
