import httpClient from "../auth/JwtInterceptors"

const COUNTRY_REST_API_URL = `${process.env.REACT_APP_BACKEND_URL}/api/countries`;

class CountryService {

    getCountries(){
        return httpClient.get(COUNTRY_REST_API_URL);
    }

}

export default new CountryService();