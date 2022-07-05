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
import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.service.CategoryService;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<Category> categories = categoryService.findAll();
		
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		for(Category c : categories) {
			categoriesDTO.add(new CategoryDTO(c));
		}
		
		return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Integer id){
		Category category = categoryService.findOne(id);
		if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);

	}

}
