import axios from 'axios'

const TIMESHEET_REST_APLI_URL = 'http://localhost:8080/api/timeSheetActivities';

class TimeSheetActivityService {

    getTimeSheets(){
        return axios.get(TIMESHEET_REST_APLI_URL);
    }

    getTimeSheetsByProjectId(projectId){
        return axios.get(TIMESHEET_REST_APLI_URL+"/projectId/"+projectId);
    }

    getTimeSheetsByThreeParameters(projectId,teamMemberId, categoryId){
        return axios.get(TIMESHEET_REST_APLI_URL+"/projectId/"+projectId+"/teamMemberId/" + teamMemberId + "/categoryId/" + categoryId);
    }
}

export default new TimeSheetActivityService();