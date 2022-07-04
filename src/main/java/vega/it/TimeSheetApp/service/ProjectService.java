package vega.it.TimeSheetApp.service;

import java.util.List;

import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;

public interface ProjectService {
	
	List<Project> findAll();
	
	Project findOne(Integer projectId);
	
	Project save (Project project);
	
	void remove(Integer id);

}
