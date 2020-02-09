package com.maple.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.properties.SecurityProperties;
import com.maple.security.core.validate.code.ValidateCode;
import com.maple.security.core.validate.code.ValidateCodeGenerator;

/**
 * 生成手机短信验证码
 * 
 * @author hzc
 *
 */
@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());

		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

}
