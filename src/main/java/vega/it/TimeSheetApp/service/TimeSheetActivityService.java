package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.TimeSheetActivity;

public interface TimeSheetActivityService {
	
	List<TimeSheetActivity> findAll();
	
}
