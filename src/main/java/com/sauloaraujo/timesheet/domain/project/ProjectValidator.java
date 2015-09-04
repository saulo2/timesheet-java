package com.sauloaraujo.timesheet.domain.project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.Valid;

@Component
public class ProjectValidator implements ConstraintValidator<Valid, Project> {
	@Override
	public void initialize(Valid constraintAnnotation) {}

	@Override
	public boolean isValid(Project value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("O nome do projeto deve ser único")
			.addPropertyNode("name")
			.addConstraintViolation();
		return false;
	}
}