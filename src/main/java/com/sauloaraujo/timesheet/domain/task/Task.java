package com.sauloaraujo.timesheet.domain.task;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.Identifiable;

import com.sauloaraujo.timesheet.domain.project.Project;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TAS_TASK")
@Setter
@Getter
public class Task implements Identifiable<Integer> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TAS_ID")
	private Integer id;
	
	@Column(name="TAS_NM")
	@NotNull
	@Size(min=1, max=100)
	private String name;

	@Column(name="TAS_DS")
	private String description;	
	
	@ManyToMany(mappedBy="tasks")
	private List<Project> projects;		
}