package com.sauloaraujo.timesheet.domain.timesheet;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryCell {
	private int column;
	private Double time;
	private boolean disabled;
}