package vega.it.TimeSheetApp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.service.TeamMemberService;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TeamMemberService teamMemberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		TeamMember teamMember = teamMemberService.findByUsername(username);
		
		if(teamMember == null) {
            throw new UsernameNotFoundException("There is no member with that specific username " + username);

		}else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String role = "ROLE_" + teamMember.getRole().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
            		teamMember.getUsername().trim(),
            		teamMember.getPassword().trim(),
                    grantedAuthorities);
        }
	}

}
