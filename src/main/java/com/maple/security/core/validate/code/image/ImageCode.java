package com.maple.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.maple.security.core.validate.code.ValidateCode;

/**
 * @author hzc
 *
 */
public class ImageCode extends ValidateCode {

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
