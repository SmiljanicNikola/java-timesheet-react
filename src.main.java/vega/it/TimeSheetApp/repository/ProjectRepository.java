package vega.it.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vega.it.TimeSheetApp.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query(value = "SELECT * from project where project.deleted = false",nativeQuery = true )
	Page<Project> findAllProjectsWithPagination(Pageable pageable);
	
	@Query(value = "SELECT * from project where project.deleted = false and project.team_member_id = :teamMemberId",nativeQuery = true )
	Page<Project> findAllProjectsWithPaginationByTeamMemberId(Pageable pageable, Integer teamMemberId);
	
	@Query(value = "SELECT p.description, p.deleted, p.finished, p.client_id,p.project_id, p.project_name, tm.team_member_id, tm.username "
			+ "FROM project p INNER JOIN team_members tm "
			+ "ON p.team_member_id = tm.team_member_id "
			+ "where tm.username = :teamMemberUsername",nativeQuery = true )
	Page<Project> findAllProjectsWithPaginationByTeamMemberUsername(Pageable pageable, String teamMemberUsername);
	
	@Query(value = "SELECT * from project where project.project_name LIKE :letter%",nativeQuery = true )
	List<Project> filterAllProjectsByFirstLetter(String letter);

}
