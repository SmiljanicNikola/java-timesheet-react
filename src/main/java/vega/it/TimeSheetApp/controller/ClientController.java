package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.AddClientRequestDTO;
import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.CountryService;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> getClients(){
		
		List<Client> clients = clientService.findAll();
		
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for(Client c : clients) {
			clientsDTO.add(new ClientDTO(c));
		}
		
		return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
	}
	
	@GetMapping("/paginate")
	public ResponseEntity<Page<Client>> findAll(Pageable pageable){
		return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Integer id){
		
		Client client = clientService.findOne(id);
		if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		Client client = clientService.findOne(id);
        if (client != null) {
        	clientService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping()
    public ResponseEntity<ClientDTO> saveClient(@RequestBody AddClientRequestDTO addClientRequestDTO) {
		
        Client client = new Client();
        
        client.setClientName(addClientRequestDTO.getClientName());
        client.setAddress(addClientRequestDTO.getAddress());
        client.setCity(addClientRequestDTO.getCity());
        client.setZipCode(addClientRequestDTO.getZipCode());
        client.setCountry(this.countryService.findOne(addClientRequestDTO.getCountryId()));        

        client = clientService.save(client);
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
	        	
	 }
	
	
	
}
