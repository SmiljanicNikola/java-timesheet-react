package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.TimeSheetActivity;

public interface TimeSheetActivityService {
	

	List<TimeSheetActivity> findAll();
	
	TimeSheetActivity findById(Integer timeSheetActivityId);
	
	TimeSheetActivity save(TimeSheetActivity timeSheetActivity);
	
	void remove(Integer id);
	
	List<TimeSheetActivity> findAllByProjectId(Integer projectId);

	List<TimeSheetActivity> findAllByThreeParameters(Integer projectId, Integer teamMemberId, Integer categoryId);

	
}
