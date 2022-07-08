package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;

public interface TeamMemberService {
	
	List<TeamMember> findAll();
	
	TeamMember findById(Integer teamMemberId);
	
	TeamMember save(TeamMember teamMember);
	
	void remove(Integer id);

	Page<TeamMember> findAllTeamMembersPaginate(Pageable pageable);
	
}
