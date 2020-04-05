package io.github.donespeak.springsecuritykit.browser.support;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 22:13
 */
@Data
public class SimpleResponse {
	private String content;

	public SimpleResponse(String content) {
		this.content = content;
	}
}
