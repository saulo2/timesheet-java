package com.sauloaraujo.timesheet.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.sauloaraujo.timesheet.domain.ErrorsException;

import cz.jirutka.spring.exhandler.handlers.ErrorMessageRestExceptionHandler;
import cz.jirutka.spring.exhandler.messages.ErrorMessage;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;

public class ErrorsExceptionHandler extends ErrorMessageRestExceptionHandler<ErrorsException> {
	public ErrorsExceptionHandler() {
		super(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
    public ValidationErrorMessage createBody(ErrorsException exception, HttpServletRequest request) {
        ErrorMessage orig = super.createBody(exception, request);
        ValidationErrorMessage message = new ValidationErrorMessage(orig);
        Errors errors = exception.getErrors();
        for (ObjectError err : errors.getGlobalErrors()) {
        	message.addError(err.getDefaultMessage());
        }
        for (FieldError error : errors.getFieldErrors()) {        	
        	message.addError(error.getField(), error.getRejectedValue(), error.getDefaultMessage());
        }
        return message;
    }
}