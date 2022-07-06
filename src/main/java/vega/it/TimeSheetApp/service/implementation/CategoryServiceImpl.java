package vega.it.TimeSheetApp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.repository.CategoryRepository;
import vega.it.TimeSheetApp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Integer categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public Category save(Category category) {
		categoryRepository.save(category);
		return category;
	}

	@Override
	public void remove(Integer id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

}
