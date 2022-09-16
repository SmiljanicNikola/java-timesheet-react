package vega.it.TimeSheetApp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.x.Ok;

import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.repository.TimeSheetActivityRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.CategoryService;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.CountryService;
import vega.it.TimeSheetApp.service.DayService;
import vega.it.TimeSheetApp.service.ProjectService;
import vega.it.TimeSheetApp.service.TeamMemberService;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.notNullValue;


@WebMvcTest(TeamMemberController.class)
public class TeamMemberControllerTests {
	
	@MockBean
    private TestEntityManager entityManager;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private CountryService countryService;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private TokenUtils jwtTokenUtil;

	@MockBean
	private DayService dayService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private TeamMemberRepository teamMemberRepository;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	@MockBean
	AuthenticationManager authenticationManager;
	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	 TeamMember teamMember1 = new TeamMember(1, "memberFirstname", "memberLastname", "mare", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	 TeamMember teamMember2 = new TeamMember(2, "memberFirstname2", "memberLastname2", "zika", "memberPassword2", 8, "secondmember@gmaill.com", false, Roles.WORKER);
	
	 
    @Test
    public void getTeamMembers_success() throws Exception{
		
        ArrayList<TeamMember> teamMembers = new ArrayList<>(Arrays.asList(teamMember1,teamMember2));

        Mockito.when(teamMemberService.findAll()).thenReturn(teamMembers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers")
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));

    }
    
	 
    @Test
 	public void getTeamMembersPageable_success() throws Exception{
		
        ArrayList<TeamMember> teamMembers = new ArrayList<>(Arrays.asList(teamMember1,teamMember2));
        PageImpl<TeamMember> teamMembersPage = new PageImpl<TeamMember>(teamMembers);
        
        Mockito.when(teamMemberService.findAllTeamMembersPaginate(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(teamMembersPage);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));
        
    }
		
	 
	@Test
 	public void getMemberById_success() throws Exception{		

        Mockito.when(teamMemberService.findById(1)).thenReturn(teamMember1);
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username").value("mare"));
    }
	
	
	@Test
 	public void getMemberByUsername_success() throws Exception{		
         		
			Mockito.when(teamMemberService.findByUsername("mare")).thenReturn(teamMember1);	         
	       	        
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/username/mare").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
    		.andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.username").value("mare"));

    }
	
	@Test
 	public void getMemberByNonExistentUsername_success() throws Exception{		
         		
			Mockito.when(teamMemberService.findByUsername("milko")).thenReturn(null);	         
	       	        
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/username/milko").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }
	

	@Test
	public void testSaveTeamMember_success() throws JsonProcessingException, Exception{
		
		TeamMember newTeamMember = new TeamMember("milenko", "milenkovic", "milenkom", "memberPassword", 8, "milenko@gmail.com");
		
		TeamMember savedTeamMember = new TeamMember(3,"milenko", "milenkovic", "milenkom", "memberPassword", 8, "milenko@gmail.com", false, null);
		
		Mockito.when(teamMemberService.save(Mockito.any(TeamMember.class))).thenReturn(savedTeamMember);

		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/teamMembers")
	               	.content(asJsonString(newTeamMember))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isCreated())
		  			.andExpect(jsonPath("$.firstname").value("milenko"))
					.andExpect(jsonPath("$.lastname").value("milenkovic"))
  		  			.andExpect(jsonPath("$.username").value("milenkom"))
  					.andExpect(jsonPath("$.email").value("milenko@gmail.com"));
					
	}
	
	
	@Test
    public void testUpdateTeamMember_success() throws Exception {
		
    	Mockito.when(teamMemberService.findById(teamMember1.getId())).thenReturn(teamMember1);

			
		teamMember1.setFirstname("updatedFirstname");
		teamMember1.setLastname("updatedLastname");
		teamMember1.setUsername("updatedUsername");
		teamMember1.setHoursPerWeek(100);
		teamMember1.setEmail("updatedEmail");
		
		TeamMember updatedTeamMember = new TeamMember(
				teamMember1.getFirstname(),
				teamMember1.getLastname(),
				teamMember1.getUsername(),
				teamMember1.getPassword(),
				teamMember1.getHoursPerWeek(),
				teamMember1.getEmail());
			
		  Mockito.when(teamMemberService.save(teamMember1)).thenReturn(updatedTeamMember);
	
	
		  mockMvc.perform(MockMvcRequestBuilders.put("/api/teamMembers/1")
	               	.content(asJsonString(teamMember1))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.firstname").value("updatedFirstname"))
	                .andExpect(jsonPath("$.lastname").value("updatedLastname"))
	                .andExpect(jsonPath("$.username").value("updatedUsername"))	             
	                .andExpect(jsonPath("$.email").value("updatedEmail"));

    }
	
	
	@Test
    public void testLogicalDeleteTeamMember_stillWORKINGGGG() throws Exception {
		
		
			Mockito.when(teamMemberService.findByUsername(teamMember2.getUsername())).thenReturn(teamMember2);

			teamMember2.setDeleted(true);
		     
			Mockito.when(teamMemberService.save(Mockito.any(TeamMember.class))).thenReturn(teamMember2);
		     
			Mockito.when(teamMemberService.findByUsername(teamMember2.getUsername())).thenReturn(teamMember2);
			
			mockMvc.perform(MockMvcRequestBuilders.delete("/api/teamMembers/2")
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isOk())
		  			.andExpect(jsonPath("$.deleted").value(true));
		     
		    assertThat(teamMember2.getDeleted()).isEqualTo(true);

    }
	
	
	@Test
    public void deleteTeamMember() throws Exception {
		
		
    	Mockito.when(teamMemberService.findById(teamMember1.getId())).thenReturn(teamMember1);
        
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/teamMembers/1")
                        .contentType(MediaType.APPLICATION_JSON))
        				.andExpect(status().isOk());

    }
	
	
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
        }
    }


}
