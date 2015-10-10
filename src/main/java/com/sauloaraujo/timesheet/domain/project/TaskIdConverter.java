package com.sauloaraujo.timesheet.domain.project;

import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.task.Task;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

@Component
public class TaskIdConverter extends CustomConverter<Task, Integer> {
	@Override
	public Integer convert(Task source, Type<? extends Integer> destinationType) {
		return source.getId();
	}
}