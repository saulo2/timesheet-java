package com.sauloaraujo.timesheet.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.project.ProjectRepository;
import com.sauloaraujo.timesheet.domain.task.Task;
import com.sauloaraujo.timesheet.domain.task.TaskRepository;
import com.sauloaraujo.timesheet.domain.user.User;
import com.sauloaraujo.timesheet.domain.user.UserRepository;

@Component
public class DatabasePopulator {
	private @Autowired ProjectRepository projectRepository;
	private @Autowired TaskRepository taskRepository;
	private @Autowired UserRepository userRepository;
	private @Autowired CalendarService calendarService;

	@Transactional
	@PostConstruct
	public void populateDatebase() {
		List<String> authorities = new ArrayList<>();
		authorities.add("USER");
		
		User user = new User();
		user.setUsername("s");
		user.setPassword("s");
		user.setEnabled(true);
		user.setAuthorities(authorities);
		userRepository.save(user);
		
		List<Task> tasks = new ArrayList<>();
		for (int i = 1; i <= 10; ++i) {
			Task task = new Task();
			task.setName("Task " + i);
			tasks.add(task);
		}
		taskRepository.save(tasks);

		Calendar calendar = calendarService.midnight();
		List<Project> projects = new ArrayList<>();
		for (int i = 1; i <= 10; ++i) {
			Project project = new Project();
			project.setName("Project " + i);
			project.setStartDate(calendar.getTime());
			project.setTasks(tasks);
			projects.add(project);
			calendar.add(Calendar.DATE, 1);
		}
		projectRepository.save(projects);
	}
}