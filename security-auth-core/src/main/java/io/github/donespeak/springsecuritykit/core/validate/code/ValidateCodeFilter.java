package io.github.donespeak.springsecuritykit.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 19:44
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Map<String, ValidateCodeType> urlMapTypes = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void afterPropertiesSet() {
    	// TODO 这部分的实现还是不够灵活，如果按照如下配置，则表示必须要验证登录路径
	    urlMapTypes.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
	    // TODO 不方便拓展新的验证方式
        addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);
    }

    private void addUrlToMap(String urlsString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlsString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlsString, ",");
            for (String url : urls) {
                urlMapTypes.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        if (validateCodeType == null) {
            // 没有校验类型，表示不支持
            filterChain.doFilter(request, response);
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("Try to validate the code in " + request.getRequestURI() + " with type " + validateCodeType);
        }
        try {
            validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType)
                .validate(new ServletWebRequest(request, response));
            if (log.isDebugEnabled()) {
                log.debug("Validate the code in " + request.getRequestURI() + " successfully.");
            }
        } catch (ValidateCodeException e) {
            if (log.isDebugEnabled()) {
                log.debug("Fail to validate the in " + request.getRequestURI());
            }
            authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            // 验证不通过，不再继续执行filter
            return;
        }
        filterChain.doFilter(request, response);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "GET")) {
            // GET 请求不需要验证码
            return null;
        }
        String requestUrl = request.getRequestURI();
        for (String url : urlMapTypes.keySet()) {
            if (antPathMatcher.match(url, requestUrl)) {
                return urlMapTypes.get(url);
            }
        }
        return null;
    }
}
