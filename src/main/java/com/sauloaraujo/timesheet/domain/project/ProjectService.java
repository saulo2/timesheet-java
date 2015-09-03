package com.sauloaraujo.timesheet.domain.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sauloaraujo.timesheet.domain.DateService;
import com.sauloaraujo.timesheet.domain.task.Task;

@Service
public class ProjectService {
	private @Autowired ProjectRepositoryImpl repositoryCustom;
	private @Autowired ProjectRepository repository;	
	private @Autowired DateService dateService;

	public Project create() {
		Project project = new Project();
		project.setStartDate(dateService.midnight());
		project.setTasks(new ArrayList<Task>());
		return project;
	}

	public Project save(Project project) {
		return repository.save(project);
	}
	
	public void delete(Integer id) {
		repository.delete(id);
	}	

	public Project findOne(int id) {
		return repository.findOne(id);
	}

	public Page<Project> find(ProjectSearchOptions options, Pageable pageable) {
		return repositoryCustom.find(options, pageable);
	}
}