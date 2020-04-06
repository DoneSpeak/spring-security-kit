package io.github.donespeak.springsecuritykit.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 22:46
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n").append("\t\t");
		sb.append("send code " + code + " to mobile " + mobile);
		sb.append("\n");
		log.warn("Please provide a real smsCodeSender to override this default sender.");
		log.info(sb.toString());
    }
}
