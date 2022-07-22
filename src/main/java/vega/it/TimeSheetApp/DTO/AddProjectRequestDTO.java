package vega.it.TimeSheetApp.DTO;


public class AddProjectRequestDTO {

	private Integer id;
	
	private String description;
	
	private String projectName;
	
	private Integer clientId;
	
	private Integer teamMemberId;
	
	private Boolean finished;
	
	public AddProjectRequestDTO() {
		super();
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public AddProjectRequestDTO(Integer id, String description, String projectName, Integer clientId,
			Integer teamMemberId, Boolean finished) {
		super();
		this.id = id;
		this.description = description;
		this.projectName = projectName;
		this.clientId = clientId;
		this.teamMemberId = teamMemberId;
		this.finished = finished;
	}


	public AddProjectRequestDTO(String description, String projectName, Integer clientId, Integer teamMemberId,
			Boolean finished) {
		super();
		this.description = description;
		this.projectName = projectName;
		this.clientId = clientId;
		this.teamMemberId = teamMemberId;
		this.finished = finished;
	}

	public AddProjectRequestDTO(String description, String projectName, Integer clientId, Integer teamMemberId) {
		super();
		this.description = description;
		this.projectName = projectName;
		this.clientId = clientId;
		this.teamMemberId = teamMemberId;
	}

	@Override
	public String toString() {
		return "AddProjectRequestDTO [id=" + id + ", description=" + description + ", projectName=" + projectName
				+ ", clientId=" + clientId + ", teamMemberId=" + teamMemberId + ", finished=" + finished + "]";
	}
	
	
	
}
