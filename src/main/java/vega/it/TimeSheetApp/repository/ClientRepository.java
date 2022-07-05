package vega.it.TimeSheetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
