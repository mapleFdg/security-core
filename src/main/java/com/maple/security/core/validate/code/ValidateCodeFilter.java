package com.maple.security.core.validate.code;

import java.io.IOException;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maple.security.core.properties.SecurityConstants;

@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	private Logger log = LoggerFactory.getLogger(ValidateCodeFilter.class);

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestUrl = request.getRequestURI();
		String requestMethod = request.getMethod();

		log.info("进行验证码检验，请求的url为：" + requestUrl + "，请求的方式为：" + requestMethod);

		if (StringUtils.equalsIgnoreCase(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, requestUrl)
				&& StringUtils.equalsIgnoreCase(requestMethod, "POST")) {
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor("image")
						.validate(new ServletWebRequest(request, response));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}
