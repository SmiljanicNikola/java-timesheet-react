package vega.it.TimeSheetApp.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vega.it.TimeSheetApp.service.TeamMemberService;

@Component
public class CustomSecurityContext {

	@Autowired
	private TeamMemberService teamMemberService;
	
	@Autowired
	private TokenUtils tokenUtils;

	
	public String getUsernameFromSecurityContextHolder() {
	        try {
	        	String teamMemberUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	        	return teamMemberUsername;
	        } catch (Exception e) {
	            return null;
        }
     }
	
	
	public SimpleGrantedAuthority getUserRoleFromSecurityContextHolder() {
	        try {
	        	SimpleGrantedAuthority userRole = (SimpleGrantedAuthority) SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0];
	        	return userRole;
	        } catch (Exception e) {
	            return null;
        }
	 }
	 
	
	 public String getToken(ServletRequest request, ServletResponse response) {
	 HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader("Authorization");
		if(token != null){
	           if(token.startsWith("Bearer ")){
	               token = token.substring(7);
	           }
			}
		return token;
	 }
	 
	
}
