package io.github.donespeak.springsecuritykit.core.validate.code;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 16:34
 */
@Data
public class ValidateCode implements Serializable {

	private static final long serialVersionUID = 683605873982665896L;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 过期时间
	 */
	private LocalDateTime expireTime;

	/**
	 *
	 * @param code 验证码
	 * @param expireIn 有效时间长度，单位为秒
	 */
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
