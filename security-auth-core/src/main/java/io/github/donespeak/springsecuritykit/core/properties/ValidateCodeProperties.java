package io.github.donespeak.springsecuritykit.core.properties;

import lombok.Data;

/**
 * 验证码相关属性配置
 *
 * @author Yang Guanrong
 * @date 2020/04/04 22:26
 */
@Data
public class ValidateCodeProperties {

	@Data
	public static class CodeProperties {
		/**
		 * 验证码长度
		 */
		private int length = 6;
		/**
		 * 验证码过期时间，单位为秒
		 */
		private int expireIn = 60;
		/**
		 * 拦截校验的url，用逗号隔开
		 */
		private String urls;
	}

	@Data
	public static class ImageCodeProperties extends CodeProperties {
		/**
		 * 验证码图片宽度
		 */
		private int width = 67;
		/**
		 * 验证码图片高度
		 */
		private int height = 23;
	}

	@Data
	public static class SmsCodeProperties extends CodeProperties {

	}

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();
	/**
	 * sms验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();
}
