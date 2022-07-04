package vega.it.TimeSheetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
