package com.sauloaraujo.timesheet;

import java.util.TimeZone;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class TimesheetApplication {
	@Bean
	@Profile("development")
	public ServletRegistrationBean h2() {
		return new ServletRegistrationBean(new WebServlet(), "/h2/*");
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages");
		return source;
	}
	
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		SpringApplication.run(TimesheetApplication.class, args);
	}
}