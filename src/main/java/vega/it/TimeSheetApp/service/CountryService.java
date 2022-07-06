package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Country;

public interface CountryService {

	List<Country> findAll();
	
	Country findById(Integer countryId);
	
	Country save (Country country);
	
	void remove(Integer id);
		
}
