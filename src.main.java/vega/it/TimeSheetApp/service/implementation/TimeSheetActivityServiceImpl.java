package vega.it.TimeSheetApp.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.model.SearchObject;
import vega.it.TimeSheetApp.model.TimeSheetActivity;
import vega.it.TimeSheetApp.repository.TimeSheetActivityRepository;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

@Service
public class TimeSheetActivityServiceImpl implements TimeSheetActivityService {

	@Autowired
	private TimeSheetActivityRepository timeSheetActivityRepository;
	
	@Override
	public List<TimeSheetActivity> findAll() {
		return timeSheetActivityRepository.findAll();
	}

	@Override
	public TimeSheetActivity findById(Integer timeSheetActivityId) {
		return timeSheetActivityRepository.findById(timeSheetActivityId).orElse(null);
	}

	@Override
	public TimeSheetActivity save(TimeSheetActivity timeSheetActivity) {
		timeSheetActivityRepository.save(timeSheetActivity);
		return timeSheetActivity;
	}

	@Override
	public void remove(Integer id) {
		timeSheetActivityRepository.deleteById(id);
		
	}


	@Override
	public List<TimeSheetActivity> findAllByAllParameters(Integer projectId, Integer teamMemberId,
			Integer categoryId, LocalDate startDate, LocalDate endDate) {
		return timeSheetActivityRepository.findAllByThreeParameters(projectId, teamMemberId, categoryId, startDate, endDate);
	}

	@Override
	public List<TimeSheetActivity> findAllByDate(LocalDate date) {
		return timeSheetActivityRepository.findAllByDate(date);
	}

	@Override
	public List<TimeSheetActivity> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		return timeSheetActivityRepository.findAllBetweenStartDateAndEndDate(startDate, endDate);
	}

	@Override
	public List<TimeSheetActivity> findByTeamMemberId(Integer teamMemberId) {
		return timeSheetActivityRepository.findAllActivitiesByTeamMemberId(teamMemberId);

	}

	@Override
	public List<TimeSheetActivity> findAllBetweenStartDateAndEndDateAndTeamMemberId(LocalDate startDate,
			LocalDate endDate, Integer teamMemberId) {
		return timeSheetActivityRepository.findAllBetweenStartDateAndEndDateAndTeamMemberId(startDate, endDate, teamMemberId);
	}

	@Override
	public List<TimeSheetActivity> findAllBetweenStartDateAndEndDateAndTeamMemberUsername(LocalDate startDate,
			LocalDate endDate, String teamMemberUsername) {
		return timeSheetActivityRepository.findAllBetweenStartDateAndEndDateAndTeamMemberUsername(startDate, endDate, teamMemberUsername);
	}
	
}
