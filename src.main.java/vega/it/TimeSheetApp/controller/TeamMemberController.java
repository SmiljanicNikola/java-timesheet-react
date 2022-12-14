package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.exceptions.EntityNotFoundException;
import vega.it.TimeSheetApp.exceptions.ResourceNotFoundException;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.security.JWTResponse;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.TeamMemberService;

@RestController
@RequestMapping(value = "api/teamMembers")
public class TeamMemberController {
	
	@Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;
    
    @Autowired
  	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	@GetMapping
	public ResponseEntity<List<TeamMemberDTO>> getTeamMembers(){
		
		List<TeamMemberDTO> teamMembersDTO = teamMemberService
				.findAll()
				.stream()
				.map(c -> new TeamMemberDTO(c))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
	}
	
	@GetMapping("/paginate")
	public ResponseEntity<Page<TeamMember>> findAll(Pageable pageable){
		return new ResponseEntity<>(teamMemberService.findAllTeamMembersPaginate(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TeamMemberDTO> getTeamMemberById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
		try 
		{
			TeamMember teamMember = teamMemberService.findById(id);
	        return new ResponseEntity<>(new TeamMemberDTO(teamMember), HttpStatus.OK);

		}catch(ResourceNotFoundException resourceNotFoundException)
		{	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that id: "+ id ,resourceNotFoundException);		
		}
	}
	
	@GetMapping(value="/username/{username}")
	public ResponseEntity<TeamMemberDTO> getTeamMemberByUsername(@PathVariable("username") String username) throws ResourceNotFoundException{
		try 
		{
			TeamMember teamMember = teamMemberService.findByUsername(username);
	        return new ResponseEntity<>(new TeamMemberDTO(teamMember), HttpStatus.OK);

		}catch(ResourceNotFoundException resourceNotFoundException)
		{	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that username: "+ username ,resourceNotFoundException);		
		}
	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {	
		try 
		{
	        TeamMember teamMember = teamMemberService.findById(id);
	        
	        teamMember.setDeleted(true);
	    	teamMemberService.save(teamMember);
	    	
	        return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(ResourceNotFoundException resourceNotFoundException)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that id: "+ id ,resourceNotFoundException);
		}
	}
	
	
	@PostMapping()
    public ResponseEntity<TeamMemberDTO> saveTeamMember(@RequestBody TeamMemberDTO teamMemberDTO) {
		
        TeamMember teamMember = new TeamMember();
        
        teamMember.setFirstname(teamMemberDTO.getFirstname());
        teamMember.setLastname(teamMemberDTO.getLastname());
        teamMember.setUsername(teamMemberDTO.getUsername());
        teamMember.setPassword(teamMemberDTO.getPassword());
        teamMember.setHoursPerWeek(teamMemberDTO.getHoursPerWeek());
        teamMember.setEmail(teamMemberDTO.getEmail());
        teamMember.setBlocked(false);
        teamMember.setDeleted(false);
        teamMember.setRole(Roles.WORKER);
        
        teamMember = teamMemberService.save(teamMember);
        return new ResponseEntity<>(new TeamMemberDTO(teamMember), HttpStatus.CREATED);
	        	
	 }
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<TeamMemberDTO> updateTeamMember(@RequestBody TeamMemberDTO teamMemberDTO, @PathVariable("id") Integer id) {
	
        TeamMember teamMember = teamMemberService.findById(id);

        if (teamMember == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        if(teamMemberDTO.getFirstname() != null) {
        	teamMember.setFirstname(teamMemberDTO.getFirstname());
        }
        
        if(teamMemberDTO.getUsername() != null) {
        	teamMember.setUsername(teamMemberDTO.getUsername());
        }
        
        if(teamMemberDTO.getHoursPerWeek() != null) {
        	teamMember.setHoursPerWeek(teamMemberDTO.getHoursPerWeek());
        }
        
        if(teamMemberDTO.getEmail() != null) {
        	teamMember.setEmail(teamMemberDTO.getEmail());
        }

        teamMember = teamMemberService.save(teamMember);

        return new ResponseEntity<>(new TeamMemberDTO(teamMember), HttpStatus.OK);
    }
	
	/*@PostMapping("/login")
	public ResponseEntity<JWTResponse> login(@RequestBody TeamMemberDTO teamMemberDTO){
		String usernameLogged = teamMemberDTO.getUsername();
		TeamMember teamMember = teamMemberService.findByUsername(usernameLogged);
		if(teamMember != null) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(teamMemberDTO.getUsername(), teamMemberDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			try {
				UserDetails userDetails = userDetailsService.loadUserByUsername(teamMemberDTO.getUsername());
				String token = tokenUtils.generateToken(userDetails);
				return ResponseEntity.ok(new JWTResponse(token, 
		   				 userDetails.getUsername()));
		        } catch (UsernameNotFoundException e) {
		            return ResponseEntity.notFound().build();
		        }
			}else {
		    	return ResponseEntity.status(404).build();
		    }
		}*/
		
	}
	
