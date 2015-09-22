package com.sauloaraujo.timesheet.web.configuration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.projection.ProjectionDefinitions;
import org.springframework.data.rest.core.projection.ProjectionFactory;
import org.springframework.data.rest.core.projection.ProxyProjectionFactory;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.support.PersistentEntityProjector;
import org.springframework.data.rest.webmvc.support.Projector;
import org.springframework.hateoas.EntityLinks;

@Configuration
public class Test {
	private @Autowired RepositoryRestConfiguration repositoryRestConfiguration;
	private @Autowired Repositories repositories;
	private @Autowired EntityLinks entityLinks;	
	private @Autowired BeanFactory beanFactory;
	private @Autowired ResourceMappings mappings;
	
	@Bean
	public PersistentEntityResourceAssembler assembler() {
		ProjectionDefinitions projectionDefinitions = repositoryRestConfiguration.projectionConfiguration();
		ProjectionFactory factory = new ProxyProjectionFactory(beanFactory);
		String projection = projectionDefinitions.getParameterName();
		Projector projector = new PersistentEntityProjector(projectionDefinitions, factory, projection, mappings);
		return new PersistentEntityResourceAssembler(repositories, entityLinks, projector, mappings);
	}
}