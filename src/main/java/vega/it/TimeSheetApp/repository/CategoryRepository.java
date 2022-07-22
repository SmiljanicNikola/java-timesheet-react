package vega.it.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vega.it.TimeSheetApp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "SELECT * from category where category.type LIKE :letter%",nativeQuery = true )
	List<Category> filterAllCategoriesByFirstLetter(String letter);
	
	
}
