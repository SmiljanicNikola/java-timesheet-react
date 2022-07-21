package vega.it.TimeSheetApp.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vega.it.TimeSheetApp.DTO.DayDTO;

public interface DayService {
	
	List<DayDTO> findAll();
		
	List<DayDTO> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
	
	

}
