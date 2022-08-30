package vega.it.TimeSheetApp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.ProjectRepository;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.ProjectService;
import vega.it.TimeSheetApp.service.TeamMemberService;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
		
	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	 
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private TokenUtils jwtTokenUtil;

	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	@MockBean
	AuthenticationManager authenticationManager;
	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	
	 
	TeamMember teamMember = new TeamMember(1, "memberFirstname", "memberLastname", "memberUsername", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	Country country = new Country(1, "SRBIJA");
	Client client = new Client(1, "client", "address", "Novi Sad", "12020", country, false);
	 
	Project project1 = new Project(1, "projectDesc1", "some proj1", client, teamMember, true);
	Project project2 = new Project(2, "projectDesc2", "some proj2", client, teamMember, true);


	/*@Test
    public void getProjectsPageable_failed() throws Exception{
        ArrayList<Project> projects = new ArrayList<>(Arrays.asList(project1,project2));
        PageImpl<Project> projectsPage = new PageImpl<Project>(projects);
        
        Mockito.when(projectService.findAllProjectsPaginate(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(projectsPage);
        //Mockito.when(teamMemberService.findAllTeamMembersPaginate(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(teamMembersPage);

       
        mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }*/
	
	@Test
 	public void getProjectById_success() throws Exception{		

        Mockito.when(projectService.findById(1)).thenReturn(project1);
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
	public void testSaveProject_success() throws JsonProcessingException, Exception{
		
		Project newProject = new Project("projectDesc3", "some proj3", client, teamMember);
		
		Project savedProject = new Project(3, "projectDesc3", "some proj3", client, teamMember, false,false);
		
		Mockito.when(projectService.save(Mockito.any(Project.class))).thenReturn(savedProject);
		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
	               	.content(asJsonString(newProject))
	               	.contentType(MediaType.APPLICATION_JSON))
         			.andExpect(status().isCreated());
		
	}
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
     }
 }
	


	
}
