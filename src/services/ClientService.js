import axios from 'axios'

const CLIENTS_REST_API_URL = 'http://localhost:8080/api/clients';

class ClientService {

    getClients(){
        return axios.get(CLIENTS_REST_API_URL);
    }

    getClientsPaginate(){
        return axios.get(CLIENTS_REST_API_URL+"/paginate");
    }

    updateClient(client, clientId){
        return axios.put(CLIENTS_REST_API_URL+ '/' + clientId, client);
    }

    deleteClient(clientId){
        return axios.delete(CLIENTS_REST_API_URL+ "/" + clientId);
    }

    createClient(client){
        return axios.post(CLIENTS_REST_API_URL, client);
    }

    getClientById(clientId){
        return axios.get(CLIENTS_REST_API_URL + '/' + clientId);
    }
}

export default new ClientService();