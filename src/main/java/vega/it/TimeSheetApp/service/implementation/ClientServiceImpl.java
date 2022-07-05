package vega.it.TimeSheetApp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.repository.ClientRepository;
import vega.it.TimeSheetApp.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findOne(Integer clientId) {
		return clientRepository.findById(clientId).orElse(null);
	}

	@Override
	public Client save(Client client) {
		clientRepository.save(client);
		return client;
	}

	@Override
	public void remove(Integer id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);

	}

}
