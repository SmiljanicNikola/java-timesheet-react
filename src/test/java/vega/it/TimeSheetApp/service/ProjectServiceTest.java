package vega.it.TimeSheetApp.service;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.ProjectRepository;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.service.implementation.ProjectServiceImpl;


@WebMvcTest(ProjectService.class)
public class ProjectServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	TeamMember teamMember = new TeamMember(1, "memberFirstname", "memberLastname", "memberUsername", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	Country country = new Country(1, "SRBIJA");
	Client client = new Client(1, "client", "address", "Novi Sad", "12020", country, false);
	 
	Project project1 = new Project(1, "projectDesc1", "some proj1", client, teamMember, false);
	Project project2 = new Project(2, "projectDesc2", "some proj2", client, teamMember, false);

	@Test
	public void getProjectsPageable_success() throws Exception{
		
        ArrayList<Project> projects = new ArrayList<>(Arrays.asList(project1,project2));
        PageImpl<Project> projectsPage = new PageImpl<Project>(projects);
        
        Mockito.when(projectRepository.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(projectsPage);
   
        assertThat(projectsPage.getNumberOfElements(), is(2));
	    assertThat(projectsPage.getTotalPages(), is(1));

	}
	
	@Test
	public void testGetAllProjects_success() {
		
		ArrayList<Project> projects = new ArrayList<>(Arrays.asList(project1,project2));
			
		 Mockito.when(projectRepository.findAll()).thenReturn(projects);

	     assertThat(projects.size(), is(2));

	}
	
	
	@Test
	public void testGetProjectById_success() {
		Mockito.when(projectRepository.existsById(1)).thenReturn(true);
		
		 assertThat(project1.getId(), is(1));
	}
	
	@Test
	public void testSaveProject_success() {
		
		Project newProject = new Project("projectDesc3", "some proj3", client, teamMember, false);

		Project savedProject = new Project(3, "projectDesc3", "some proj3", client, teamMember, false);

		Mockito.when(projectRepository.save(newProject)).thenReturn(savedProject);
		
		assertThat(savedProject.getId(), is(3));
		assertThat(savedProject.getProjectName(), is("some proj3"));
		assertThat(savedProject.getDescription(), is("projectDesc3"));
		assertThat(savedProject.getClient(), is(client));
		assertThat(savedProject.getLead(), is(teamMember));
		assertThat(savedProject.getFinished(), is(false));



	}

}
