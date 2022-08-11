package vega.it.TimeSheetApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.security.JWTResponse;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.TeamMemberService;


@RestController
@RequestMapping(value = "api/auth")
public class AuthController {
	
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
	
	@PostMapping("/login")
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
		}
	
	

}
