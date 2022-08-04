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
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="client_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="client_name", unique=false, nullable=false)
	private String clientName;
	
	@Column(name="address", unique=false, nullable=false)
	private String address;
	
	@Column(name="city", unique=false, nullable=false)
	private String city;
	
	@Column(name="zip_code", unique=false, nullable=false)
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name="country_id", referencedColumnName="country_id")
	private Country country;
	
	@Column(name="deleted", unique=false, nullable=false)
	private Boolean deleted;

	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Project> projects;
	
	/*@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<TimeSheetActivity> timeSheetActivities;*/

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Client(String clientName, String address, String city, String zipCode, Country country, Boolean deleted) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.deleted = deleted;
	}

	public Client(String clientName, String address, String city, String zipCode) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Client(String clientName, String address, String city, String zipCode, Country country) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	public Client(Integer id, String clientName, String address, String city, String zipCode, Country country,
			List<Project> projects) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.projects = projects;
	}
	
	

	public Client() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientName=" + clientName + ", address=" + address + ", city=" + city
				+ ", zipCode=" + zipCode + ", country=" + country + ", projects=" + projects + "]";
	}

	/*public List<TimeSheetActivity> getTimeSheetActivities() {
		return timeSheetActivities;
	}

	public void setTimeSheetActivities(List<TimeSheetActivity> timeSheetActivities) {
		this.timeSheetActivities = timeSheetActivities;
	}*/
	
	
	
	
	
	
	
	

}
