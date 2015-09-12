package com.sauloaraujo.timesheet.web.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sauloaraujo.timesheet.domain.ErrorsException;

@ControllerAdvice
public class ErrorsExceptionAdvice {
	private @Autowired MessageSource source;
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorsDto handleThrowable(Locale locale, Throwable throwable) {
		throwable.printStackTrace();
		
		ObjectErrorDto globalError = new ObjectErrorDto();
		globalError.setMessage(throwable.getMessage());
		
		List<ObjectErrorDto> globalErrors = new ArrayList<>();
		globalErrors.add(globalError);
		
		ErrorsDto errors = new ErrorsDto();
		errors.setGlobalErrors(globalErrors);
		return errors;
	}

	@ExceptionHandler(ErrorsException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody ErrorsDto handleErrorsException(Locale locale, ErrorsException exception) {
		List<ObjectErrorDto> globalErrors = new ArrayList<>();
		for (ObjectError error : exception.getErrors().getGlobalErrors()) {			
			ObjectErrorDto dto = new ObjectErrorDto();
			copy(locale, error, dto);
			globalErrors.add(dto);
		}

		List<FieldErrorDto> fieldErrors = new ArrayList<>();
		for (FieldError error : exception.getErrors().getFieldErrors()) {			
			FieldErrorDto dto = new FieldErrorDto();
			copy(locale, error, dto);
			dto.setField(error.getField());
			dto.setRejectedValue(error.getRejectedValue());
			fieldErrors.add(dto);
		}
		
		ErrorsDto dto = new ErrorsDto();
		dto.setGlobalErrors(globalErrors);
		dto.setFieldErrors(fieldErrors);
		return dto;
	}

	private void copy(Locale locale, ObjectError error, ObjectErrorDto dto) {
		String message = source.getMessage(error, locale);
		if (message == null) {
			message = error.getDefaultMessage();
		}
		dto.setObjectName(error.getObjectName());
		dto.setMessage(message);
	}
}