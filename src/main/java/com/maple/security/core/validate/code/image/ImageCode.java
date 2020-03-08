package com.maple.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.maple.security.core.validate.code.ValidateCode;

/**
 * 
 * 图片验证码
 * 
 * @author hzc
 *
 */
public class ImageCode extends ValidateCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3759730393693215145L;
	
	
	private BufferedImage image;

	public ImageCode() {

	}

	public ImageCode(BufferedImage image, String code, int exprieIn) {
		super(code, exprieIn);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime exprieTime) {
		super(code, exprieTime);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
