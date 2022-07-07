package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.DTO.CategoryDTO;
import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;

public interface CategoryService {

	List<Category> findAll();
	
	Category findById(Integer categoryId);
	
	Category save (Category category);
	
	void remove(Integer id);
	
	Page<Category> findAll(Pageable pageable);

	
}
