package com.poshist.sys.config;


import com.poshist.sys.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/common/**","/dist/**","/bower_components/**","/plugins/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(authenticationSuccessHandler()).failureHandler(authenticationFailedHandler())
                .loginPage("/login").failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .permitAll();
        http.csrf().disable();
    }


     @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
     }


    @Bean
    public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccesssHandler authenticationSuccessHandler = new AuthenticationSuccesssHandler();
        return authenticationSuccessHandler;
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailedHandler() {
        AuthenticationFailedHandler authenticationFailedHandler = new AuthenticationFailedHandler();
        return authenticationFailedHandler;
    }

}



