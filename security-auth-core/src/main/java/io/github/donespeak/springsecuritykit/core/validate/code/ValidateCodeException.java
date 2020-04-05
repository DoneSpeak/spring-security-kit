package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:23
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 2674223243672688243L;

	public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
