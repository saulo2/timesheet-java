package com.sauloaraujo.timesheet.domain.project;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectSearchOptions {
	private String name;
	private String description;
	private List<Integer> tasks;
}