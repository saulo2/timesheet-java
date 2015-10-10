package com.sauloaraujo.timesheet.domain.user;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
	name="USERS", 
	uniqueConstraints=@UniqueConstraint(columnNames="username")
)
@Setter
@Getter
public class User {
	@Id
	@Column(name="username")
	@Size(max=50)
	private String username;

	public String getId() {
		return username;
	}
	
	@NotNull
	@Size(max=50)
	private String password;
	
	@NotNull
	private boolean enabled;
	
	@ElementCollection
	@CollectionTable(
		name="authorities", 
		joinColumns=@JoinColumn(name="username"),
		uniqueConstraints=@UniqueConstraint(columnNames={"username", "authority"})
	)
	@Column(name="authority", length=50)
	private List<String> authorities;
}