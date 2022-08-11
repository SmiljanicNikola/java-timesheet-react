package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;

public interface ClientService {
	
	List<Client> findAll();
	
	Client findById(Integer clientId);
	
	Client save (Client client);
	
	void remove(Integer id);
	
	Page<Client> findAllClientsPagination(Pageable pageable);
	
	List<Client> filterAllClientsByFirstLetter(String letter);

	Page<Client> findAllClientsPaginatedByTeamMemberUsername(String teamMemberUsername,Pageable pageable);

}
