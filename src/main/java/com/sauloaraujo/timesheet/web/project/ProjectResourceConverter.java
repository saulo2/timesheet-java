package com.sauloaraujo.timesheet.web.project;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.project.ProjectService;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

@Component
public class ProjectResourceConverter extends BidirectionalConverter<Project, ProjectResource> {
	private @Autowired ProjectService projectService;
	private @Autowired MapperFacade mapperFacade;

	@Override
	public ProjectResource convertTo(Project source, Type<ProjectResource> destinationType) {
		ProjectResource resource = new ProjectResource();
		mapperFacade.map(source, resource);
		resource.add(linkTo(methodOn(ProjectController.class).get(source.getId())).withSelfRel());
		resource.add(linkTo(methodOn(ProjectController.class).getProjectForm(Integer.toString(source.getId()))).withRel("form"));
		return resource;
	}

	@Override
	public Project convertFrom(ProjectResource source, Type<Project> destinationType) {
		Project project = projectService.findOne(source.getId());
		mapperFacade.map(source, project);
		return project;
	}
}