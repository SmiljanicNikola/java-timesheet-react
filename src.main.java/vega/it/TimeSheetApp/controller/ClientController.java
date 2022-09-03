package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vega.it.TimeSheetApp.DTO.AddClientRequestDTO;
import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.exceptions.EntityNotFoundException;
import vega.it.TimeSheetApp.exceptions.ResourceNotFoundException;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.CountryService;
import vega.it.TimeSheetApp.service.TeamMemberService;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> getClients(){
		
		List<ClientDTO> clientsDTO = clientService
				.findAll()
				.stream()
				.map(c -> new ClientDTO(c))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/paginate")
	public ResponseEntity<Page<Client>> findAll(Pageable pageable){
		
		Object userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0];
		String teamMemberUsername = SecurityContextHolder.getContext().getAuthentication().getName();
				
		Page<Client> page = null;
		if(userRole.toString().contains("ROLE_WORKER")) {
			page = clientService.findAllClientsPaginatedByTeamMemberUsername(teamMemberUsername, pageable);
		}
		
		if(userRole.toString().contains("ROLE_ADMIN")) {
			page = clientService.findAllClientsPagination(pageable);
		}
		
		return new ResponseEntity<>(page,HttpStatus.OK);
			
	}
	
	@GetMapping("/byTeamMemberUsername/paginated")
	public ResponseEntity<Page<Client>> findAllClientsAssociatedWithTeamMember(Pageable pageable){
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return new ResponseEntity<>(clientService.findAllClientsPaginatedByTeamMemberUsername(username, pageable),HttpStatus.OK);	
		
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Integer id){
		try 
		{
			Client client = clientService.findById(id);
	        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
		}
		catch(ResourceNotFoundException resourceNotFoundException)
		{	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that id: "+ id ,resourceNotFoundException);		
		}
	}
	
	@GetMapping(value="filterBy/{letter}")
	public ResponseEntity<List<ClientDTO>> getClientByFirstLetter(@PathVariable("letter") String letter){
		
			List<ClientDTO> clientsDTO = clientService.filterAllClientsByFirstLetter(letter).stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
			
			
	        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
		
	}


	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			
			Client client = clientService.findById(id);    
	        client.setDeleted(true);
	        clientService.save(client);
	        return new ResponseEntity<>(HttpStatus.OK);
	        
		}catch(ResourceNotFoundException resourceNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that id: "+ id ,resourceNotFoundException);
		}
    }
	
	
	@PostMapping()
    public ResponseEntity<ClientDTO> saveClient(@RequestBody AddClientRequestDTO addClientRequestDTO) {
		
        Client client = new Client();
        
        client.setClientName(addClientRequestDTO.getClientName());
        client.setAddress(addClientRequestDTO.getAddress());
        client.setCity(addClientRequestDTO.getCity());
        client.setZipCode(addClientRequestDTO.getZipCode());
        client.setCountry(this.countryService.findById(addClientRequestDTO.getCountryId()));        

        client = clientService.save(client);
        
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
	        	
	}
	
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody AddClientRequestDTO addClientRequestDTO, @PathVariable("id") Integer id) {
	
        Client client = clientService.findById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        if(client.getClientName() != null) {
        	client.setClientName(addClientRequestDTO.getClientName());
        }
        client.setAddress(addClientRequestDTO.getAddress());
        client.setCity(addClientRequestDTO.getCity());
        client.setZipCode(addClientRequestDTO.getZipCode());

        client = clientService.save(client);

        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }
	
}
