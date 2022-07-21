import axios from 'axios'


const TeamMembers_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/teamMembers`;

class TeamMemberService {

    getTeamMembers(){
        return axios.get(TeamMembers_REST_API_URL);
    }

    getTeamMembersPaginate(){
        return axios.get(TeamMembers_REST_API_URL+"/paginate");
    }

    getMembersPaginateWithParams(nextPage, size){
        return axios.get(TeamMembers_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    updateTeamMember(teamMemberId, teamMember){
        return axios.put(TeamMembers_REST_API_URL+ '/' + teamMemberId, teamMember);
    }

    deleteTeamMember(teamMemberId){
        return axios.delete(TeamMembers_REST_API_URL+ "/" + teamMemberId);
    }

    createTeamMember(teamMember){
        return axios.post(TeamMembers_REST_API_URL, teamMember);
    }

    getTeamMemberById(teamMemberId){
        return axios.get(TeamMembers_REST_API_URL + '/' + teamMemberId);
    }
}

export default new TeamMemberService();