package com.sauloaraujo.timesheet.rest;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(RestHandlerExceptionResolver.builder().build());		
	}
}