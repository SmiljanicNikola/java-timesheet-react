package vega.it.TimeSheetApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="description", unique=false, nullable=false)
	private String description;
	
	@Column(name="project_name", unique=false, nullable=false)
	private String projectName;
	
	@ManyToOne
	@JoinColumn(name="client_id", referencedColumnName="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="team_member_id", referencedColumnName="team_member_id")
	private TeamMember lead;
	
	@Column(name="finished", unique=false, nullable=false)
	private Boolean finished;
	
	@JsonIgnore
	@OneToMany(mappedBy="project")
	private List<TimeSheetActivity> timeSheetActivities;
	
	@Column(name="deleted", unique=false, nullable=false)
	private Boolean deleted;


	public Project() {
		super();
	}

	
	public Boolean getDeleted() {
		return deleted;
	}



	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



	public List<TimeSheetActivity> getTimeSheetActivities() {
		return timeSheetActivities;
	}



	public void setTimeSheetActivities(List<TimeSheetActivity> timeSheetActivities) {
		this.timeSheetActivities = timeSheetActivities;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TeamMember getLead() {
		return lead;
	}

	public void setLead(TeamMember lead) {
		this.lead = lead;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Project(Integer id, String description, String projectName, Client client, TeamMember lead,
			Boolean finished) {
		super();
		this.id = id;
		this.description = description;
		this.projectName = projectName;
		this.client = client;
		this.lead = lead;
		this.finished = finished;
	}

	public Project(String description, String projectName, Client client, TeamMember lead, Boolean finished) {
		super();
		this.description = description;
		this.projectName = projectName;
		this.client = client;
		this.lead = lead;
		this.finished = finished;
	}
	
	
	
}
