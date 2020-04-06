package io.github.donespeak.springsecuritykit.core.validate.code.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import io.github.donespeak.springsecuritykit.core.validate.code.AbstractValidateCodeProcessor;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeGenerator;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeType;

import javax.imageio.ImageIO;
import java.io.OutputStream;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:00
 */
@Slf4j
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	@Autowired
	private ImageCodeGenerator imageCodeGenerator;

    @Override
    public ValidateCodeType getValidateCodeType() {
        return ValidateCodeType.IMAGE;
    }

    @Override
    protected ValidateCodeGenerator getValidateCodeGenerator() {
        return imageCodeGenerator;
    }

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
    	try (OutputStream out = request.getResponse().getOutputStream()) {
		    ImageIO.write(imageCode.getImage(), "JPEG", out);
	    }
    }
}
