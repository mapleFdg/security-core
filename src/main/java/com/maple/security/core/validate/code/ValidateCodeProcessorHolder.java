package com.maple.security.core.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * 校验码处理器管理器
 * 
 * 
 * 
 * @author hzc
 *
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * 根据传入类型获取相应的validateCodeProcessor
	 * 
	 * @param type
	 * @return
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.name());
	}

	/**
	 * 根据传入类型获取相应的validateCodeProcessor
	 * 
	 * @param type
	 * @return
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);
		if (validateCodeProcessor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return validateCodeProcessor;

	}

}
