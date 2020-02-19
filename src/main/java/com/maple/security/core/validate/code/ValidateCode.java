/**
 * 
 */
package com.maple.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hzc
 *
 */
public class ValidateCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1610861362581104396L;

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
