package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.List;

import com.sauloaraujo.timesheet.domain.task.Task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRow {
	private Task task;
	private List<EntryCell> entryCells; 	
}