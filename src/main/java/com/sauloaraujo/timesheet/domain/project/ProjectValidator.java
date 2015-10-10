package com.sauloaraujo.timesheet.domain.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProjectValidator implements Validator {
	private @Autowired ProjectRepository repository;
	private @Autowired Validator validator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);

		Project project = (Project) target;
		String name = project.getName();
		if (name != null) {
			Project databaseProject = repository.findByNameIgnoreCase(name);
			if (databaseProject != null && databaseProject.getId() != project.getId()) {
				errors.rejectValue("name", "unique.name.project", new String[] {name}, "A project with this name already exists");
			}
		}
	}
}