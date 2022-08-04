package vega.it.TimeSheetApp.DTO;

import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;

public class ProjectDTO {

	private Integer id;
	
	private String description;
	
	private String projectName;
	
	private ClientDTO client;
	
	private TeamMemberDTO lead;
	
	private Boolean finished;
	
	public ProjectDTO() {
		
	}
	
	public ProjectDTO(Project project) {
		this(project.getId(), project.getDescription(), project.getProjectName(), new ClientDTO(project.getClient()), new TeamMemberDTO(project.getLead()), project.getFinished());
	}
	
	

	public ProjectDTO(String description, String projectName, ClientDTO client, TeamMemberDTO lead, Boolean finished) {
		super();
		this.description = description;
		this.projectName = projectName;
		this.client = client;
		this.lead = lead;
		this.finished = finished;
	}

	public ProjectDTO(Integer id, String description, String projectName, ClientDTO client, TeamMemberDTO lead,
			Boolean finished) {
		super();
		this.id = id;
		this.description = description;
		this.projectName = projectName;
		this.client = client;
		this.lead = lead;
		this.finished = finished;
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

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public TeamMemberDTO getLead() {
		return lead;
	}

	public void setLead(TeamMemberDTO lead) {
		this.lead = lead;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	
	
	
}
