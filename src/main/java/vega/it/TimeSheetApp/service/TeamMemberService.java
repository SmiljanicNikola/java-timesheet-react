package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.exceptions.EntityArleadyExistsException;
import vega.it.TimeSheetApp.exceptions.EntityNotFoundException;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;

public interface TeamMemberService {
	
	List<TeamMember> findAll();
	
	TeamMember findById(Integer teamMemberId) throws EntityNotFoundException;
	
	TeamMember save(TeamMember teamMember) throws EntityArleadyExistsException;
	
	void remove(Integer id);

	Page<TeamMember> findAllTeamMembersPaginate(Pageable pageable);
	
}
