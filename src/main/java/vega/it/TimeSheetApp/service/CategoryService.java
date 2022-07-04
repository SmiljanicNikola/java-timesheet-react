package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;

public interface CategoryService {

	List<Category> findAll();
	
	Category findOne(Integer categoryId);
	
	Category save (Category category);
	
	void remove(Integer id);
	
}
