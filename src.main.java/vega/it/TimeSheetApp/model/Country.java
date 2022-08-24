package vega.it.TimeSheetApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="country_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique = false, nullable=false)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="country")
	private List<Client> clients;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Country() {
		super();
	}

	public Country(Integer id, String name, List<Client> clients) {
		super();
		this.id = id;
		this.name = name;
		this.clients = clients;
	}
	
	

	public Country(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", clients=" + clients + "]";
	}
	
	

	
	
	
}
