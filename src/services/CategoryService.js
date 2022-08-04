import axios from 'axios'
import httpClient from "../auth/JwtInterceptors"

const CATEGORIES_REST_API_URL =  `${process.env.REACT_APP_BACKEND_URL}/api/categories`;

class CategoryService {

    getCategories(){
        return httpClient.get(CATEGORIES_REST_API_URL);
    }

    getCategoriesPaginate(){
        return httpClient.get(CATEGORIES_REST_API_URL+"/paginate");
    }

    getCategoriesPaginateWithParams(nextPage, size){
        return httpClient.get(CATEGORIES_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }
    
    updateCategory(categoryId, category){
        return httpClient.put(CATEGORIES_REST_API_URL+ '/' + categoryId, category);
    }

    deleteCategory(categoryId){
        return httpClient.delete(CATEGORIES_REST_API_URL+ "/" + categoryId);
    }

    createCategory(category){
        return httpClient.post(CATEGORIES_REST_API_URL, category);
    }

    getCategoryById(categoryId){
        return httpClient.get(CATEGORIES_REST_API_URL + '/' + categoryId);
    }

    filterCategoriesByFirstLetters(letters){
        return httpClient.get(CATEGORIES_REST_API_URL + '/filterBy/' + letters);
    }
    
}

export default new CategoryService();