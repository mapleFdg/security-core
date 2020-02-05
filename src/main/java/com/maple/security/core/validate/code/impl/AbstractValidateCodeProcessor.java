package com.maple.security.core.validate.code.impl;

import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.validate.code.ValidateCode;
import com.maple.security.core.validate.code.ValidateCodeProcessor;

/**
 * 
 * @author hzc
 *
 * @param <C>
 */
public class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	@Override
	public void create(ServletWebRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(ServletWebRequest request) {
		// TODO Auto-generated method stub
		
	}

}
