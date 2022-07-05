package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.Country;

public interface CountryService {

	List<Country> findAll();
	
	Country findOne(Integer countryId);
	
	Country save (Country country);
	
	void remove(Integer id);
	
}
