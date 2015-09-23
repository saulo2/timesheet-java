package com.sauloaraujo.timesheet.web.project;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.project.ProjectSearchOptions;
import com.sauloaraujo.timesheet.domain.project.ProjectService;
import com.sauloaraujo.timesheet.domain.task.TaskService;
import com.sauloaraujo.timesheet.web.task.TaskResourceAssembler;

@RestController
@RequestMapping("/api/projects")
@ExposesResourceFor(Project.class)
public class ProjectController {
	private static final int FIRST_PAGE = 0;
	private static final int DEFAULT_SIZE = 2;

	private @Autowired ProjectService projectService;
	private @Autowired TaskService taskService;
	private @Autowired ProjectResourceAssembler projectAssembler;
	private @Autowired TaskResourceAssembler taskAssembler;
	
	@RequestMapping(method=RequestMethod.POST)
	public Object post(@RequestBody ProjectResource project) {
		projectService.save(projectAssembler.toEntity(project));
		return null;
	}	

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ProjectResource get(@PathVariable("id") int id) {
		return projectAssembler.toResource(projectService.findOne(id));
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public Object put(@PathVariable("id") int id, @RequestBody ProjectResource project) {
		projectService.save(projectAssembler.toEntity(id, project));
		return null;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public Object delete(@PathVariable("id") int id) {
		projectService.delete(id);
		return null;
	}	

	@RequestMapping(method=RequestMethod.GET, value="/search/options/form")
	public ResourceSupport getProjectSearchOptionsForm(
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="tasks", required=false) List<String> tasks) {
		ProjectSearchOptionsFormResource resource = new ProjectSearchOptionsFormResource();
		resource.get_embedded().setTasks(taskAssembler.toResources(taskService.findAll()));
		resource.add(linkTo(methodOn(ProjectController.class).getProjectSearchOptionsForm(name, description, tasks)).withSelfRel());
		resource.add(linkTo(methodOn(ProjectController.class).getProjectSearchResults(name, description, tasks, FIRST_PAGE, DEFAULT_SIZE)).withRel("results"));
		return resource;
	}

	@RequestMapping(method=RequestMethod.GET, value="/search/result")
	public ProjectSearchResultsResource getProjectSearchResults(
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="tasks", required=false) List<String> tasks,
			@RequestParam(value="page") int page,
			@RequestParam(value="size") int size) {
		ProjectSearchOptions options = new ProjectSearchOptions();
		options.setName(name);
		options.setDescription(description);
		options.setTasks(taskAssembler.getIds(tasks));		
		Pageable pageable = new PageRequest(page, size);
		Page<Project> result = projectService.find(options, pageable);
		ProjectSearchResultsResource resource = new ProjectSearchResultsResource();
		resource.get_embedded().setProjects(projectAssembler.toResources(result.getContent()));
		resource.add(linkTo(methodOn(ProjectController.class).getProjectSearchResults(name, description, tasks, page, size)).withSelfRel());
		for (int i = 0; i < result.getTotalPages(); ++i) {
			resource.add(linkTo(methodOn(ProjectController.class).getProjectSearchResults(name, description, tasks, i, size)).withRel(Integer.toString(i + 1)));	
		}
		return resource;
	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}/form")
	public ProjectFormResource getProjectForm(@PathVariable("id") String id) {
		ProjectFormResource resource = new ProjectFormResource();
		Project project;
		if ("new".equals(id)) {
			project = projectService.create();
			resource.add(linkTo(methodOn(ProjectController.class).post(null)).withRel("save"));
		} else {
			project = projectService.findOne(Integer.parseInt(id));
			resource.add(linkTo(methodOn(ProjectController.class).put(project.getId(), null)).withRel("save"));
			resource.add(linkTo(methodOn(ProjectController.class).delete(project.getId())).withRel("delete"));
		}		
		resource.get_embedded().setProject(projectAssembler.toResource(id, project));
		resource.get_embedded().setTasks(taskAssembler.toResources(taskService.findAll()));
		resource.add(linkTo(methodOn(ProjectController.class).getProjectForm(id)).withSelfRel());		
		return resource;
	}	
}