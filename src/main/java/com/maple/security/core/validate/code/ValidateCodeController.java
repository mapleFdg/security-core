package com.maple.security.core.validate.code;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.properties.SecurityConstants;

@RestController
public class ValidateCodeController {

	/**
	 * 获取所有ValidateCodeProcessor的实现类
	 */
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		// 根据类型获取对应的validateCodeProcessor实现类，并且调用create方法创建校验码
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}
}
