package com.sauloaraujo.timesheet.web;

import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.timesheet.ProjectRow;
import com.sauloaraujo.timesheet.domain.timesheet.TaskRow;
import com.sauloaraujo.timesheet.web.timesheet.TimesheetResource.ProjectRowDto;
import com.sauloaraujo.timesheet.web.timesheet.TimesheetResource.ProjectRowDto.TaskRowDto;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class TimesheetConfigurableMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(ProjectRow.class, ProjectRowDto.class)
			.field("project.id", "id")
			.field("project.name", "project")
			.byDefault()
		.register();
		
		factory.classMap(TaskRow.class, TaskRowDto.class)
			.field("task.id", "id")
			.field("task.name", "task")
			.byDefault()
		.register();
	}
}