package io.github.donespeak.springsecuritykit.core.validate.code;

import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:40
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private List<ValidateCodeProcessor> processors;

	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		for (ValidateCodeProcessor processor: processors) {
			if(processor.getValidateCodeType().equals(type)) {
				return processor;
			}
		}
		return null;
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		ValidateCodeType validateCodeType;
		try {
			validateCodeType = ValidateCodeType.valueOf(StringUtils.toUpperCase(type));
		} catch (Exception ex) {
			return null;
		}
		return findValidateCodeProcessor(validateCodeType);
	}
}
