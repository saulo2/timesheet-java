package com.sauloaraujo.timesheet.rest.project;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectSearchResultsResource extends ResourceSupport {
	@Setter
	@Getter	
	public static class Embedded {
		private List<ProjectResource> projects;
	}

	private Embedded _embedded = new Embedded();	
}