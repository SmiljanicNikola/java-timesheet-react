package vega.it.TimeSheetApp.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TimeSheetActivityController.class)
public class TimeSheetControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private TimeSheetActivityService timeSheetService;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private CountryService countryService;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private TokenUtils jwtTokenUtil;

	@MockBean
	private DayService dayService;
	
	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private TimeSheetActivityRepository timeSheetRepository;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	/*@MockBean	
	private UserDetailsServiceImpl jwtUserDetailsService;*/
	
	TeamMember teamMember = new TeamMember(1, "memberFirstname", "memberLastname", "memberUsername", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	Country country = new Country(1, "SRBIJA");
    Client client = new Client(1, "client", "address", "Novi Sad", "12020", country, false);
    Project project = new Project(1, "projectDesc", "some proj", client, teamMember, true);
    Category category = new Category(1, "categoryType");
    
    TimeSheetActivity timeSheetActivity1 = new TimeSheetActivity(1, "nis spec", teamMember, project, category, 8, 0, LocalDate.now());
    TimeSheetActivity timeSheetActivity2 = new TimeSheetActivity(1, "puno toga", teamMember, project, category, 8, 2, LocalDate.now());
	
    @BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    
	@Test
	public void testExportPDF() throws Exception {
		
		List<TimeSheetActivity> listTimeSheetActivities = new ArrayList<>();
		listTimeSheetActivities.add(new TimeSheetActivity(1, "nis spec", teamMember, project, category, 8, 0, LocalDate.now()));
		listTimeSheetActivities.add(new TimeSheetActivity(1, "puno toga", teamMember, project, category, 8, 2, LocalDate.now()));

		Mockito.when(timeSheetService.findAll()).thenReturn(listTimeSheetActivities);

		/*RequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/timeSheetActivites/export")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON);
		
		
		MvcResult mvcResult = mockMvc.perform(mockRequest).andExpect(status().isOk()).andReturn();*/
		mockMvc.perform(MockMvcRequestBuilders.post("/api/timeSheetActivities/export")
               	.content(asJsonString(listTimeSheetActivities))
               	.contentType(MediaType.APPLICATION_JSON))
	  			.andExpect(status().isOk());
		
		//byte[] bytes = mvcResult.getResponse().getContentAsByteArray();
		
		
		//Files.write(path, bytes);
	}
	
	@Test
	public void getTimesheetActivityById_success() throws Exception{		

       Mockito.when(timeSheetService.findById(1)).thenReturn(timeSheetActivity1);
		
       mockMvc.perform(MockMvcRequestBuilders.get("/api/timeSheetActivities/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", notNullValue()));
             
   }
	
	@Test
	public void getTimesheetActivitiesByDate_success() throws Exception{		
		
        ArrayList<TimeSheetActivity> listTimeSheetActivities = new ArrayList<>(Arrays.asList(timeSheetActivity1,timeSheetActivity2));
		
       Mockito.when(timeSheetService.findAllByDate(LocalDate.now())).thenReturn(listTimeSheetActivities);
       
       LocalDate date = LocalDate.now();
		
       mockMvc.perform(MockMvcRequestBuilders.get("/api/timeSheetActivities/byDate/$`date`").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", notNullValue()));
             
   }
	
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
    }
	
}
}
