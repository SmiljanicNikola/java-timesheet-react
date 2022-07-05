package vega.it.TimeSheetApp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.repository.CountryRepository;
import vega.it.TimeSheetApp.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country findOne(Integer countryId) {
		return countryRepository.findById(countryId).orElse(null);
	}

	@Override
	public Country save(Country country) {
		countryRepository.save(country);
		return country;
	}

	@Override
	public void remove(Integer id) {
		countryRepository.deleteById(id);
		
	}

}
