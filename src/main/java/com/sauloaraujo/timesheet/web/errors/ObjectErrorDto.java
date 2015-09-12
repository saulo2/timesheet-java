package com.sauloaraujo.timesheet.web.errors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ObjectErrorDto {
	private String objectName;
	private String message;
}