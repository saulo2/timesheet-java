package com.sauloaraujo.timesheet.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sauloaraujo.timesheet.domain.BusinessException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleResourceNotFoundException(BusinessException exception, HttpServletRequest request) {
		return new ResponseEntity<>("", null, HttpStatus.UNPROCESSABLE_ENTITY);
	}		
}