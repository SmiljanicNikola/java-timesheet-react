package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.service.ProjectService;

@RestController
@RequestMapping(value = "api/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getProjects(){
		List<Project> projects = projectService.findAll();
		
		List<ProjectDTO> projectsDTO = new ArrayList<>();
		for(Project p : projects) {
			projectsDTO.add(new ProjectDTO(p));
		}
		
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Integer id){
		Project project = projectService.findOne(id);
		if(project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
        return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);

	}
	

}
