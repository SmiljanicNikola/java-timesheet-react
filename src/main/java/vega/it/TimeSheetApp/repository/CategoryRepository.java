package vega.it.TimeSheetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
