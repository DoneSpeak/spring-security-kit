package io.github.donespeak.springsecuritykit.core.validate.code.image;

import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片认证码
 *
 * @author Yang Guanrong
 * @date 2020/04/05 16:40
 */
@Data
public class ImageCode extends ValidateCode {

	private BufferedImage image;

	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
}
