package com.sauloaraujo.timesheet.domain.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sauloaraujo.timesheet.domain.DateFactory;
import com.sauloaraujo.timesheet.domain.task.Task;

@Service
public class ProjectService {
	private @Autowired ProjectRepositoryImpl repositoryCustom;
	private @Autowired ProjectRepository repository;	
	private @Autowired DateFactory dateFactory;

	public Project create() {
		Project project = new Project();
		project.setStartDate(dateFactory.today());
		project.setTasks(new ArrayList<Task>());
		return project;
	}

	public Project save(Project project) {
		return repository.save(project);
	}
	
	public void delete(Integer id) {
		repository.delete(id);
	}	

	public Project findById(int id) {
		return repository.findOne(id);
	}

	public Page<Project> find(ProjectSearchOptions options, Pageable pageable) {
		return repositoryCustom.find(options, pageable);
	}
}