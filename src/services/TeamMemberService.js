import axios from 'axios'

const TeamMembers_REST_API_URL = 'http://localhost:8080/api/teamMembers';

class TeamMemberService {

    getTeamMembers(){
        return axios.get(TeamMembers_REST_API_URL);
    }

    getTeamMembersPaginate(){
        return axios.get(TeamMembers_REST_API_URL+"/pagination");
    }

    updateTeamMember(teamMember, teamMemberId){
        return axios.put(TeamMembers_REST_API_URL+ '/' + teamMemberId, teamMember);
    }

    deleteTeamMember(teamMemberId){
        return axios.delete(TeamMembers_REST_API_URL+ "/" + teamMemberId);
    }

    createProject(teamMember){
        return axios.post(TeamMembers_REST_API_URL, teamMember);
    }

    getProjectById(teamMemberId){
        return axios.get(TeamMembers_REST_API_URL + '/' + teamMemberId);
    }
}

export default new TeamMemberService();