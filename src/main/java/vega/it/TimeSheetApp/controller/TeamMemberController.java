package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.service.ProjectService;
import vega.it.TimeSheetApp.service.TeamMemberService;

@RestController
@RequestMapping(value = "api/teamMembers")
public class TeamMemberController {
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	@GetMapping
	public ResponseEntity<List<TeamMemberDTO>> getTeamMembers(){
		List<TeamMember> teamMembers = teamMemberService.findAll();
		
		List<TeamMemberDTO> teamMembersDTO = new ArrayList<>();
		for(TeamMember tm : teamMembers) {
			teamMembersDTO.add(new TeamMemberDTO(tm));
		}
		
		return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TeamMemberDTO> getTeamMemberById(@PathVariable("id") Integer id){
		TeamMember teamMember = teamMemberService.findOne(id);
		if(teamMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new TeamMemberDTO(teamMember), HttpStatus.OK);

	}

}
