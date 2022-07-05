package vega.it.TimeSheetApp.DTO;

import vega.it.TimeSheetApp.model.Country;

public class CountryDTO {
	
	
	private Integer id;
	
	private String name;
	
	public CountryDTO(Country country) {
		this(country.getId(), country.getName());
	}

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

	public CountryDTO() {
		super();
	}

	public CountryDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CountryDTO(String name) {
		super();
		this.name = name;
	}
	
	
	
}
