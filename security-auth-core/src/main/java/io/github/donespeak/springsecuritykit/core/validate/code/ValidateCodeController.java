package io.github.donespeak.springsecuritykit.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 16:48
 */
@RestController
public class ValidateCodeController {

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ValidateCodeProcessor processor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
        if(processor == null) {
            throw new NoHandlerFoundException("GET", request.getRequestURI(), null);
        }
        processor.create(new ServletWebRequest(request, response));
    }
}
