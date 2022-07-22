import React, {useState, useEffect} from 'react'
import axios from 'axios';
import Pagination from '../utils/Pagination';
import CategoryService from '../../services/CategoryService';
import { NewCategoryForm } from '../forms/NewCategoryForm';
import PaginationHelper from '../utils/PaginationHelper';


export const Categories = () => {

    const [categories, setCategories] = useState([]);
    const[pageNumber, setPageNumber] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [paginatedCategories, setPaginatedCategories] = useState([])
	const [categoriesPerPage, setCategoriesPerPage] = useState(2);
	const [category, setCategory] = useState({})
	const [type, setType] = useState('')
	const [size, setSize] = useState(2);
	const [display, setDisplay] = useState(false);
	const alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	const [letters, setLetters] = useState('')

    useEffect(() => {
		
		const fetchPaginatedCategories = async () =>{
			CategoryService.getCategoriesPaginateWithParams(currentPage, size)
			.then(response => {
				setPaginatedCategories(response.data.content);
			})
		};

		fetchPaginatedCategories();
        
		CategoryService.getCategoriesPaginate()
        .then(response => {
			setCategories(response.data.content);
		})
		
	}, []);
	
	function handleLetterClick(letter){
		CategoryService.filterCategoriesByFirstLetters(letter).then(response => {
			setPaginatedCategories(response.data)
		})
	}

    const nextPage = async () => {
		let nextPage = currentPage + 1;
		setCurrentPage(nextPage)
		PaginationHelper.displayPaginated(nextPage, size, CategoryService.getCategoriesPaginateWithParams, setPaginatedCategories)
	}	


	const previousPage = async () => {
		let previousPage = currentPage - 1;
		if(currentPage < 0){currentPage=0}
		
		setCurrentPage(previousPage)
		PaginationHelper.displayPaginated(previousPage, size, CategoryService.getCategoriesPaginateWithParams, setPaginatedCategories)
	}		
    
    function saveCategory(id){
		let updatedCategory = {
			type: type
		}
		CategoryService.updateCategory(id, updatedCategory);
		window.location.reload();
	}

	function deleteCategory(id){
		CategoryService.deleteCategory(id);
		window.location.reload();
	}

	function toggleModal(){
		setDisplay(true)
	}

	const handleTypeChange = (e) => {
		setType(e.target.value);
	}

	const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);
		CategoryService.getCategoriesPaginateWithParams(currentPage,size)
		.then(response => {
			setPaginatedCategories(response.data.content);
		})
	}

	function handleSearchChange(e){
		setLetters(e.target.value)
		CategoryService.filterCategoriesByFirstLetters(letters).then(response => {
			setPaginatedCategories(response.data)
		})
	}

    return (
        <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Categories</h2>
				<div class="grey-box-wrap reports">
					<a onClick={ () => toggleModal()} class="link new-member-popup">
						Create new category
					</a>
					<div class="search-page">
						<input type="search" onChange={handleSearchChange} name="search-clients" class="in-search" />
					</div>
				</div>
				<NewCategoryForm display={display}>

                </NewCategoryForm>

				<div class="new-member-wrap">
					<div id="new-member" class="new-member-inner">
						<h2>Create new client</h2>
						<ul class="form">
							<li>
								<label>Client name:</label>
								<input type="text" class="in-text" />
							</li>								
							<li>
								<label>Address:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>City:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Zip/Postal code:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Country:</label>
								<select>
									<option>Select country</option>
								</select>
							</li>
						</ul>
						<div class="buttons">
							<div class="inner">
								<a class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>

				<div class="alpha">
					<ul>	
						{alphabet.map((letter) => (
							<li>
								<a onClick={() => handleLetterClick(letter)}>{letter}</a>
							</li>
						))}				
					</ul>
				</div>

				<div class="accordion-wrap clients">
                			
					{paginatedCategories.map((category) => 
					(
						<tr key={category.id}>
							<div class="item">
								<div class="heading">
									<span>{category.type}</span>
									<i>+</i>
								</div>
								<div class="details">
									<ul class="form">
										<li>
											<label>Type:</label>
											<input type="text" onChange={handleTypeChange} defaultValue={category.type} class="in-text" />
										</li>										
									</ul>
									<div class="buttons">
										<div class="inner">
											<a onClick={ () => saveCategory(category.id)} class="btn green">Save</a>
											<a onClick={ () => deleteCategory(category.id)} class="btn red">Delete</a>
										</div>
									</div>
								</div>
							</div>
						</tr> 
					))
					}

				</div>

				<div class="pagination">
					<ul>
						<li>
							<button onClick={() => previousPage()} style={{marginTop:'15px', marginRight:'5px'}}>Pervious</button>
						</li>
						<li>
							<Pagination
								clientsPerPage={categoriesPerPage}
								totalClients={categories.length}
								paginate={paginate}
							/>
						</li>
						<li>
							<button onClick={() => nextPage()} style={{marginTop:'15px',  marginLeft:'5px'}}>
								Next
							</button>
						</li>
					</ul>
				</div>
			</section>			
		</div>
    )
}

