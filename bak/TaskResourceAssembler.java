package com.sauloaraujo.timesheet.web.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.task.Task;
import com.sauloaraujo.timesheet.domain.task.TaskService;

@Component
public class TaskResourceAssembler extends ResourceAssemblerSupport<Task, TaskResource> {
	private @Autowired EntityLinks links;
	private @Autowired TaskService service;

	public TaskResourceAssembler() {
		super(TaskController.class, TaskResource.class);
	}

	@Override
	public TaskResource toResource(Task entity) {
		TaskResource resource = new TaskResource();
		BeanUtils.copyProperties(entity, resource);
		resource.add(links.linkToSingleResource(entity));
		return resource;
	}
	
	public List<String> getUris(List<Task> tasks) {
		if (tasks == null) {
			return null;
		} else {
			List<String> uris = new ArrayList<>();
			for (Task task : tasks) {
				uris.add(getUri(task));
			}
			return uris;
		}		
	}

	public String getUri(Task task) {
		return links.linkToSingleResource(task).getHref();
	}	
	
	public List<Task> getTasks(List<String> uris) {
		return service.findAll(getIds(uris));
	}	
		
	public List<Integer> getIds(List<String> uris) {
		if (uris == null) {
			return null;
		} else {
			List<Integer> ids = new ArrayList<>();
			for (String uri : uris) {
				ids.add(getId(uri));
			}
			return ids;
		}
	}
	
	public Integer getId(String uri) {
		return new Integer(uri.substring(uri.lastIndexOf('/') + 1)); 
	}
}