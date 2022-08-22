package vega.it.TimeSheetApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vega.it.TimeSheetApp.DTO.ProjectDTO;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.model.Project;

public interface ProjectService {
	
	List<Project> findAll();
	
	Project findById(Integer projectId);
	
	Project save (Project project);
	
	void remove(Integer id);
	
	Page<Project> findAll(Pageable pageable);
	
	Page<Project> findAllProjectsPaginate(Pageable pageable);
	
	Page<Project> findAllProjectsPaginatedByTeamMemberId(Integer teamMemberId,Pageable pageable);

	Page<Project> findAllProjectsPaginatedByTeamMemberUsername(String teamMemberUsername,Pageable pageable);

	List<Project> filterAllProjectsByFirstLetter(String letter);

}
