package com.sauloaraujo.timesheet.domain;

import org.springframework.validation.Errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1602848837313064018L;
	
	private Errors errors;
}