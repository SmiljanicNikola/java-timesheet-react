import axios from 'axios'

const TIMESHEET_REST_APLI_URL = 'http://localhost:8080/api/timeSheetActivities';

class TimeSheetActivityService {

    getTeamMembers(){
        return axios.get(TIMESHEET_REST_APLI_URL);
    }

    getTeamMembersPaginate(){
        return axios.get(TIMESHEET_REST_APLI_URL+"/pagination");
    }
}

export default new TimeSheetActivityService();