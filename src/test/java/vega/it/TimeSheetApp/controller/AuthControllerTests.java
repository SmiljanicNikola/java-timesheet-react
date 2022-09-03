package vega.it.TimeSheetApp.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.CategoryService;
import vega.it.TimeSheetApp.service.TeamMemberService;

@WebMvcTest(AuthController.class)
public class AuthControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private CategoryService categoryService;

	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private TeamMemberRepository teamMemberRepository;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	@MockBean
	private TokenUtils jwtTokenUtil;
	
	@MockBean
	AuthenticationManager authenticationManager;

	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	TeamMember testUser = new TeamMember(1, "memberFirstname", "memberLastname", "mare", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	
	  @Test
	  public void testValidRole()
	  {
	    //Get the user by username from configured user details service
	    UserDetails userDetails = userDetailsService.loadUserByUsername("petarp");
	    Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authToken);
	   
	  }
	  
	  
	  /*@Test (expected = AccessDeniedException.class)
	  public void testInvalidUser()
	  {
	    UserDetails userDetails = userDetailsService.loadUserByUsername ("admin");
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    authorities.add(new GrantedAuthorityImpl("ROLE_INVALID"));
	    Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), authorities);
	    SecurityContextHolder.getContext().setAuthentication(authToken);
	    DemoService service = (DemoService) webApplicationContext.getBean("demoService");
	    service.method();
	  }*/
	
	@Test
	public void testUserLogin() throws Exception {
	    /*RequestBuilder requestBuilder = POST("api/auth/login")
	            .param("username", testUser.getUsername())
	            .param("password", testUser.getPassword());
	    mockMvc.perform(requestBuilder)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(cookie().exists("JSESSIONID"));*/
	    

        /*((MockHttpServletRequestBuilder) mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
        		.contentType(MediaType.APPLICATION_JSON)))
		  		.content(testUser.getUsername())
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));*/
	}
	   
}
