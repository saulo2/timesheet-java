package com.sauloaraujo.timesheet.rest.task;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sauloaraujo.timesheet.domain.task.Task;

@RestController
@RequestMapping("/api/users/me/tasks")
@ExposesResourceFor(Task.class)
public class TaskController {
}