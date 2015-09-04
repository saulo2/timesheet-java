package com.sauloaraujo.timesheet.domain.task;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.Valid;

@Component
public class TaskValidator implements ConstraintValidator<Valid, Task> {
	@Override
	public void initialize(Valid constraintAnnotation) {}

	@Override
	public boolean isValid(Task value, ConstraintValidatorContext context) {
		return true;
	}
}