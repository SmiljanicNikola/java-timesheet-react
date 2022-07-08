package vega.it.TimeSheetApp.DTO;

import java.time.LocalDate;

import vega.it.TimeSheetApp.model.TimeSheetActivity;


public class TimeSheetActivityDTO {
	
	
	private Integer id;
	
	private String description;
	
	private TeamMemberDTO teamMember;
	
	private ProjectDTO project;
	
	private CategoryDTO category;
	
	private Integer time;
	
	private Integer overtime;
	
	private LocalDate date;
	
	public TimeSheetActivityDTO() {
		
	}
	
	public TimeSheetActivityDTO(TimeSheetActivity timeSheetActivity) {
		this(timeSheetActivity.getId(), timeSheetActivity.getDescription(), new TeamMemberDTO(timeSheetActivity.getTeamMember()), new ProjectDTO(timeSheetActivity.getProject()), new CategoryDTO(timeSheetActivity.getCategory()), timeSheetActivity.getTime(), timeSheetActivity.getOvertime(), timeSheetActivity.getDate());
	}

	



	public TimeSheetActivityDTO(Integer id, String description, TeamMemberDTO teamMember, ProjectDTO project,
			CategoryDTO category, Integer time, Integer overtime, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.teamMember = teamMember;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public TimeSheetActivityDTO(String description, TeamMemberDTO teamMember, ProjectDTO project, CategoryDTO category,
			Integer time, Integer overtime, LocalDate date) {
		super();
		this.description = description;
		this.teamMember = teamMember;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

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

	

	public TeamMemberDTO getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMemberDTO teamMember) {
		this.teamMember = teamMember;
	}

	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
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
	
	


}
