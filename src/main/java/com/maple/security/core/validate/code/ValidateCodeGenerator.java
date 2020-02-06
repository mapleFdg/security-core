/**
 * 
 */
package com.maple.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 * 验证码生成接口
 * 
 * @author hzc
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @return
	 */
	public ValidateCode generate(ServletWebRequest request);
}
