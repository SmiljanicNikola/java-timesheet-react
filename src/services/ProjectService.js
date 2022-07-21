import axios from 'axios'
import httpClient from "../auth/JwtInterceptors"

const PROJECTS_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/projects`;

class ProjectService {

    getProjects(){
        return axios.get(PROJECTS_REST_API_URL);
    }

    getProjectsPaginate(){
        return axios.get(PROJECTS_REST_API_URL+"/paginate");
    }

    getProjectsPaginateWithParams(nextPage, size){
        return axios.get(PROJECTS_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    updateProject(projectId, project){
        return axios.put(PROJECTS_REST_API_URL+ '/' + projectId, project);
    }

    deleteProject(projectId){
        return axios.delete(PROJECTS_REST_API_URL+ "/" + projectId);
    }

    createProject(project){
        return axios.post(PROJECTS_REST_API_URL, project);
    }

    getProjectById(projectId){
        return axios.get(PROJECTS_REST_API_URL + '/' + projectId);
    }

    filterProjectsByFirstLetters(letters){
        return axios.get(PROJECTS_REST_API_URL + '/filterBy/' + letters);
    }

}

export default new ProjectService();