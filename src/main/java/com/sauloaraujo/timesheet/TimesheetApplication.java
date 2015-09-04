package com.sauloaraujo.timesheet;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class TimesheetApplication {
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
	
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		SpringApplication.run(TimesheetApplication.class, args);
	}
}