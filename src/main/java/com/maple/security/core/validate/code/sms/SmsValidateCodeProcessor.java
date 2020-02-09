package com.maple.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.properties.SecurityConstants;
import com.maple.security.core.validate.code.ValidateCode;
import com.maple.security.core.validate.code.impl.AbstractValidateCodeProcessor;
/**
 * 
 * 手机短信验证码处理器
 * 
 * @author hzc
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	@Autowired
	private SmsCodeSender smsCodeSender;

	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {

		String mobile = ServletRequestUtils.getStringParameter(request.getRequest(),
				SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);

		smsCodeSender.send(mobile, validateCode.getCode());

	}

}
