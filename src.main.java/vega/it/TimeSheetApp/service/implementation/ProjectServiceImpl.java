package vega.it.TimeSheetApp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.Project;
import vega.it.TimeSheetApp.repository.ProjectRepository;
import vega.it.TimeSheetApp.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<Project> findAll() {
		return projectRepository.findAll().stream().filter(project -> project.getDeleted() == false).collect(Collectors.toList());
	}

	@Override
	public Project findById(Integer projectId) {
		return projectRepository.findById(projectId).orElse(null);
		
	}

	@Override
	public Project save(Project project) {
		projectRepository.save(project);
		return project;
	}

	@Override
	public void remove(Integer id) {
		projectRepository.deleteById(id);
		
	}

	@Override
	public Page<Project> findAll(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	@Override
	public Page<Project> findAllProjectsPaginate(Pageable pageable) {
		return projectRepository.findAllProjectsWithPagination(pageable);
	}

	@Override
	public List<Project> filterAllProjectsByFirstLetter(String letter) {
		return projectRepository.filterAllProjectsByFirstLetter(letter);
	}

	@Override
	public Page<Project> findAllProjectsPaginatedByTeamMemberId( Integer teamMemberId,Pageable pageable) {
		return projectRepository.findAllProjectsWithPaginationByTeamMemberId(pageable, teamMemberId);
	}

	@Override
	public Page<Project> findAllProjectsPaginatedByTeamMemberUsername(String teamMemberUsername, Pageable pageable) {
		return projectRepository.findAllProjectsWithPaginationByTeamMemberUsername(pageable, teamMemberUsername);
	}

}
