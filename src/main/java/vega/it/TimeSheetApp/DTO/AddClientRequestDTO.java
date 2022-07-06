package vega.it.TimeSheetApp.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import vega.it.TimeSheetApp.model.Country;

public class AddClientRequestDTO {
	
	private Integer id;
	
	private String clientName;
	
	private String address;
	
	private String city;
	
	private String zipCode;
	
	private Integer countryId;
	
	public AddClientRequestDTO() {
		
	}

	public AddClientRequestDTO(Integer id, String clientName, String address, String city, String zipCode,
			Integer countryId) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.countryId = countryId;
	}
	
	

	public AddClientRequestDTO(String clientName, String address, String city, String zipCode, Integer countryId) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.countryId = countryId;
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
	
	
}
