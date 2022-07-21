package vega.it.TimeSheetApp.DTO;

import java.time.LocalDate;

public class AddActivityDTORequest {

private Integer id;
	
	private String description;
	
	private Integer teamMemberId;
	
	private Integer projectId;
	
	private Integer categoryId;
	
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

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public AddActivityDTORequest(Integer id, String description, Integer teamMemberId, Integer projectId,
			Integer categoryId, Integer time, Integer overtime, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.teamMemberId = teamMemberId;
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public AddActivityDTORequest(String description, Integer teamMemberId, Integer projectId, Integer categoryId,
			Integer time, Integer overtime, LocalDate date) {
		super();
		this.description = description;
		this.teamMemberId = teamMemberId;
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public AddActivityDTORequest() {
		super();
	}
	
	
	
}
