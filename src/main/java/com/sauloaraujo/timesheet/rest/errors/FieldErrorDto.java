package com.sauloaraujo.timesheet.rest.errors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FieldErrorDto extends ObjectErrorDto {
	private String field;
	private Object rejectedValue;
}