import axios from 'axios'

const TeamMembers_REST_API_URL = 'http://localhost:8080/api/teamMembers';

class TeamMemberService {

    getTeamMembers(){
        return axios.get(TeamMembers_REST_API_URL);
    }

    getTeamMembersPaginate(){
        return axios.get(TeamMembers_REST_API_URL+"pagination");
    }
}

export default new TeamMemberService();