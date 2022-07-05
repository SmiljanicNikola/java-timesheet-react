package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vega.it.TimeSheetApp.DTO.TimeSheetActivityDTO;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

public class TimeSheetActivityController {

	@Autowired
	private TimeSheetActivityService timeSheetActivityService;
	
	@GetMapping
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimesheetActivities(){
		List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAll();
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = new ArrayList<>();
		for(TimeSheetActivity tsa : timesheetActivities) {
			timesheetActivitiesDTO.add(new TimeSheetActivityDTO(tsa));
		}
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TimeSheetActivityDTO> getTimeSheetActivityById(@PathVariable("id") Integer id){
		TimeSheetActivity timeSheetActivity = timeSheetActivityService.findOne(id);
		if(timeSheetActivity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new TimeSheetActivityDTO(timeSheetActivity), HttpStatus.OK);

	}
	
}
