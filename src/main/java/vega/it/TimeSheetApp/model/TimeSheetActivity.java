package vega.it.TimeSheetApp.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="timesheet_activity")
public class TimeSheetActivity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="timesheet_activity_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="description", unique=false, nullable=false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="client_id", referencedColumnName="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="project_id", referencedColumnName="project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName="category_id")
	private Category category;
	
	@Column(name="time", unique=false, nullable=false)
	private Integer time;
	
	@Column(name="overtime", unique = false, nullable=false)
	private Integer overtime;
	
	@Column(name="date", unique = false)
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

	public TimeSheetActivity(Integer id, String description, Client client, Project project, Category category,
			Integer time, Integer overtime, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.client = client;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public TimeSheetActivity(String description, Client client, Project project, Category category, Integer time,
			Integer overtime, LocalDate date) {
		super();
		this.description = description;
		this.client = client;
		this.project = project;
		this.category = category;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}
	
	public TimeSheetActivity() {
		
	}

	@Override
	public String toString() {
		return "TimeSheetActivity [id=" + id + ", description=" + description + ", client=" + client + ", project="
				+ project + ", category=" + category + ", time=" + time + ", overtime=" + overtime + ", date=" + date
				+ "]";
	}
	
	

}
