package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vega.it.TimeSheetApp.DTO.AddProjectRequestDTO;
import vega.it.TimeSheetApp.DTO.ClientDTO;
import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.exceptions.ResourceNotFoundException;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.ProjectService;
import vega.it.TimeSheetApp.service.TeamMemberService;

@RestController
@RequestMapping(value = "api/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getProjects(){
		
		List<ProjectDTO> projectsDTO = projectService
				.findAll()
				.stream()
				.map(p -> new ProjectDTO(p))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/paginate")
	public ResponseEntity<Page<Project>> findAll(Pageable pageable){
		
		return new ResponseEntity<>(projectService.findAllProjectsPaginate(pageable), HttpStatus.OK);	
		
	}
	
	@GetMapping(value="filterBy/{letter}")
	public ResponseEntity<List<ProjectDTO>> getProjectByFirstLetter(@PathVariable("letter") String letter){
		
			List<ProjectDTO> projectsDTO = projectService.filterAllProjectsByFirstLetter(letter).stream().map(p -> new ProjectDTO(p)).collect(Collectors.toList());
			
			
	        return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Integer id){
		try 
		{	
			Project project = projectService.findById(id);
	        return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
		}
		catch(ResourceNotFoundException resourceNotFoundException) 
		{	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with that id: "+ id ,resourceNotFoundException);	
		}
	}
	
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try
		{	
	        Project project = projectService.findById(id);
	        project.setDeleted(true);
	    	projectService.save(project);
	        return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(ResourceNotFoundException resourceNotFoundException) 
		{	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamMember with that id: "+ id ,resourceNotFoundException);	
		}    
	}
    
	
	@PostMapping()
    public ResponseEntity<ProjectDTO> saveProject(@RequestBody AddProjectRequestDTO addProjectRequestDTO) {
		
        Project project = new Project();
        project.setDescription(addProjectRequestDTO.getDescription());
        project.setProjectName(addProjectRequestDTO.getProjectName());
        project.setClient(this.clientService.findById(addProjectRequestDTO.getClientId()));
        project.setLead(this.teamMemberService.findById(addProjectRequestDTO.getTeamMemberId()));
        project.setFinished(false);
        project.setDeleted(false);

        project = projectService.save(project);
        return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.CREATED);
	        	
	}
	
	
	@PutMapping(value = "/{id}")
	    public ResponseEntity<ProjectDTO> updateProject(@RequestBody AddProjectRequestDTO addProjectRequestDTO, @PathVariable("id") Integer id) {
		
	        Project project = projectService.findById(id);

	        if (project == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        
	        if(addProjectRequestDTO.getDescription() != null) {
	        	project.setDescription(addProjectRequestDTO.getDescription());
	        }
	        
	        if(addProjectRequestDTO.getProjectName() != null) {
	        	project.setProjectName(addProjectRequestDTO.getProjectName());
	        }
	        
	        if(addProjectRequestDTO.getClientId() != null) {
		        project.setClient(this.clientService.findById(addProjectRequestDTO.getClientId()));
	        }
	        
	        if(addProjectRequestDTO.getTeamMemberId() != null) {
		        project.setLead(this.teamMemberService.findById(addProjectRequestDTO.getTeamMemberId()));
	        }

	        project = projectService.save(project);

	        return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
	        
	    }

}