package com.sauloaraujo.timesheet.domain;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {
	private @Autowired CalendarService calendarService;
	
	public Date midnight() {
		return calendarService.midnight().getTime(); 
	}

	public Date midnight(Date date) {
		return calendarService.midnight(date).getTime();
	}
	
	public boolean sameDay(Date date1, Date date2) {
		return midnight(date1).equals(midnight(date2));
	}	
}