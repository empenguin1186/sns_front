package jp.co.training.snsFront;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@Validated
@Configuration
@ConfigurationProperties(prefix = "path")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${role.USER}")
    private String ROLE_USER;

    private String CERT_PROCCESSING_PATH;

    private String LOGIN_PATH;

    private String DEFAULT_SUCCESS_PATH;

    @Autowired
    @Qualifier("authenticationRealm")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] permittedUrls = {LOGIN_PATH, CERT_PROCCESSING_PATH};
        http.authorizeRequests()
                .antMatchers(permittedUrls).permitAll()
                .antMatchers("/timeline").hasAnyAuthority(ROLE_USER);

        http.formLogin()
                .loginProcessingUrl(CERT_PROCCESSING_PATH)
                .loginPage(LOGIN_PATH)
                .permitAll()
                .failureUrl(LOGIN_PATH + "?error=notPermitted")
                .defaultSuccessUrl(DEFAULT_SUCCESS_PATH, false)
                .usernameParameter("username")
                .passwordParameter("password").permitAll()
                .and()
                .csrf();
//                .and()
//                .logout()
//                    .logoutSuccessUrl("/login");
    }

//    @Autowired
//    public ITemplateResolver templateResolver;
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new SpringSecurityDialect());
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        UserDto support = supportUser();
//
//        auth.inMemoryAuthentication()
//                .withUser(support.getUsername()).password(support.getPassword()).roles(ROLE_USER);
//    }

}

