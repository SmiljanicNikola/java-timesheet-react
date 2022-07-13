package vega.it.TimeSheetApp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ReportDTO;
import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.DTO.TimeSheetActivityDTO;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.ReportsPDFExporter;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

@RestController
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
	
	/*@GetMapping(value="byDate/{date}")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByDate(@PathVariable("date") LocalDate date){
		//LocalDate specificDate = LocalDate.parse(date); 
		List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAllByDate(date);
		
		List<TimeSheetActivityDTO> timeSheetActivitesDTO = timesheetActivities.stream().map(tsa -> new TimeSheetActivityDTO(tsa)).toList();
        return new ResponseEntity<>(timeSheetActivitesDTO, HttpStatus.OK);

	}*/
	
	@GetMapping(value="byDate/{date}")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByDate(
		@RequestParam(name = "date")
	       @DateTimeFormat(iso = ISO.DATE)
	       LocalDate date){
		
		//List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAll().stream().filter(tsa -> tsa.getDate().toString().equals(date.toString())).collect(Collectors.toList());
		List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAllByDate(date);
		List<TimeSheetActivityDTO> timeSheetActivitesDTO = timesheetActivities.stream().map(tsa -> new TimeSheetActivityDTO(tsa)).toList();
        return new ResponseEntity<>(timeSheetActivitesDTO, HttpStatus.OK);

	}
	
	@GetMapping(value="/projectId/{projectId}")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByProjectId(@PathVariable("projectId") Integer projectId){
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService.findAllByProjectId(projectId)
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.toList();
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);

	}
	
	@GetMapping(value="/search")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByThreeParameters(
			@RequestParam(required=false) Integer projectId, 
			@RequestParam(required=false) Integer teamMemberId, 
			@RequestParam(required=false) Integer categoryId){
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService.findAllByThreeParameters(projectId, teamMemberId, categoryId)
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.toList();
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);

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
	
	@PostMapping("/reports/export/{id}")
    public void exportToPDFKlijent(HttpServletResponse response,@RequestBody ReportDTO reportDTO) throws DocumentException, IOException {
    	response.setContentType("application/pdf");
    	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String currentDateTime = dateFormatter.format(new Date());
    	
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=reports_" + currentDateTime + ".pdf";
    	
    	response.setHeader(headerKey, headerValue);
    	
    	/*List<IzlaznaFaktura> listaIzlaznihFaktura = izlaznaFakturaService.findAll();

    	List<IzlaznaFaktura> listaIzlaznihFakturaPartnera = new ArrayList<IzlaznaFaktura>();
    	
    	for(IzlaznaFaktura izlazna : listaIzlaznihFaktura) {
    		if(izlazna.getPoslovniPartner().getId() == id) {
    			listaIzlaznihFakturaPartnera.add(izlazna);
    		}
    		
    	}*/
    	/*System.out.println(listaIzlaznihFakturaPartnera);
        log.debug("REST request to delete IzlaznaFaktura : {}", listaIzlaznihFakturaPartnera);*/


    	/*ReportsPDFExporter exporter = new ReportsPDFExporter(reportDTO);
    	exporter.export(response);*/
    }
	
	
	
}
