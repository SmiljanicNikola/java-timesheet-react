package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.TeamMember;

public interface TeamMemberService {
	
	List<TeamMember> findAll();
	
	TeamMember findOne(Integer teamMemberId);
	
	TeamMember save(TeamMember teamMember);
	
	void remove(Integer id);

}
