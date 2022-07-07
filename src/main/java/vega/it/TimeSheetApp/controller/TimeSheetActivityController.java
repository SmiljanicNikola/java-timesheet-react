package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.TimeSheetActivityDTO;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "api/timeSheetActivities")
public class TimeSheetActivityController {

	@Autowired
	private TimeSheetActivityService timeSheetActivityService;
	
	@GetMapping
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimesheetActivities(){
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService
				.findAll()
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.toList();
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TimeSheetActivityDTO> getTimeSheetActivityById(@PathVariable("id") Integer id){
		TimeSheetActivity timeSheetActivity = timeSheetActivityService.findById(id);
		if(timeSheetActivity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new TimeSheetActivityDTO(timeSheetActivity), HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		TimeSheetActivity timeSheetActivity = timeSheetActivityService.findById(id);
		
        if (timeSheetActivity == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        
        timeSheetActivityService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	
}
