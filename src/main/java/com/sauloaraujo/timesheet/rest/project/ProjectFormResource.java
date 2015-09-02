package com.sauloaraujo.timesheet.rest.project;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.sauloaraujo.timesheet.rest.task.TaskResource;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectFormResource extends ResourceSupport {
	@Setter
	@Getter	
	public static class Embedded {
		private ProjectResource project;
		private List<TaskResource> tasks;
	}
	
	private Embedded _embedded = new Embedded();
}