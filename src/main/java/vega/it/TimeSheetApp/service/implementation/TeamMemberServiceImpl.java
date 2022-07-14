package vega.it.TimeSheetApp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.service.TeamMemberService;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Override
	public List<TeamMember> findAll() {
		return teamMemberRepository.findAll().stream().filter(teamMember -> teamMember.getDeleted() == false).collect(Collectors.toList());
	}

	@Override
	public TeamMember findById(Integer teamMemberId) {
		return teamMemberRepository.findById(teamMemberId).orElse(null);
	}

	@Override
	public TeamMember save(TeamMember teamMember) {
		teamMemberRepository.save(teamMember);
		return teamMember;
	}

	@Override
	public void remove(Integer id) {
		teamMemberRepository.deleteById(id);
		
	}

	@Override
	public Page<TeamMember> findAllTeamMembersPaginate(Pageable pageable) {
		return teamMemberRepository.findAllTeamMembersWithPagination(pageable);
	}

	@Override
	public List<TeamMember> filterMembersByUsername(String letter) {
		return teamMemberRepository.filterAllTeamMembersByUsername(letter);
	}

	

}
