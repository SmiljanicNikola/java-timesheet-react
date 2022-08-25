import httpClient from "../auth/JwtInterceptors"

const PROJECTS_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/projects`;

class ProjectService {

    getProjects(){
        return httpClient.get(PROJECTS_REST_API_URL);
    }

    getProjectsPaginate(){
        return httpClient.get(PROJECTS_REST_API_URL+"/paginate");
    }

    getProjectsPaginateWithParams(nextPage, size){
        return httpClient.get(PROJECTS_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    getProjectsByTeamMemberIdPaginated(teamMemberId,nextPage, size){
        return httpClient.get(PROJECTS_REST_API_URL+"/teamMemberId/"+teamMemberId+"/paginated?page="+nextPage+"&size="+size);
    }

    getProjectsByTeamMemberUsernamePaginated(nextPage, size){
        return httpClient.get(PROJECTS_REST_API_URL+"/byTeamMemberUsername/paginated?page="+nextPage+"&size="+size);
    }

    updateProject(projectId, project){
        return httpClient.put(PROJECTS_REST_API_URL+ '/' + projectId, project);
    }

    deleteProject(projectId){
        return httpClient.delete(PROJECTS_REST_API_URL+ "/" + projectId);
    }

    createProject(project){
        return httpClient.post(PROJECTS_REST_API_URL, project);
    }

    getProjectById(projectId){
        return httpClient.get(PROJECTS_REST_API_URL + '/' + projectId);
    }

    filterProjectsByFirstLetters(letters){
        return httpClient.get(PROJECTS_REST_API_URL + '/filterBy/' + letters);
    }

}

export default new ProjectService();