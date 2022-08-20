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

import vega.it.TimeSheetApp.DTO.AddActivityDTORequest;
import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.DayDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.DTO.ReportDTO;
import vega.it.TimeSheetApp.DTO.TeamMemberDTO;
import vega.it.TimeSheetApp.DTO.TimeSheetActivityDTO;
import vega.it.TimeSheetApp.model.ActivitiesPDFExporter;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.model.Report;
import vega.it.TimeSheetApp.model.ReportPDFExporter;
import vega.it.TimeSheetApp.model.ReportsPDFExporter;
import vega.it.TimeSheetApp.model.SearchObject;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.service.CategoryService;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.DayService;
import vega.it.TimeSheetApp.service.ProjectService;
import vega.it.TimeSheetApp.service.TeamMemberService;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

@RestController
@RequestMapping(value = "api/timeSheetActivities")
public class TimeSheetActivityController {

	@Autowired
	private TimeSheetActivityService timeSheetActivityService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DayService dayService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	
	@GetMapping
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimesheetActivities(){
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService
				.findAll()
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.collect(Collectors.toList());
		
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
	
	@GetMapping(value="/teamMemberId/{teamMemberId}")//Dodao / u value path
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByTeamMemberId(@PathVariable("teamMemberId") Integer teamMemberId){
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService.findByTeamMemberId(teamMemberId)
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="byDate/{date}")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByDate(
		@RequestParam(name = "date")
	       @DateTimeFormat(iso = ISO.DATE)
	       LocalDate date){
		
		//List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAll().stream().filter(tsa -> tsa.getDate().toString().equals(date.toString())).collect(Collectors.toList());
		List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAllByDate(date);
		List<TimeSheetActivityDTO> timeSheetActivitesDTO = timesheetActivities.stream().map(tsa -> new TimeSheetActivityDTO(tsa)).collect(Collectors.toList());
        return new ResponseEntity<>(timeSheetActivitesDTO, HttpStatus.OK);

	}
	
	
	@GetMapping(value="/searchBetweenDates")
	public ResponseEntity<List<DayDTO>> getTimeSheetActivityBetweenStartDateAndEndDate(
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
		
		List<DayDTO> daysDTO = dayService.findAllBetweenStartDateAndEndDate(startDate, endDate);
		
		return new ResponseEntity<>(daysDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/searchBetweenDatesAndMemberId")
	public ResponseEntity<List<DayDTO>> getTimeSheetActivityBetweenStartDateAndEndDateAndTeamMemberId(
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(required=false) Integer teamMemberId){
		
		List<DayDTO> daysDTO = dayService.findAllBetweenStartDateAndEndDateAndTeamMemberId(startDate, endDate, teamMemberId);
		
		return new ResponseEntity<>(daysDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/searchBetweenDatesAndMemberUsername")
	public ResponseEntity<List<DayDTO>> getTimeSheetActivityBetweenStartDateAndEndDateAndTeamMemberUsername(
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(required=false) String teamMemberUsername){
		
		List<DayDTO> daysDTO = dayService.findAllBetweenStartDateAndEndDateAndTeamMemberUsername(startDate, endDate, teamMemberUsername);
		
		return new ResponseEntity<>(daysDTO, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/search")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByThreeParameterss(
			@RequestParam(required=false) Integer projectId, 
			@RequestParam(required=false) Integer teamMemberId, 
			@RequestParam(required=false) Integer categoryId,
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
			){
		
		List<TimeSheetActivityDTO> timesheetActivitiesDTO = timeSheetActivityService.findAllByThreeParameters(projectId, teamMemberId, categoryId, startDate, endDate)
				.stream()
				.map(tsa -> new TimeSheetActivityDTO(tsa))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(timesheetActivitiesDTO, HttpStatus.OK);

	}
	
	@PostMapping()
    public ResponseEntity<TimeSheetActivityDTO> saveTimeSheetActivity(@RequestBody AddActivityDTORequest addActivityDTORequest) {
		
		TimeSheetActivity timeSheetActivity = new TimeSheetActivity();
		timeSheetActivity.setDescription(addActivityDTORequest.getDescription());
		timeSheetActivity.setProject(this.projectService.findById(addActivityDTORequest.getProjectId()));
		timeSheetActivity.setTeamMember(this.teamMemberService.findById(addActivityDTORequest.getTeamMemberId()));
		timeSheetActivity.setCategory(this.categoryService.findById(addActivityDTORequest.getCategoryId()));
		timeSheetActivity.setTime(addActivityDTORequest.getTime());
		timeSheetActivity.setOvertime(addActivityDTORequest.getOvertime());
		timeSheetActivity.setDate(addActivityDTORequest.getDate());
      
        timeSheetActivity = timeSheetActivityService.save(timeSheetActivity);
        return new ResponseEntity<>(new TimeSheetActivityDTO(timeSheetActivity), HttpStatus.CREATED);
	        	
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
	
	@GetMapping(value="searchByDate/{date}")
	public ResponseEntity<List<TimeSheetActivityDTO>> getTimeSheetActivityByDate2(
		@PathVariable(name = "date")
	       @DateTimeFormat(iso = ISO.DATE)
	       LocalDate date){
		
		//List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAll().stream().filter(tsa -> tsa.getDate().toString().equals(date.toString())).collect(Collectors.toList());
		List<TimeSheetActivity> timesheetActivities = timeSheetActivityService.findAllByDate(date);
		List<TimeSheetActivityDTO> timeSheetActivitesDTO = timesheetActivities.stream().map(tsa -> new TimeSheetActivityDTO(tsa)).collect(Collectors.toList());
        return new ResponseEntity<>(timeSheetActivitesDTO, HttpStatus.OK);

	}
	
	
	@PostMapping("/reports/export")
    public void exportToPDFKlijent(HttpServletResponse response,@RequestBody Report report) throws DocumentException, IOException {
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


    	ReportPDFExporter exporter = new ReportPDFExporter(report);
    	exporter.export(response);
    }
	
	@PostMapping("/export")
    public void exportToPDF(HttpServletResponse response,@RequestBody List<TimeSheetActivity> timeSheetActivities) throws DocumentException, IOException {
    	response.setContentType("application/pdf");
    	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String currentDateTime = dateFormatter.format(new Date());
    	
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=reports_" + currentDateTime + ".pdf";
    	
    	response.setHeader(headerKey, headerValue);
    	
    	//List<TimeSheetActivity> listOfActivities = timeSheetActivityService.fin

    	/*List<IzlaznaFaktura> listaIzlaznihFakturaPartnera = new ArrayList<IzlaznaFaktura>();
    	
    	for(IzlaznaFaktura izlazna : listaIzlaznihFaktura) {
    		if(izlazna.getPoslovniPartner().getId() == id) {
    			listaIzlaznihFakturaPartnera.add(izlazna);
    		}
    		
    	}*/
    	/*System.out.println(listaIzlaznihFakturaPartnera);
        log.debug("REST request to delete IzlaznaFaktura : {}", listaIzlaznihFakturaPartnera);*/


    	ActivitiesPDFExporter exporter = new ActivitiesPDFExporter(timeSheetActivities);
    	exporter.export(response);
    }
	
	
	
	
	
}
