package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.service.ClientService;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> getClients(){
		List<Client> clients = clientService.findAll();
		
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for(Client c : clients) {
			clientsDTO.add(new ClientDTO(c));
		}
		
		return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Integer id){
		Client client = clientService.findOne(id);
		if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);

	}
	
}
