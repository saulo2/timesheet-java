package com.sauloaraujo.timesheet.web.task;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.task.Task;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

@Component
public class TaskResourceConverter extends BidirectionalConverter<Task, TaskResource> {
	private @Autowired MapperFacade mapperFacade;
	
	@Override
	public TaskResource convertTo(Task source, Type<TaskResource> destinationType) {
		TaskResource resource = new TaskResource();
		mapperFacade.map(source, resource);
		resource.add(linkTo(methodOn(TaskController.class).get(source.getId())).withSelfRel());
		return resource;
	}

	@Override
	public Task convertFrom(TaskResource source, Type<Task> destinationType) {
		throw new UnsupportedOperationException();
	}
}