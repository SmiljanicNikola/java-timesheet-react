package vega.it.TimeSheetApp.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vega.it.TimeSheetApp.DTO.DayDTO;
import vega.it.TimeSheetApp.model.TimeSheetActivity;

public interface DayService {
	
	List<DayDTO> findAll();
		
	List<DayDTO> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
	
	List<DayDTO> findAllBetweenStartDateAndEndDateAndTeamMemberId(LocalDate startDate, LocalDate endDate, Integer teamMemberId);

	List<DayDTO> findAllBetweenStartDateAndEndDateAndTeamMemberUsername(LocalDate startDate, LocalDate endDate, String teamMemberUsername);

}
