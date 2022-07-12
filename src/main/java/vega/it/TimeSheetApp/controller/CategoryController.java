package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.AddClientRequestDTO;
import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.CategoryDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.service.CategoryService;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/paginate")
	public ResponseEntity<Page<Category>> findAll(Pageable pageable){
		return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		
		List<CategoryDTO> categoriesDTO = categoryService
				.findAll()
				.stream()
				.map(c -> new CategoryDTO(c))
				.toList();
		
		return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Integer id){
		Category category = categoryService.findById(id);
		if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		Category category = categoryService.findById(id);
        if (category == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
	
	@PostMapping()
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setType(categoryDTO.getType());       

        category = categoryService.save(category);
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.CREATED);
	        	
	 }
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id) {
	
        Category category = categoryService.findById(id);

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        category.setType(categoryDTO.getType());

        category = categoryService.save(category);

        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
    }

}
