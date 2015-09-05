package com.sauloaraujo.timesheet.domain.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	private @Autowired TaskRepository repository;
	
	public List<Task> findAll() {
		return repository.findAll(); 
	}

	public List<Task> findAll(Iterable<Integer> ids) {
		return repository.findAll(ids); 
	}
}