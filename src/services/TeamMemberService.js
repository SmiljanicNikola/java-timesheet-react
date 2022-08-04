import axios from 'axios'
import httpClient from "../auth/JwtInterceptors"


const TeamMembers_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/teamMembers`;

class TeamMemberService {

    getTeamMembers(){
        return httpClient.get(TeamMembers_REST_API_URL);
    }

    getTeamMembersPaginate(){
        return httpClient.get(TeamMembers_REST_API_URL+"/paginate");
    }

    getMembersPaginateWithParams(nextPage, size){
        return httpClient.get(TeamMembers_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    updateTeamMember(teamMemberId, teamMember){
        return httpClient.put(TeamMembers_REST_API_URL+ '/' + teamMemberId, teamMember);
    }

    deleteTeamMember(teamMemberId){
        return httpClient.delete(TeamMembers_REST_API_URL+ "/" + teamMemberId);
    }

    createTeamMember(teamMember){
        return httpClient.post(TeamMembers_REST_API_URL, teamMember);
    }

    getTeamMemberById(teamMemberId){
        return httpClient.get(TeamMembers_REST_API_URL + '/' + teamMemberId);
    }

    getTeamMemberByUsername(teamMemberUsername){
        return httpClient.get(TeamMembers_REST_API_URL + '/username/' + teamMemberUsername);
    }
}

export default new TeamMemberService();