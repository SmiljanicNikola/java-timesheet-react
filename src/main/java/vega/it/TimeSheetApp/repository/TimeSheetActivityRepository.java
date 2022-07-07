package vega.it.TimeSheetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.TimeSheetActivity;

@Repository
public interface TimeSheetActivityRepository extends JpaRepository<TimeSheetActivity, Integer> {

}