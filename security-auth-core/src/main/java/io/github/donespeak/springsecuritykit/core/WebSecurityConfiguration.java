// package io.github.donespeak.springsecuritykit.core;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AnonymousAuthenticationProvider;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
// /**
//  * @author Yang Guanrong
//  * @date 2020/01/12 22:51
//  */
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//     @Autowired
//     private PasswordEncoder passwordEncoder;
//
//     // @Override
//     // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     // 如果直接覆盖该方法，而不调用父类的方法，就会报一下的错误。 -> 继承重写方法的时候，要特别注意是否覆盖了父类原本的实现
//     // TODO 查明是什么原因
//     //     // super.configure(auth);
//     //     // No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//     // }
//
//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
//
//     @Bean
//     @Override
//     public UserDetailsService userDetailsService() {
//         String finalPassword = "123456";
//         finalPassword = passwordEncoder.encode(finalPassword);
//         InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//         manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
//         manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//         return manager;
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         // @formatter:off
//         http
//             .requestMatchers().anyRequest()
//             .and()
//             .authorizeRequests()
//             .antMatchers("/oauth/**").permitAll();
//         // @formatter:on
//     }
// }