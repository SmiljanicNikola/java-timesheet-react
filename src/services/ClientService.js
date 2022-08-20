import axios from 'axios'
import httpClient from "../auth/JwtInterceptors"

const CLIENTS_REST_API_URL =  `${process.env.REACT_APP_BACKEND_URL}/api/clients`;

class ClientService {

    getClients(){
        return httpClient.get(CLIENTS_REST_API_URL);
    }

    getClientsPaginate(){
        return httpClient.get(CLIENTS_REST_API_URL+"/paginate");
    }

    getClientsPaginateWithParams(nextPage, size){
        return httpClient.get(CLIENTS_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    getClientsAssociatedWithTeamMemberPaginated(nextPage, size){
        return httpClient.get(CLIENTS_REST_API_URL+"/byTeamMemberUsername/paginated?page="+nextPage+"&size="+size);
    }

    updateClient(clientId, client){
        return httpClient.put(CLIENTS_REST_API_URL+ '/' + clientId, client);
    }

    deleteClient(clientId){
        return httpClient.delete(CLIENTS_REST_API_URL+ "/" + clientId);
    }

    createClient(client){
        return httpClient.post(CLIENTS_REST_API_URL, client);
    }

    getClientById(clientId){
        return httpClient.get(CLIENTS_REST_API_URL + '/' + clientId);
    }

    filterClientsByFirstLetters(letters){
        return httpClient.get(CLIENTS_REST_API_URL + '/filterBy/' + letters);
    }
}

export default new ClientService();