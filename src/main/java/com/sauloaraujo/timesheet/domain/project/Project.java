package com.sauloaraujo.timesheet.domain.project;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.hateoas.Identifiable;

import com.sauloaraujo.timesheet.domain.task.Task;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRO_PROJECT")
@Setter
@Getter
public class Project implements Identifiable<Integer> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRO_ID")
	private Integer id;
	
	@Column(name="PRO_NM")
	private String name;

	@Column(name="PRO_DS")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PRO_START_DATE")	
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PRO_END_DATE")
	private Date endDate;

	@ManyToMany
	@JoinTable(
		name="PRT_PROJECT_TASK", 
		joinColumns=@JoinColumn(name="PRO_ID"),
		inverseJoinColumns=@JoinColumn(name="TAS_ID"),
		uniqueConstraints=@UniqueConstraint(columnNames={"PRO_ID", "TAS_ID"})
	)	
	private List<Task> tasks;
}