package com.mindtree.areamanagement.modules.genie.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.areamanagement.modules.user.entity.User;

public class GenieDto {

	private String name;
	private String severity;
	private String description;
	private Date date;
	private boolean status;
	@JsonIgnoreProperties("genie")
	@ManyToMany
	private List<User> users = new ArrayList<User>();

	public GenieDto() {
		super();
	}

	public GenieDto(String name, String severity, String description, Date date, boolean status, List<User> users) {
		super();
		this.name = name;
		this.severity = severity;
		this.description = description;
		this.date = date;
		this.status = status;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}