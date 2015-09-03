package com.sauloaraujo.timesheet.domain.timesheet;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryCell {
	private int id;
	private Double time;
	private boolean disabled;
}