package com.maple.security.core.validate.code;

import com.maple.security.core.properties.SecurityConstants;

/**
 * 校验码类型
 * 
 * @author hzc
 *
 */
public enum ValidateCodeType {

	/**
	 * 图片验证码
	 */
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	},
	
	/**
	 * 手机验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 * 
	 * @return
	 */
	public abstract String getParamNameOnValidate();
	

}
