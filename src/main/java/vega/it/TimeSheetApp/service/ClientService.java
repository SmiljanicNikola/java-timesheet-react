package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.Client;

public interface ClientService {
	
	List<Client> findAll();
	
	Client findOne(Integer clientId);
	
	Client save (Client client);
	
	void remove(Integer id);
	
}
