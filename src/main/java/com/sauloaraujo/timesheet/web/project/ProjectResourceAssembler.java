package com.sauloaraujo.timesheet.web.project;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.web.task.TaskResourceAssembler;

@Component
public class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {
	private @Autowired EntityLinks links;
	private @Autowired TaskResourceAssembler taskAssembler;
	
	public ProjectResourceAssembler() {
		super(ProjectController.class, ProjectResource.class);
	}

	@Override
	public ProjectResource toResource(Project entity) {
		return toResource(entity.getId().toString(), entity);
	}
	
	public ProjectResource toResource(String id, Project entity) {
		ProjectResource resource = new ProjectResource();
		BeanUtils.copyProperties(entity, resource);
		resource.setTasks(taskAssembler.getUris(entity.getTasks()));		
		resource.add(links.linkToSingleResource(entity));
		resource.add(linkTo(methodOn(ProjectController.class).getProjectForm(id)).withRel("form"));
		return resource;		
	}
	
	public Project toEntity(Integer id, ProjectResource resource) {
		Project entity = toEntity(resource);
		entity.setId(id);
		return entity;
	}
	
	public Project toEntity(ProjectResource resource) {
		Project entity = new Project();
		BeanUtils.copyProperties(resource, entity);
		entity.setTasks(taskAssembler.getTasks(resource.getTasks()));
		return entity;
	}	
}