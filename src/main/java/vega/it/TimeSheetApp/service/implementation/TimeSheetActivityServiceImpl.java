package vega.it.TimeSheetApp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
