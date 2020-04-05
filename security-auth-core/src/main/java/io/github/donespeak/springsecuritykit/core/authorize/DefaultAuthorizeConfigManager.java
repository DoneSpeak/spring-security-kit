package io.github.donespeak.springsecuritykit.core.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/02/03 17:35
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    /**
     * 配置所有的AuthorizeConfigProvider，调用该方法的地方应该在完成HttpSecurity的基础配置之后。
     * @param urlRegistry
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry) {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;

        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(urlRegistry);
            if (existAnyRequestConfig && currentIsAnyRequestConfig) {
                // 仅允许存在一个AnyRequest
                throw new RuntimeException("Duplicated anyRequest urlRegistry:" + existAnyRequestConfigName + ","
                    + authorizeConfigProvider.getClass().getSimpleName());
            } else if (currentIsAnyRequestConfig) {
                existAnyRequestConfig = true;
                existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
            }
        }

        if (!existAnyRequestConfig) {
            // 如果配置都没有声明要全部认证，则设置全部url要认证
            urlRegistry.anyRequest().authenticated();
        }
    }
}