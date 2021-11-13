package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
		if(type == null) {
			return null;
		}
		ValidateCodeType validateCodeType;
		try {
			validateCodeType = ValidateCodeType.valueOf(type.toUpperCase());
		} catch (Exception ex) {
			return null;
		}
		return findValidateCodeProcessor(validateCodeType);
	}
}
