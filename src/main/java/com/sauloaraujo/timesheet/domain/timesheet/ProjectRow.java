package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectRow {
	private int id;
	private String project;
	private List<TaskRow> taskRows;
}