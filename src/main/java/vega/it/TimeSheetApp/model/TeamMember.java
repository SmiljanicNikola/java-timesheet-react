package vega.it.TimeSheetApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vega.it.TimeSheetApp.exceptions.ResourceNotFoundException;

@Entity
@Table(name="team_members")
public class TeamMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="team_member_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="firstname", unique=false, nullable=false)
	private String firstname;
	
	@Column(name="lastname", unique=false, nullable=false)
	private String lastname;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="hours_per_week", unique=false, nullable=false)
	private Integer hoursPerWeek;
	
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	@Column(name="blocked", unique=false, nullable=false)
	private boolean blocked;
	
	@Column(name="deleted", unique=false, nullable=false)
	private Boolean deleted;

	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@JsonIgnore
	@OneToMany(mappedBy="lead")
	private List<Project> projects;
	
	public TeamMember() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="teamMember")
	private List<TimeSheetActivity> timeSheetActivities;

	public TeamMember(String firstname, String lastname, String username, String password, Integer hoursPerWeek,
			String email, boolean blocked, Roles role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.hoursPerWeek = hoursPerWeek;
		this.email = email;
		this.blocked = blocked;
		this.role = role;
	}
	
	

	public TeamMember(String firstname, String lastname, String username, String password, Integer hoursPerWeek,
			String email, boolean blocked, Boolean deleted) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.hoursPerWeek = hoursPerWeek;
		this.email = email;
		this.blocked = blocked;
		this.deleted = deleted;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public TeamMember(Integer id, String firstname, String lastname, String username, String password,
			Integer hoursPerWeek, String email, boolean blocked, Roles role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.hoursPerWeek = hoursPerWeek;
		this.email = email;
		this.blocked = blocked;
		this.role = role;
	}
	
	

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<TimeSheetActivity> getTimeSheetActivities() {
		return timeSheetActivities;
	}

	public void setTimeSheetActivities(List<TimeSheetActivity> timeSheetActivities) {
		this.timeSheetActivities = timeSheetActivities;
	}

	@Override
	public String toString() {
		return "TeamMember [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", hoursPerWeek=" + hoursPerWeek + ", email=" + email + ", blocked="
				+ blocked + ", role=" + role + "]";
	}

	/*public TeamMember orElseThrow(Object object) {
		return orElseThrow(new ResourceNotFoundException("User not found with id :"));
	}	*/

}
