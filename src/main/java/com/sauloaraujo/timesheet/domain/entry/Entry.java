package com.sauloaraujo.timesheet.domain.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.sauloaraujo.timesheet.domain.project.Project;
import com.sauloaraujo.timesheet.domain.task.Task;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
	name="ENT_ENTRY", 
	uniqueConstraints=@UniqueConstraint(columnNames={"PRO_ID", "TAS_ID", "ENT_DT"})
)
@Setter
@Getter
public class Entry {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ENT_ID")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	@NotNull
	private Date date;
	
	@Column(name="ENT_TIME")
	@NotNull
	@Min(0)
	private double time;
	
	@ManyToOne
	@JoinColumn(name="PRO_ID")
	@NotNull
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="TAS_ID")
	@NotNull
	private Task task; 	
}