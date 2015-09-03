package com.sauloaraujo.timesheet.rest.timesheet;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.sauloaraujo.timesheet.domain.timesheet.ProjectRow;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TimesheetResource extends ResourceSupport {
	private List<Date> dates;
	private List<ProjectRow> projectRows;
}