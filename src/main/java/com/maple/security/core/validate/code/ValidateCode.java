/**
 * 
 */
package com.maple.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * @author hzc
 *
 */
public class ValidateCode {

	private String code;

	private LocalDateTime expirTime;

	public ValidateCode() {

	}

	public ValidateCode(String code, int exprieIn) {
		super();
		this.code = code;
		this.setExpirTime(LocalDateTime.now().plusSeconds(exprieIn));
	}

	public ValidateCode(String code, LocalDateTime exprieTime) {
		super();
		this.code = code;
		this.setExpirTime(exprieTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isExpire() {
		return LocalDateTime.now().isAfter(getExpirTime());
	}

	public LocalDateTime getExpirTime() {
		return expirTime;
	}

	public void setExpirTime(LocalDateTime expirTime) {
		this.expirTime = expirTime;
	}

}
