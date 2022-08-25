import httpClient from "../auth/JwtInterceptors"

const TIMESHEET_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/timeSheetActivities`;

class TimeSheetActivityService {

    getTimeSheets(){
        return httpClient.get(TIMESHEET_REST_API_URL);
    }

    getTimeSheetsByProjectId(projectId){
        return httpClient.get(TIMESHEET_REST_API_URL+"/projectId/"+projectId);
    }

    getTimeSheetsByDate(date){
        return httpClient.get(TIMESHEET_REST_API_URL+"/searchByDate/"+date);
    }

    getTimeSheetsByTeamMemberId(teamMemberId){
        return httpClient.get(TIMESHEET_REST_API_URL+"/teamMemberId/"+teamMemberId);
    }

    createTimesheetActivity(timeSheetActivity){
        return httpClient.post(TIMESHEET_REST_API_URL, timeSheetActivity);
    }

    searchTimeSheetsActivities(projectId,teamMemberId, categoryId, startDate, endDate){
        return httpClient.get(TIMESHEET_REST_API_URL+"/search?" + "projectId="+projectId+"&teamMemberId=" + teamMemberId + "&categoryId=" + categoryId + "&startDate=" + startDate + "&endDate=" + endDate);
    }

    exportPDFReport(timeSheets){
        return httpClient.post(TIMESHEET_REST_API_URL+"/export", timeSheets,{
            params: {
                cacheBustTimestamp: Date.now(),
            },
            responseType: 'blob',
            timeout: 120,
        });
    }

    searchByDate(date){
        return httpClient.get(TIMESHEET_REST_API_URL+"/searchByDate/"+date);
    }

    searchByReportCriteria(){
        return httpClient.get(TIMESHEET_REST_API_URL+"/search?");
    }

    getTimeSheetsBetweenStartDateAndEndDate(startDate, endDate){
        return httpClient.get(TIMESHEET_REST_API_URL+"/searchBetweenDates?startDate="+startDate+"&endDate="+endDate)
    }

    getTimeSheetsBetweenStartDateAndEndDateAndTeamMemberId(startDate, endDate, teamMemberId){
        return httpClient.get(TIMESHEET_REST_API_URL+"/searchBetweenDatesAndMemberId?startDate="+startDate+"&endDate="+endDate+"&teamMemberId="+teamMemberId);
    }

    getTimeSheetsBetweenStartDateAndEndDateAndTeamMemberUsername(startDate, endDate){
        return httpClient.get(TIMESHEET_REST_API_URL+"/searchBetweenDatesAndMemberUsername?startDate="+startDate+"&endDate="+endDate);
    }

    emptySearchTimeSheets(){
        return httpClient.get(TIMESHEET_REST_API_URL+"/search?");
    }
    
}

export default new TimeSheetActivityService();