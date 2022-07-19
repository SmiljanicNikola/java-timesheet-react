import axios from 'axios'

const TIMESHEET_REST_APLI_URL = `${process.env.REACT_APP_BACKEND_URL}/api/timeSheetActivities`;

class TimeSheetActivityService {

    getTimeSheets(){
        return axios.get(TIMESHEET_REST_APLI_URL);
    }

    getTimeSheetsByProjectId(projectId){
        return axios.get(TIMESHEET_REST_APLI_URL+"/projectId/"+projectId);
    }

    getTimeSheetsByDate(date){
        return axios.get(TIMESHEET_REST_APLI_URL+"/searchByDate/"+date);
    }

    getTimeSheetsByThreeParameters(projectId,teamMemberId, categoryId){
        return axios.get(TIMESHEET_REST_APLI_URL+"/projectId/"+projectId+"/teamMemberId/" + teamMemberId + "/categoryId/" + categoryId);
    }

    exportPDFReport(report){
        return axios.post(TIMESHEET_REST_APLI_URL+"/reports/export", report);
    }

    searchByDate(date){
        return axios.get(TIMESHEET_REST_APLI_URL+"/searchByDate/"+date);
    }

    searchByReportCriteria(){
        return axios.get(TIMESHEET_REST_APLI_URL+"/search?");
    }

    getTimeSheetsBetweenStartDateAndEndDate(startDate, endDate){
        return axios.get(TIMESHEET_REST_APLI_URL+"/searchBetweenDates?startDate="+startDate+"&endDate="+endDate)
    }
}

export default new TimeSheetActivityService();