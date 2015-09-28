package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.List;

import com.sauloaraujo.timesheet.domain.project.Project;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectRow {
	private Project project;
	private List<TaskRow> taskRows;
}