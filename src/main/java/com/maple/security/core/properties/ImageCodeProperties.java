package com.maple.security.core.properties;

/**
 * 
 * 图片验证码
 * 
 * @author hzc
 *
 */
public class ImageCodeProperties extends SmsCodeProperties {

	public ImageCodeProperties() {
		setLength(4);
	}
	
	/**
	 * 图片宽度
	 */
	private int width = 67;
	
	/**
	 * 图片长度
	 */
	private int height = 23;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
