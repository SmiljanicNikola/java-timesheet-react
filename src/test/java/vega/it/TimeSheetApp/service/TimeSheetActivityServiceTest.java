package vega.it.TimeSheetApp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.repository.ProjectRepository;
import vega.it.TimeSheetApp.repository.TimeSheetActivityRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;

@WebMvcTest(TimeSheetActivityService.class)
public class TimeSheetActivityServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private TimeSheetActivityRepository timeSheetActivityRepository;
	
	@MockBean
	private TimeSheetActivityService timeSheetActivityService;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;

	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
	
	TeamMember teamMember = new TeamMember(1, "memberFirstname", "memberLastname", "memberUsername", "memberPassword", 8, "member@gmail.com", false, Roles.ADMIN);
	Country country = new Country(1, "SRBIJA");
    Client client = new Client(1, "client", "address", "Novi Sad", "12020", country, false);
    Project project = new Project(1, "projectDesc", "some proj", client, teamMember, true);
    Category category = new Category(1, "categoryType");
    
    TimeSheetActivity timeSheetActivity1 = new TimeSheetActivity(1, "nis spec", teamMember, project, category, 8, 0, LocalDate.now());
    TimeSheetActivity timeSheetActivity2 = new TimeSheetActivity(1, "puno toga", teamMember, project, category, 8, 2, LocalDate.now());
	
    @Test
	public void testGetAllTimeSheetActivities_success() {
		
		ArrayList<TimeSheetActivity> timeSheetActivities = new ArrayList<>(Arrays.asList(timeSheetActivity1,timeSheetActivity2));
			
		 Mockito.when(timeSheetActivityRepository.findAll()).thenReturn(timeSheetActivities);

	     assertThat(timeSheetActivities.size(), is(2));

	}
	
}
