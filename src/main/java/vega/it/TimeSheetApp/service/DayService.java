package vega.it.TimeSheetApp.service;

import java.time.LocalDate;
import java.util.List;

import vega.it.TimeSheetApp.DTO.DayDTO;
import vega.it.TimeSheetApp.model.TimeSheetActivity;

public interface DayService {
	
	List<DayDTO> findAll();
		
	List<DayDTO> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

}
