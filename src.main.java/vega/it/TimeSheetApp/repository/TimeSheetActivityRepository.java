package vega.it.TimeSheetApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vega.it.TimeSheetApp.model.SearchObject;
import vega.it.TimeSheetApp.model.TimeSheetActivity;

@Repository
public interface TimeSheetActivityRepository extends JpaRepository<TimeSheetActivity, Integer> {

	
	@Query(value= "SELECT * FROM timesheet_activity where timesheet_activity.project_id = :projectId",nativeQuery = true)
	List<TimeSheetActivity> findAllByProjectId(Integer projectId);
	
	
	@Query(value= "SELECT * FROM timesheet_activity where timesheet_activity.team_member_id = :teamMemberId",nativeQuery = true)
	List<TimeSheetActivity> findAllActivitiesByTeamMemberId(Integer teamMemberId);
	
	
	@Query(value= "SELECT * FROM timesheet_activity where timesheet_activity.project_id = :projectId "
			+ "and(:teamMemberId is null or timesheet_activity.team_member_id = :teamMemberId) "
			+ "and (:categoryId is null or timesheet_activity.category_id = :categoryId) "
			+ "and (:startDate is null or timesheet_activity.date >= :startDate) "
			+ "and (:endDate is null or timesheet_activity.date <= :endDate)",nativeQuery = true)
	List<TimeSheetActivity> findAllByThreeParameters(@Param("projectId")Integer projectId, 
													@Param("teamMemberId")Integer teamMemberId,
													@Param("categoryId")Integer categoryId,
													@Param("startDate") LocalDate startDate,
													@Param("endDate") LocalDate endDate);
	
	@Query(value= "SELECT * FROM timesheet_activity where timesheet_activity.date = :date",nativeQuery = true)
	List<TimeSheetActivity> findAllByDate(LocalDate date);
	
	
	@Query(value= "SELECT * FROM timesheet_activity where (:startDate is null or timesheet_activity.date >= :startDate) and (:endDate is null or timesheet_activity.date <= :endDate)",nativeQuery = true)
	List<TimeSheetActivity> findAllBetweenStartDateAndEndDate(@Param("startDate") LocalDate startDate,
															@Param("endDate") LocalDate endDate);
	
	@Query(value= "SELECT * FROM timesheet_activity where (:startDate is null or timesheet_activity.date >= :startDate) and (:endDate is null or timesheet_activity.date <= :endDate) and (:teamMemberId is null or timesheet_activity.team_member_id = :teamMemberId)",nativeQuery = true)
	List<TimeSheetActivity> findAllBetweenStartDateAndEndDateAndTeamMemberId(@Param("startDate") LocalDate startDate,
															@Param("endDate") LocalDate endDate,
															@Param("teamMemberId") Integer teamMemberId);
	
	 
}