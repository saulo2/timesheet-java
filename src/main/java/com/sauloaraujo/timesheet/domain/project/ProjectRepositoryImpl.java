package com.sauloaraujo.timesheet.domain.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.JPQLQuery;
import com.sauloaraujo.timesheet.domain.task.QTask;
import com.sauloaraujo.timesheet.domain.task.Task;

@Repository
public class ProjectRepositoryImpl extends QueryDslRepositorySupport implements ProjectRepositoryCustom {
	public ProjectRepositoryImpl() {
		super(Project.class);
	}

	@Override
	public Page<Project> find(ProjectSearchOptions options, Pageable pageable) {
		JPQLQuery query = from(QProject.project);				
		if (StringUtils.hasLength(options.getName())) {
			query.where(QProject.project.name.containsIgnoreCase(options.getName()));
		}		
		if (StringUtils.hasLength(options.getName())) {
			query.where(QProject.project.name.containsIgnoreCase(options.getDescription()));
		}		
		if (options.getTasks() != null) {
			List<Integer> ids = new ArrayList<>();
			for (Task task : options.getTasks()) {
				ids.add(task.getId());
			}
			query.where(
				new JPASubQuery()
					.from(QTask.task)
					.where(
						QTask.task.in(QProject.project.tasks),
						QTask.task.id.in(ids)
					).exists()
			);
		}		
		long total = query.count();
		query.orderBy(QProject.project.name.toLowerCase().asc());
		query.limit(pageable.getPageSize());
		query.offset(pageable.getOffset());
		return new PageImpl<Project>(query.list(QProject.project), pageable, total);
	}
}