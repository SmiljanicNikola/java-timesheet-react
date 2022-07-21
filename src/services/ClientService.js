import axios from 'axios'

const CLIENTS_REST_API_URL =  `${process.env.REACT_APP_BACKEND_URL}/api/clients`;

class ClientService {

    getClients(){
        return axios.get(CLIENTS_REST_API_URL);
    }

    getClientsPaginate(){
        return axios.get(CLIENTS_REST_API_URL+"/paginate");
    }

    getClientsPaginateWithParams(nextPage, size){
        return axios.get(CLIENTS_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }

    updateClient(clientId, client){
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

    filterClientsByFirstLetters(letters){
        return axios.get(CLIENTS_REST_API_URL + '/filterBy/' + letters);
    }
}

export default new ClientService();