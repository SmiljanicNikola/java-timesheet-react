package vega.it.TimeSheetApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {
	
	@Query(value = "SELECT * from team_members where team_members.username = :username",nativeQuery = true )
	TeamMember findTeamMemberByUsername(String username);


	@Query(value = "SELECT * from team_members where team_members.deleted = false",nativeQuery = true )
	Page<TeamMember> findAllTeamMembersWithPagination(Pageable pageable);
	
	@Query(value = "SELECT * from team_members where team_members.username LIKE :letter%",nativeQuery = true )
	List<TeamMember> filterAllTeamMembersByUsername(String letter);
	
}
