package com.sauloaraujo.timesheet.web.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	private static final String USER = "USER";
	
	private @Autowired AuthenticationEntryPoint authenticationEntryPoint;
	private @Autowired AuthenticationFailureHandler authenticationFailureHandler; 
	private @Autowired AuthenticationSuccessHandler authenticationSuccessHandler; 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("k").password("k").roles(USER).and()
				.withUser("s").password("s").roles(USER).and();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginProcessingUrl = "/api/authentications";
		
        http
        	.csrf()
        		.disable()
        	.exceptionHandling()
        		.authenticationEntryPoint(authenticationEntryPoint)
        		.and()
        	.formLogin()
        		.loginProcessingUrl(loginProcessingUrl)
            	.failureHandler(authenticationFailureHandler)
            	.successHandler(authenticationSuccessHandler)            	
        		.and()
        	.authorizeRequests()
    			.antMatchers(loginProcessingUrl).permitAll()
            	.antMatchers("/api/**").authenticated()
        		.anyRequest().permitAll();
	}
}