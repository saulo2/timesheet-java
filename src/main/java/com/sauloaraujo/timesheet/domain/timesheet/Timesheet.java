package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Timesheet {
	private List<Date> dates;
	private List<ProjectRow> projectRows;
}