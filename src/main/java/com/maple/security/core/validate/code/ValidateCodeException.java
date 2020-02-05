/**
 * 
 */
package com.maple.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author hzc
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 844248991024461918L;

	public ValidateCodeException(String msg) {
		super(msg);
	}


}
