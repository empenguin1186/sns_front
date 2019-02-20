package jp.co.training.snsFront;

import jp.co.training.snsFront.Model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static String ROLE_USER = "USER";

    @Autowired
    UserDetailsService userDetailsService;

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
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] permittedUrls = {"/login","/"};
        http.authorizeRequests()
                    .antMatchers(permittedUrls).permitAll()
                    .antMatchers("/timeline").hasRole(ROLE_USER)
                .and()
                .exceptionHandling();

        http.formLogin()
                .loginProcessingUrl("/authenticate")
                    .loginPage("/login")
//                .successForwardUrl("/profile/kodaira")
                    .failureUrl("/login?error=notPermitted")
                    .defaultSuccessUrl("/timeline", true)
                    .usernameParameter("username")
                    .passwordParameter("password").permitAll();
//                .csrf()
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
//
//    @Bean
//    @ConfigurationProperties("inmotion.user")
//    public UserDto supportUser() {
//        return new UserDto();
//    }

}
