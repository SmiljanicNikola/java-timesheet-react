package vega.it.TimeSheetApp.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vega.it.TimeSheetApp.DTO.DayDTO;
import vega.it.TimeSheetApp.repository.CountryRepository;
import vega.it.TimeSheetApp.service.DayService;
import vega.it.TimeSheetApp.service.TimeSheetActivityService;

@Service
public class DayServiceImpl implements DayService {
	
	@Autowired
	private TimeSheetActivityService timeSheetActivityService;

	@Override
	public List<DayDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DayDTO> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		
		List<DayDTO> daysDTO = timeSheetActivityService.findAllBetweenStartDateAndEndDate(startDate, endDate)
				.stream()
				.map(tsa -> new DayDTO(tsa.getTime(), tsa.getOvertime(), tsa.getDate()))
				.toList();
	
		
			Integer time = 0;
			
			/*for(DayDTO dayDTO : daysDTO) {
				if(dayDTO.getDate() == dayDTO.getDate()) {
					time += dayDTO.getTime();
					dayDTO.setTime(time);
				}
			}*/
			System.out.println("bla");
			for(int i = 0; i < daysDTO.size(); i++ ) {
				for(int j = daysDTO.size()-1; j > 0; j--) {
					if(daysDTO.get(i).getDate() == daysDTO.get(j).getDate()) {
						System.out.println(daysDTO.get(i).getDate());
						System.out.println(daysDTO.get(j).getDate());

						System.out.println(daysDTO.get(i));
						System.out.println("SIZE:"+daysDTO.size());
						System.out.println("TIME OF J:"+daysDTO.get(j).getTime());
						System.out.println(daysDTO.get(j));
						System.out.println("TIME OF I:"+daysDTO.get(i).getTime());
						System.out.println(daysDTO.get(i));

						daysDTO.get(i).setTime(daysDTO.get(i).getTime() + daysDTO.get(j).getTime());
						System.out.println(daysDTO.get(i).getTime());
						daysDTO.get(j).setTime(0);
	
					}else {
						System.out.println("PRAZNO");
					}
				}
			}
		
		
		return daysDTO;
		
	}

}
