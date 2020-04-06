package io.github.donespeak.springsecuritykit.core.validate.code.sms;

/**
 * 发送手机验证码
 *
 * @author Yang Guanrong
 * @date 2020/04/06 22:45
 */
public interface SmsCodeSender {

	/**
	 * 发送验证码<code>code</code>到<code>mobile</code>。
	 */
	void send(String mobile, String code);
}
