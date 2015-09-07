package com.sauloaraujo.timesheet.rest;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.sauloaraujo.timesheet.domain.ErrorsException;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;
import cz.jirutka.spring.exhandler.support.HttpMessageConverterUtils;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(exceptionHandlerExceptionResolver());
		exceptionResolvers.add(restHandlerExceptionResolver());		
	}
	
    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(HttpMessageConverterUtils.getDefaultHttpMessageConverters());
        return resolver;
    }	
	
    @Bean
    public RestHandlerExceptionResolver restHandlerExceptionResolver() {
        return RestHandlerExceptionResolver.builder()
        	.addErrorMessageHandler(EmptyResultDataAccessException.class, HttpStatus.NOT_FOUND)
        	.addHandler(ErrorsException.class, new ErrorsExceptionHandler())
        	.defaultContentType(MediaType.APPLICATION_JSON)
        	.messageSource(messageSource())        	
        	.build();
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}