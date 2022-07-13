package vega.it.TimeSheetApp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Report {
	
	private Integer id;
	
	private String description;
	
	private TeamMember teamMember;
	
	private Client client;
	
	private Project project;
	
	private Category category;
	
	private Integer time;
	
	private Integer overtime;
	
	private LocalDate date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getOvertime() {
		return overtime;
	}

	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Report(String description, TeamMember teamMember, Client client, Project project, Category category,
			Integer time, Integer overtime, LocalDate date) {
		super();
		this.description = description;
		this.teamMember = teamMember;
		this.client = client;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public Report(Integer id, String description, TeamMember teamMember, Client client, Project project,
			Category category, Integer time, Integer overtime, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.teamMember = teamMember;
		this.client = client;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", description=" + description + ", teamMember=" + teamMember + ", client=" + client
				+ ", project=" + project + ", category=" + category + ", time=" + time + ", overtime=" + overtime
				+ ", date=" + date + "]";
	}
	
	

}
