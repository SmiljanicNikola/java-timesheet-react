package vega.it.TimeSheetApp.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vega.it.TimeSheetApp.DTO.AddClientRequestDTO;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Country;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.ClientRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.CountryService;
import vega.it.TimeSheetApp.service.TeamMemberService;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@WebMvcTest(ClientController.class)
public class ClientControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private ClientRepository clientRepository;
	
	@MockBean
	private CountryService countryService;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;

	
	Country country = new Country(1, "SRBIJA");
    Client client1 = new Client(1, "clientOne", "addressOne", "Novi Sad", "12020", country, false);
    Client client2 = new Client(2, "clientTwo", "addressTwo", "Novi Sad", "12020", country, false);

    @BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    
    
    @Test
    public void getClients_success() throws Exception{
		
        ArrayList<Client> clients = new ArrayList<>(Arrays.asList(client1,client2));

        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));

    }
    
    @Test
 	public void getClientsPageable_success() throws Exception{
		
        ArrayList<Client> clients = new ArrayList<>(Arrays.asList(client1,client2));
        PageImpl<Client> clientsPage = new PageImpl<Client>(clients);
        
        Mockito.when(clientService.findAllClientsPagination(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(clientsPage);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));
        
    }
    
    
   	@Test
    	public void getClientById_success() throws Exception{		

           Mockito.when(clientService.findById(1)).thenReturn(client1);
   		
           mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/1").contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$", notNullValue()))
                   .andExpect(jsonPath("$.clientName").value("clientOne"));
       }
   	
	@Test
	public void filterClientByLetterOfClientName_success() throws Exception{
		
	   ArrayList<Client> clients = new ArrayList<>(Arrays.asList(client1,client2));

       Mockito.when(clientService.filterAllClientsByFirstLetter("c")).thenReturn(clients);
		
       mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/filterBy/c").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", notNullValue()))
       		   .andExpect(jsonPath("$[1].clientName").value("clientTwo"))
		   		.andExpect(jsonPath("$[0].clientName").value("clientOne"));

   }
	
	@Test
	public void testSaveClient_WORKINGGG() throws JsonProcessingException, Exception{
		
		Country country = new Country(1, "SRBIJA");
	    AddClientRequestDTO newClient = new AddClientRequestDTO("newClientName", "newAddress", "Novi Sad", "12020", country.getId());

		
		Client savedClient= new Client(3, "newClientName", "newAddress", "Novi Sad", "12020", country, false);
		
		Mockito.when(clientService.save(Mockito.any(Client.class))).thenReturn(savedClient);

		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/teamMembers")
	               	.content(asJsonString(newClient))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isCreated())
		  			.andExpect(jsonPath("$.clientName").value("newClientName"))
					.andExpect(jsonPath("$.address").value("newAddress"))
  		  			.andExpect(jsonPath("$.city").value("Novi Sad"));
					
	}
	
	@Test
    public void testUpdateClient_success() throws Exception {
		
    	Mockito.when(clientService.findById(client1.getId())).thenReturn(client1);

			
    	client1.setClientName("updatedClientName");
    	client1.setAddress("updatedAddress");
    	client1.setCity("updatedCity");
    	client1.setZipCode("updatedZipCode");
		
		Client updatedClient = new Client(
				client1.getClientName(),
				client1.getAddress(),
				client1.getCity(),
				client1.getZipCode(),
				client1.getCountry());
			
		  Mockito.when(clientService.save(client1)).thenReturn(updatedClient);
	
	
		  mockMvc.perform(MockMvcRequestBuilders.put("/api/clients/1")
	               	.content(asJsonString(client1))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.clientName").value("updatedClientName"))
	                .andExpect(jsonPath("$.address").value("updatedAddress"))
	                .andExpect(jsonPath("$.city").value("updatedCity"))	             
	                .andExpect(jsonPath("$.zipCode").value("updatedZipCode"));

    }
	
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
     }
 }
   	
    
}
