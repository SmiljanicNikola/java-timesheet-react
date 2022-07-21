import axios from 'axios'

const TIMESHEET_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/timeSheetActivities`;

class TimeSheetActivityService {

    getTimeSheets(){
        return axios.get(TIMESHEET_REST_API_URL);
    }

    getTimeSheetsByProjectId(projectId){
        return axios.get(TIMESHEET_REST_API_URL+"/projectId/"+projectId);
    }

    getTimeSheetsByDate(date){
        return axios.get(TIMESHEET_REST_API_URL+"/searchByDate/"+date);
    }

    createTimesheetActivity(timeSheetActivity){
        return axios.post(TIMESHEET_REST_API_URL, timeSheetActivity);
    }

    getTimeSheetsByThreeParameters(projectId,teamMemberId, categoryId){
        return axios.get(TIMESHEET_REST_API_URL+"/projectId/"+projectId+"/teamMemberId/" + teamMemberId + "/categoryId/" + categoryId);
    }

    exportPDFReport(report){
        return axios.post(TIMESHEET_REST_API_URL+"/reports/export", report);
    }

    searchByDate(date){
        return axios.get(TIMESHEET_REST_API_URL+"/searchByDate/"+date);
    }

    searchByReportCriteria(){
        return axios.get(TIMESHEET_REST_API_URL+"/search?");
    }

    getTimeSheetsBetweenStartDateAndEndDate(startDate, endDate){
        return axios.get(TIMESHEET_REST_API_URL+"/searchBetweenDates?startDate="+startDate+"&endDate="+endDate)
    }

    emptySearchTimeSheets(){
        return axios.get(TIMESHEET_REST_API_URL+"/search?");
    }
    
}

export default new TimeSheetActivityService();