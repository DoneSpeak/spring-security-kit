package io.github.donespeak.springsecuritykit.browser.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeRepository;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeType;

import javax.servlet.http.HttpSession;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 01:27
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

	private static final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
	    getSession(request).setAttribute(getSessionKey(request, type), code);
    }

    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
		return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) getSession(request).getAttribute(getSessionKey(request, type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
	    getSession(request).removeAttribute(getSessionKey(request, type));
    }

    private HttpSession getSession(ServletWebRequest request) {
	    return request.getRequest().getSession();
    }
}
