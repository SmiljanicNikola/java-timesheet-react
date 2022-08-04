package vega.it.TimeSheetApp.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.TimeSheetApp.DTO.DayDTO;
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
	
	public Set<DayDTO> findDuplicateWithStream(List<DayDTO> list)
    {
        return list.stream().distinct()
                .filter(i -> Collections.frequency(list, i) > 1)
                .collect(Collectors.toSet());
    }
	
	public Set<DayDTO> findNonDuplicates(List<DayDTO> list)
    {
        Set<DayDTO> duplicates = new HashSet<>();
        Set<DayDTO> nonDuplicates = new HashSet<>();

        Set<DayDTO> seen = new HashSet<>();
 
        for (DayDTO day : list) {
            if (seen.add(day)) {
            	nonDuplicates.add(day);
            }
        }
 
        return nonDuplicates;
    }

	@Override
	public List<DayDTO> findAllBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		
		List<DayDTO> daysDTO = timeSheetActivityService.findAllBetweenStartDateAndEndDate(startDate, endDate)
				.stream()
				.map(tsa -> new DayDTO(tsa.getTime(), tsa.getOvertime(), tsa.getDate()))
				.collect(Collectors.toList());
				
		Set<DayDTO> nonDuplicates = findDuplicateWithStream(daysDTO);
		List<DayDTO> targetList = new ArrayList<>(nonDuplicates);

		System.out.println("Duplicates");
		System.out.println(nonDuplicates);
		
		for(DayDTO dayDTO : targetList) {
			System.out.println(dayDTO);
		}

	
		/*for(int i = 0; i < daysDTO.size(); i++ ) {
			for(int j = daysDTO.size()-1; j > 0; j--) {
				if(daysDTO.get(i).getDate() == daysDTO.get(j).getDate()) {
					daysDTO.get(i).setTime(daysDTO.get(i).getTime() + daysDTO.get(j).getTime());
					System.out.println(daysDTO.get(i).getTime());
					daysDTO.get(j).setTime(0);
				}else {
					System.out.println("PRAZNO");
				}
			}
		}*/
		
		return daysDTO;
			
	}

	@Override
	public List<DayDTO> findAllBetweenStartDateAndEndDateAndTeamMemberId(LocalDate startDate, LocalDate endDate,
			Integer teamMemberId) {
		
		List<DayDTO> daysDTO = timeSheetActivityService.findAllBetweenStartDateAndEndDateAndTeamMemberId(startDate, endDate, teamMemberId)
				.stream()
				.map(tsa -> new DayDTO(tsa.getTime(), tsa.getOvertime(), tsa.getDate()))
				.collect(Collectors.toList());
	
		
		return daysDTO;
	}
	
}
