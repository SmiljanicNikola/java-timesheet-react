import axios from 'axios'

const CATEGORIES_REST_API_URL = 'http://localhost:8080/api/categories';

class CategoryService {

    getCategories(){
        return axios.get(CATEGORIES_REST_API_URL);
    }

    getCategoriesPaginate(){
        return axios.get(CATEGORIES_REST_API_URL+"/paginate");
    }

    updateCategory(category, categoryId){
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