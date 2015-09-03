package com.sauloaraujo.timesheet.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.project.ProjectRepository;
import com.sauloaraujo.timesheet.domain.task.Task;
import com.sauloaraujo.timesheet.domain.task.TaskRepository;

@Component
public class DatabasePopulator {
	private @Autowired ProjectRepository projectRepository;
	private @Autowired TaskRepository taskRepository;
	private @Autowired DateService dateService;
	
	@Transactional
	@PostConstruct
	public void populateDatebase() {
		Date today = dateService.midnight();

		List<Task> tasks = new ArrayList<>();
		for (int i = 1; i <= 10; ++i) {
			Task task = new Task();
			task.setName("Task " + i);
			tasks.add(task);
		}
		taskRepository.save(tasks);

		List<Project> projects = new ArrayList<>();
		for (int i = 1; i <= 10; ++i) {
			Project project = new Project();
			project.setName("Project " + i);
			project.setStartDate(today);
			project.setTasks(tasks);
			projects.add(project);
		}
		projectRepository.save(projects);
	}
}