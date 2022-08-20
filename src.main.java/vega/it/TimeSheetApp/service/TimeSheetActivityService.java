package vega.it.TimeSheetApp.service;

import java.time.LocalDate;
import java.util.List;

import vega.it.TimeSheetApp.DTO.DayDTO;
import vega.it.TimeSheetApp.model.SearchObject;
import vega.it.TimeSheetApp.model.TimeSheetActivity;

public interface TimeSheetActivityService {
	
	List<TimeSheetActivity> findAll();
	
	TimeSheetActivity findById(Integer timeSheetActivityId);
	
	List<TimeSheetActivity> findByTeamMemberId(Integer teamMemberId);
	
	TimeSheetActivity save(TimeSheetActivity timeSheetActivity);
	
	void remove(Integer id);
	
	List<TimeSheetActivity> findAllByProjectId(Integer projectId);

	List<TimeSheetActivity> findAllByThreeParameters(Integer projectId, Integer teamMemberId, Integer categoryId, LocalDate startDate, LocalDate endDate);
	
	List<TimeSheetActivity> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
	
	List<TimeSheetActivity> findAllBetweenStartDateAndEndDateAndTeamMemberId(LocalDate startDate, LocalDate endDate, Integer teamMemberId);

	List<TimeSheetActivity> findAllBetweenStartDateAndEndDateAndTeamMemberUsername(LocalDate startDate, LocalDate endDate, String teamMemberUsername);

	List<TimeSheetActivity> findAllByDate(LocalDate date);

}
