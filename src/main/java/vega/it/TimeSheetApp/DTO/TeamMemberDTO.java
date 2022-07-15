package vega.it.TimeSheetApp.DTO;

import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;

public class TeamMemberDTO {

	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String password;
	
	private Integer hoursPerWeek;
	
	private String email;
	
	private boolean blocked;
	
	private Roles role;

	public TeamMemberDTO() {
		
	}
	
	public TeamMemberDTO(TeamMember teamMember) {
		this(teamMember.getId(), teamMember.getFirstname(), teamMember.getLastname(), teamMember.getUsername(), teamMember.getPassword(), teamMember.getHoursPerWeek(), teamMember.getEmail(), teamMember.isBlocked());
	}
	
	public TeamMemberDTO(Integer id, String firstname, String lastname, String username, String password,
			Integer hoursPerWeek, String email, boolean blocked) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.hoursPerWeek = hoursPerWeek;
		this.email = email;
		this.blocked = blocked;
	}
	
	



	public TeamMemberDTO(String firstname, String lastname, String username, String password, Integer hoursPerWeek,
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

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public TeamMemberDTO(String firstname, String lastname, String username, String password, Integer hoursPerWeek,
			String email, boolean blocked) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.hoursPerWeek = hoursPerWeek;
		this.email = email;
		this.blocked = blocked;
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
