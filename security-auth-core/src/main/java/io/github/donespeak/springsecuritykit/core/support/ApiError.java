package io.github.donespeak.springsecuritykit.core.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 01:11
 */
@Data
@NoArgsConstructor
public class ApiError {
    private String status;
    private String message;

    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
