package com.sauloaraujo.timesheet.web.timesheet;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sauloaraujo.timesheet.domain.CalendarService;
import com.sauloaraujo.timesheet.domain.DateService;
import com.sauloaraujo.timesheet.domain.timesheet.Timesheet;
import com.sauloaraujo.timesheet.domain.timesheet.TimesheetService;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {
	private @Autowired DateService dateService;
	private @Autowired CalendarService calendarService;
	private @Autowired TimesheetService timesheetService;
	
	@RequestMapping(method=RequestMethod.GET, value="/today")
	public TimesheetResource get(@RequestParam(value="days") int days) {
		return get(dateService.midnight(), days);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{start}")
	public TimesheetResource get(@PathVariable("start") @DateTimeFormat(iso=ISO.DATE) Date start, @RequestParam(value="days") int days) {
		Timesheet timesheet = timesheetService.get(start, days); 
		TimesheetResource resource = new TimesheetResource();
		BeanUtils.copyProperties(timesheet, resource);
		
		resource.add(linkTo(methodOn(TimesheetController.class).get(start, days)).withSelfRel());		
		
		Calendar calendar = calendarService.midnight(start);
		calendar.add(Calendar.DATE, -days);
		resource.add(linkTo(methodOn(TimesheetController.class).get(calendar.getTime(), days)).withRel("previous"));
		
		calendar = calendarService.midnight(start);
		calendar.add(Calendar.DATE, days);
		resource.add(linkTo(methodOn(TimesheetController.class).get(calendar.getTime(), days)).withRel("next"));
		
		if (days > 1) {
			resource.add(linkTo(methodOn(TimesheetController.class).get(start, days - 1)).withRel("minus"));	
		}
		
		resource.add(linkTo(methodOn(TimesheetController.class).get(start, days + 1)).withRel("plus"));

		return resource; 
	}

	@RequestMapping(method=RequestMethod.PATCH, value="/{start}")
	public void patch(@PathVariable("start") @DateTimeFormat(iso=ISO.DATE) Date start, @RequestBody Timesheet timesheet) {
		timesheetService.patch(start, timesheet);
	}
}