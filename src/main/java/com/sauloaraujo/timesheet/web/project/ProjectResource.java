package com.sauloaraujo.timesheet.web.project;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectResource extends ResourceSupport {
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private List<String> tasks;
}