package com.sauloaraujo.timesheet.web.timesheet;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sauloaraujo.timesheet.domain.DateService;
import com.sauloaraujo.timesheet.domain.timesheet.Timesheet;
import com.sauloaraujo.timesheet.domain.timesheet.TimesheetService;

import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {
	private @Autowired DateService dateService;
	private @Autowired TimesheetService timesheetService;
	private @Autowired MapperFacade mapperFacade;

	@RequestMapping(method=RequestMethod.GET)
	public TimesheetResource get(@RequestParam(value="start", required=false) @DateTimeFormat(iso=ISO.DATE) Date start, 
								 @RequestParam(value="days", required=false) Integer days) {
		if (start == null) {
			start = dateService.midnight();
		}
		if (days == null) {
			days = 7;
		}
		Timesheet timesheet = timesheetService.get(start, days);
		TimesheetResource resource = mapperFacade.map(timesheet, TimesheetResource.class);
		return resource; 
	}

	@RequestMapping(method=RequestMethod.PATCH, value="/{start}")
	public Object patch(@PathVariable("start") @DateTimeFormat(iso=ISO.DATE) Date start, 
						@RequestBody TimesheetResource resource) {
		Timesheet timesheet = mapperFacade.map(resource, Timesheet.class);
		timesheetService.patch(start, timesheet);
		return null;
	}
}