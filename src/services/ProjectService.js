import axios from 'axios'

const PROJECTS_REST_API_URL = 'http://localhost:8080/api/projects';

class ClientService {

    getProjects(){
        return axios.get(PROJECTS_REST_API_URL);
    }

    getProjectsPaginate(){
        return axios.get(PROJECTS_REST_API_URL+"/paginate");
    }
}

export default new ClientService();