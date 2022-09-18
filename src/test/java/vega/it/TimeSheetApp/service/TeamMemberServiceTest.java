package vega.it.TimeSheetApp.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vega.it.TimeSheetApp.controller.TeamMemberController;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.service.implementation.TeamMemberServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@WebMvcTest(TeamMemberServiceImpl.class)
public class TeamMemberServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private TeamMemberRepository teamMemberRepository;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	
	TeamMember teamMember1 = new TeamMember(1, "memberFirstname", "memberLastname", "mare", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	
	@Test
	public void testGetTeamMeberByUsername() {
		 Mockito.when(teamMemberRepository.findTeamMemberByUsername(teamMember1.getUsername())).thenReturn(teamMember1);

	     assertThat(teamMember1.getId(), is(1));

	}
	
	 @Test
	 public void testGetTeamMemberById() {
		 Mockito.when(teamMemberRepository.existsById(1)).thenReturn(true);

	     assertThat(teamMember1.getId(), is(1));
	 }
	 
	@Test
	public void testSaveTeamMember() {
		
		TeamMember newTeamMember = new TeamMember("milenko", "milenkovic", "milenkom", "memberPassword", 8, "milenko@gmail.com");
			
		TeamMember savedTeamMember = new TeamMember(3,"milenko", "milenkovic", "milenkom", "memberPassword", 8, "milenko@gmail.com", false, null);
		
		 Mockito.when(teamMemberRepository.save(newTeamMember)).thenReturn(savedTeamMember);

	     assertThat(savedTeamMember.getId(), is(3));
	     assertThat(savedTeamMember.getUsername(), is("milenkom"));
	     assertThat(savedTeamMember.getFirstname(), is("milenko"));
	     assertThat(savedTeamMember.getLastname(), is("milenkovic"));
	     assertThat(savedTeamMember.getPassword(), is("memberPassword"));
	     assertThat(savedTeamMember.getHoursPerWeek(), is(8));
	     assertThat(savedTeamMember.getEmail(), is("milenko@gmail.com"));

		}
		
		@Test
		public void testFilterTeamMembersByUsername() {
			
			TeamMember teamMember4 = new TeamMember(4, "memberFirstname", "memberLastname", "djura", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
			TeamMember teamMember5 = new TeamMember(5, "memberFirstname", "memberLastname", "mika", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
			TeamMember teamMember6 = new TeamMember(6, "memberFirstname", "memberLastname", "mile", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);

			ArrayList<TeamMember> filteredTeamMembers = new ArrayList<>(Arrays.asList(teamMember5,teamMember6));

			
			 Mockito.when(teamMemberRepository.filterAllTeamMembersByUsername("mi")).thenReturn(filteredTeamMembers);
			 
		     assertThat(filteredTeamMembers.size(), is(2));
	 
		}


}
