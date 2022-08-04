package vega.it.TimeSheetApp.model;

import java.time.LocalDate;

public class SearchObject {

	private Integer projectId;
	
	private Integer teamMemberId;
	
	private Integer categoryId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public SearchObject() {
		super();
	}

	public SearchObject(Integer projectId, Integer teamMemberId, Integer categoryId, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.projectId = projectId;
		this.teamMemberId = teamMemberId;
		this.categoryId = categoryId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}
