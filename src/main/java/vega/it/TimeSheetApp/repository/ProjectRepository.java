package vega.it.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query(value = "SELECT * from project where project.deleted = false",nativeQuery = true )
	Page<Project> findAllProjectsWithPagination(Pageable pageable);
	
	@Query(value = "SELECT * from project where project.project_name LIKE :letter%",nativeQuery = true )
	List<Project> filterAllProjectsByFirstLetter(String letter);

}
