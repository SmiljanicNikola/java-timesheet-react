package vega.it.TimeSheetApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vega.it.TimeSheetApp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query(value = "SELECT * from client where client.deleted = false",nativeQuery = true )
	Page<Client> findAllClientsWithPagination(Pageable pageable);
	
}
