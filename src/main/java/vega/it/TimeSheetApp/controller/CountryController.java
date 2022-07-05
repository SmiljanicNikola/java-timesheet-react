package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.CategoryDTO;
import vega.it.TimeSheetApp.DTO.CountryDTO;
import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.service.CategoryService;
import vega.it.TimeSheetApp.service.CountryService;

@RestController
@RequestMapping(value = "api/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public ResponseEntity<List<CountryDTO>> getCountries(){
		List<Country> countries = countryService.findAll();
		
		List<CountryDTO> countriesDTO = new ArrayList<>();
		for(Country c : countries) {
			countriesDTO.add(new CountryDTO(c));
		}
		
		return new ResponseEntity<>(countriesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CountryDTO> getCountryById(@PathVariable("id") Integer id){
		Country country = countryService.findOne(id);
		if(country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new CountryDTO(country), HttpStatus.OK);

	}
	
}
