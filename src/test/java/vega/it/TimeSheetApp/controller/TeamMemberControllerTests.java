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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamMemberController.class)
public class TeamMemberControllerTests {
	
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
 	public void getMemberById_success() throws Exception{		

        //Mockito.when(teamMemberRepository.findById(1)).thenReturn(Optional.ofNullable(teamMember1));
        Mockito.when(teamMemberService.findById(1)).thenReturn(teamMember1);
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //andExpect(jsonPath("$.username").value("mare"));
    }
	
	@Test
    public void getTeamMembersPageable_success() throws Exception{
        ArrayList<TeamMember> teamMembers = new ArrayList<>(Arrays.asList(teamMember1,teamMember2));
        PageImpl<TeamMember> teamMembersPage = new PageImpl<TeamMember>(teamMembers);
        
        Mockito.when(teamMemberRepository.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(teamMembersPage);
        //Mockito.when(teamMemberService.findAllTeamMembersPaginate(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(teamMembersPage);

        

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
	
	@Test
    public void getTeamMembers_success() throws Exception{
		
        ArrayList<TeamMember> teamMembers = new ArrayList<>(Arrays.asList(teamMember1,teamMember2));

        Mockito.when(teamMemberRepository.findAll()).thenReturn(teamMembers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teamMembers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	/*@Test
    public void deleteTeamMember_success() throws Exception {
        Mockito.when(teamMemberRepository.deleteById(teamMember2.getId())).thenReturn();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/teamMembers/2")
                        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }*/

	@Test
	public void testSaveTeamMember() throws JsonProcessingException, Exception{
		 TeamMember teamMember1 = new TeamMember(1, "rrr", "rrrr", "milenkom", "memberPassword", 8, "milenko@gmail.com", false, Roles.WORKER);
		
		Mockito.when(teamMemberService.save(teamMember1)).thenReturn(teamMember1);
		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/teamMembers").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
		
		
	}


}
