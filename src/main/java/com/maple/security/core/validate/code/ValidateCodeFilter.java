package com.maple.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maple.security.core.properties.SecurityConstants;
import com.maple.security.core.properties.SecurityProperties;

/**
 * 
 * OncePerRequestFilter 保证过滤器只被调用一次
 * 
 * InitializingBean 实现这个接口的意义是，在其他参数都初始化完毕后，我们初始化url
 * 
 * @author hzc
 *
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private Logger log = LoggerFactory.getLogger(ValidateCodeFilter.class);

	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 存放所需要校验码的url
	 */
	private Map<String, ValidateCodeType> urlMap = new HashMap<>();

	/**
	 * 系统中的校验码处理器
	 */
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 系统配置信息
	 */
	@Autowired
	private SecurityProperties securityProperties;

	// 工具类，用于匹配字符串
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 在配置信息加载完成后执行
	 * 
	 * 初始化要拦截的url配置信息
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestUrl = request.getRequestURI();
		String requestMethod = request.getMethod();

		ValidateCodeType validateCodeType = getValidateCodeType(request);

		if (validateCodeType != null) {
			log.info("需要进行验证码校验的url为：" + requestUrl + "，请求方式为：" + requestMethod + "，校验的验证码类型为："
					+ validateCodeType.name());
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType.name().toLowerCase())
						.validate(new ServletWebRequest(request, response));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
	 * 
	 * @param urlString
	 * @param type
	 */
	private void addUrlToMap(String urlString, ValidateCodeType type) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for (String url : urls) {
				urlMap.put(url, type);
			}
		}
	}

	/**
	 * 获取校验码的类型，如果当前请求不需要校验，则返回null
	 * 
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType type = null;
		if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					type = urlMap.get(url);
				}
			}
		}
		return type;
	}
}
