package com.maple.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.maple.security.core.properties.SecurityConstants;


public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
	
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public SmsAuthenticationFilter() {
		super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request     that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its
	 *                    details set
	 */
	protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter. If
	 * set to true, and an authentication request is received which is not a POST
	 * request, an exception will be raised immediately and authentication will not
	 * be attempted. The <tt>unsuccessfulAuthentication()</tt> method will be called
	 * as if handling a failed authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getMobileParameter() {
		return mobileParameter;
	}

	public void setMobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}
}
