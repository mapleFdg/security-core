package com.maple.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.validate.code.ValidateCode;
import com.maple.security.core.validate.code.ValidateCodeException;
import com.maple.security.core.validate.code.ValidateCodeGenerator;
import com.maple.security.core.validate.code.ValidateCodeProcessor;
import com.maple.security.core.validate.code.ValidateCodeRepository;
import com.maple.security.core.validate.code.ValidateCodeType;

/**
 * 
 * @author hzc
 *
 * @param <C>
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * /** 收集系统中所有的{@link ValidateCodeGenerator}
	 * 接口的实现，并以Map的形式存入，key为bean名称，value为实现类
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	/**
	 * 验证码存取器
	 */
	@Autowired
	private ValidateCodeRepository validateCodeRepository;

	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		// 获取验证码的类型
		String type = getValidateCodeType().toString().toLowerCase();
		// 获取验证码生成器的实现类的类名
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();

		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存验证码
	 * 
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpirTime());
		// 保存验证码，传入验证码的类型，根据不同的类型进行保存
		validateCodeRepository.save(request, code, getValidateCodeType());
	}

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param validateCode
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType type = getValidateCodeType();

		C codeInSession = (C) validateCodeRepository.get(request, type);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), type.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败！");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(type + "请填写验证码");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(type + "验证码不存在");
		}

		if (codeInSession.isExpire()) {
			validateCodeRepository.remove(request, type);
			throw new ValidateCodeException(type + "验证码已过期，请重新获取");
		}

		if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
			throw new ValidateCodeException(type + "验证码不正确");
		}
		validateCodeRepository.remove(request, type);

	}

	/**
	 * 根据请求的url获取校验码的类型
	 * 
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType() {
		// 从实现类的类名中获取类型，对类名有要求，名称需为imageCode
		String type = StringUtils.substringBefore(getClass().getSimpleName(), ValidateCodeProcessor.class.getSimpleName());
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

}
