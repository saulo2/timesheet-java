package com.sauloaraujo.timesheet.web.errors;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorsDto {
	private List<ObjectErrorDto> globalErrors;
	private List<FieldErrorDto> fieldErrors;
}