package com.sauloaraujo.timesheet.web.configuration.security;

import javax.sql.DataSource;

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
	private @Autowired DataSource dataSource;
	private @Autowired AuthenticationEntryPoint authenticationEntryPoint;
	private @Autowired AuthenticationFailureHandler authenticationFailureHandler; 
	private @Autowired AuthenticationSuccessHandler authenticationSuccessHandler;	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginProcessingUrl = "/api/authentications";
		
        http
        	.csrf()
        		.disable()
        	.headers().frameOptions()
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