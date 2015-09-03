package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRow {
	private int id;
	private String task;
	private List<EntryCell> entryCells; 	
}