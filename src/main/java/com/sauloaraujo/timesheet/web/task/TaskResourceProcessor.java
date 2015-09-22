package com.sauloaraujo.timesheet.web.task;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.task.Task;

@Component
public class TaskResourceProcessor implements ResourceProcessor<Resource<Task>>{
	@Override
	public Resource<Task> process(Resource<Task> resource) {
		resource.add(new Link("teste", "teste"));
		return resource;
	}
}