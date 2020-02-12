package com.maple.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.maple.security.core.properties.SecurityProperties;
import com.maple.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.maple.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.maple.security.core.validate.code.sms.SmsCodeSender;

/**
 * 
 * @author hzc
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * ConditionalOnMissingBean 若不存在imageCodeGenerator这个bean时才使用下面这个方法进行初始化
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
