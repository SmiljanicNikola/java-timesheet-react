package vega.it.TimeSheetApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
	
	

}
