package vega.it.TimeSheetApp.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
 	public void getProjectByNonExistentId_success() throws Exception{		

        Mockito.when(projectService.findById(7)).thenReturn(null);
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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
	
	@Test
    public void testUpdateProject_success() throws Exception {
		
    	Mockito.when(projectService.findById(project1.getId())).thenReturn(project1);

    	TeamMember teamMemberTest = new TeamMember(5, "member", "someLastname", "someUsername", "memberPassword", 8, "memberrrr@gmail.com", false, Roles.ADMIN);
    	Client clientTest = new Client(5, "client", "address", "Novi Sad", "12020", country, false);
    	 
		project1.setProjectName("updatedProjectName");
		project1.setDescription("updatedDescription");
		project1.setClient(clientService.findById(clientTest.getId()));
		project1.setLead(teamMemberService.findById(teamMemberTest.getId()));
		
		Project updatedProject = new Project(
				project1.getDescription(),
				project1.getProjectName(),
				project1.getClient(),
				project1.getLead(),
				project1.getFinished(),
				project1.getDeleted());
			
		  Mockito.when(projectService.save(project1)).thenReturn(updatedProject);
	
	
		  mockMvc.perform(MockMvcRequestBuilders.put("/api/projects/1")
	               	.content(asJsonString(project1))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.projectName").value("updatedProjectName"))
	                .andExpect(jsonPath("$.description").value("updatedDescription"));
	                

    }
	
	@Test
	public void deleteProjectByNonExistentId_success() throws Exception {
	    Mockito.when(projectService.findById(5)).thenReturn(null);

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/projects/2")
	            .contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().is4xxClientError());
	}
	
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
     }
 }
	


	
}
