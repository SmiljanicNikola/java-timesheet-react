import axios from 'axios'

const CATEGORIES_REST_API_URL =  `${process.env.REACT_APP_BACKEND_URL}/api/categories`;

class CategoryService {

    getCategories(){
        return axios.get(CATEGORIES_REST_API_URL);
    }

    getCategoriesPaginate(){
        return axios.get(CATEGORIES_REST_API_URL+"/paginate");
    }

    getCategoriesPaginateWithParams(nextPage, size){
        return axios.get(CATEGORIES_REST_API_URL+"/paginate?page="+nextPage+"&size="+size);
    }
    
    updateCategory(categoryId, category){
        return axios.put(CATEGORIES_REST_API_URL+ '/' + categoryId, category);
    }

    deleteCategory(categoryId){
        return axios.delete(CATEGORIES_REST_API_URL+ "/" + categoryId);
    }

    createCategory(category){
        return axios.post(CATEGORIES_REST_API_URL, category);
    }

    getCategoryById(categoryId){
        return axios.get(CATEGORIES_REST_API_URL + '/' + categoryId);
    }
}

export default new CategoryService();