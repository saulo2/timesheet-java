package com.sauloaraujo.timesheet.web.task;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskResource extends ResourceSupport {
	private String name;
}