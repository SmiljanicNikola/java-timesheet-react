package vega.it.TimeSheetApp.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import vega.it.TimeSheetApp.model.Client;

public class ClientDTO {

	
	private Integer id;
	
	private String clientName;
	
	private String address;
	
	private String city;
	
	private String zipCode;

	public ClientDTO(Client client) {
		this(client.getId(), client.getClientName(), client.getAddress(), client.getCity(), client.getZipCode());
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

	public ClientDTO(String clientName, String address, String city, String zipCode) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}

	public ClientDTO(Integer id, String clientName, String address, String city, String zipCode) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	
	
	
}
