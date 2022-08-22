package vega.it.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query(value = "SELECT * from client where client.deleted = false",nativeQuery = true )
	Page<Client> findAllClientsWithPagination(Pageable pageable);
	
	@Query(value = "SELECT * from client where client.client_name LIKE :letter%",nativeQuery = true )
	List<Client> filterAllClientsByFirstLetter(String letter);
	
	@Query(value = "SELECT DISTINCT c.client_id, c.address, c.city, c.client_name, c.deleted,c.zip_code, c.country_id, tm.team_member_id, tm.username FROM client c JOIN project p ON p.client_id = c.client_id  JOIN team_members tm ON tm.team_member_id = p.team_member_id where tm.username = :teamMemberUsername",nativeQuery = true )
	Page<Client> findAllClientsWithPaginationByTeamMemberUsername(String teamMemberUsername,Pageable pageable);
	
	
}
