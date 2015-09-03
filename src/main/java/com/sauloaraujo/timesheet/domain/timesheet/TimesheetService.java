package com.sauloaraujo.timesheet.domain.timesheet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sauloaraujo.timesheet.domain.CalendarService;
import com.sauloaraujo.timesheet.domain.DateService;
import com.sauloaraujo.timesheet.domain.entry.Entry;
import com.sauloaraujo.timesheet.domain.entry.EntryRepository;
import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.project.ProjectRepository;
import com.sauloaraujo.timesheet.domain.task.Task;

@Service
public class TimesheetService {
	private @Autowired ProjectRepository projectRepository;
	private @Autowired EntryRepository entryRepository;
	private @Autowired CalendarService calendarService;
	private @Autowired DateService dateService;
	
	public Timesheet get(Date start, int days) {
		List<Date> dates = new ArrayList<>();
		Calendar calendar = calendarService.midnight(start);
		for (int i = 0; i < days; ++i) {
			dates.add(calendar.getTime());
			calendar.add(Calendar.DATE, 1);			
		}		
		start = dates.get(0);
		Date end = dates.get(dates.size() - 1);

		List<Entry> entries = entryRepository.findByDateBetween(start, end);

		List<ProjectRow> projectRows = new ArrayList<>();
		for (Project project : projectRepository.findByStartDateLessThanEqual(end)) {
			List<TaskRow> taskRows = new ArrayList<>();
			for (Task task : project.getTasks()) {
				List<EntryCell> entryCells = new ArrayList<>();				
				for (int id = 0; id < dates.size(); ++id) {
					Double time = null;
					Date date = dates.get(id);					
					for (Entry entry : entries) {
						if (entry.getProject().getId().equals(project.getId())
								&& entry.getTask().getId().equals(task.getId())
								&& dateService.midnight(entry.getDate()).equals(date)) {
							time = entry.getTime();
						}
					}

					EntryCell entryCell = new EntryCell();
					entryCell.setId(id);
					entryCell.setTime(time);
					entryCell.setEnabled(dateService.midnight(project.getStartDate()).compareTo(date) <= 0);
					entryCells.add(entryCell);
				}

				TaskRow taskRow = new TaskRow();
				taskRow.setId(task.getId());
				taskRow.setTask(task.getName());
				taskRow.setEntryCells(entryCells);
				taskRows.add(taskRow);
			}
			
			ProjectRow projectRow = new ProjectRow();
			projectRow.setId(project.getId());
			projectRow.setProject(project.getName());
			projectRow.setTaskRows(taskRows);
			projectRows.add(projectRow);
		}

		Timesheet timesheet = new Timesheet();
		timesheet.setDates(dates);
		timesheet.setProjectRows(projectRows);
		return timesheet;
	}
}