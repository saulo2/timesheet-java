package com.sauloaraujo.timesheet.domain;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	public Calendar midnight() {
		Calendar calendar = Calendar.getInstance();
		midnight(calendar);
		return calendar;
	}
	
	public void midnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}		

	public Calendar midnight(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		midnight(calendar);
		return calendar;		
	}	
}