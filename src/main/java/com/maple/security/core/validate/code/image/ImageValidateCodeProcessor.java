/**
 * 
 */
package com.maple.security.core.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.maple.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * 
 * 图片校验码处理器
 * 
 * @author hzc
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	/**
	 * 发送图片验证码，并将其写进response中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
		ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
