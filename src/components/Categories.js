import React, {useState, useEffect} from 'react'
import axios from 'axios';
import Pagination from './Pagination';
import CategoryService from '../services/CategoryService';


export const Categories = () => {
    const [categories, setCategories] = useState([]);
    const[pageNumber, setPageNumber] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [paginatedCategories, setPaginatedCategories] = useState([])
	const [categoriesPerPage, setCategoriesPerPage] = useState(2);
	const [category, setCategory] = useState({})

    useEffect(() => {

		console.log(pageNumber);
		const fetchPaginatedCategories = async () =>{
		axios.get("http://localhost:8080/api/categories/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedCategories(response.data.content);
			})
		};

		fetchPaginatedCategories();
        
		axios.get("http://localhost:8080/api/categories/paginate")
        .then(response => {
			setCategories(response.data.content);
        })
        
    }, []);

    const nextPage = async () => {

		console.log('NEXT')
		let nextPage = currentPage + 1;
		console.log(nextPage)
		
		axios.get("http://localhost:8080/api/categories/paginate?page="+nextPage+"&size=2")
		.then(response => {
			setPaginatedCategories(response.data.content);
			})
	}	

	const previousPage = async () => {

		console.log('PERVIOUS')
		let previousPage = currentPage - 1;
		console.log(previousPage)
		
		axios.get("http://localhost:8080/api/categories/paginate?page="+previousPage+"&size=2")
		.then(response => {
			setPaginatedCategories(response.data.content);
			})
	}	
    
    function saveCategory(id){
		console.log('save')
	}

	function deleteCategory(id){
		CategoryService.deleteCategory(id).then(response => {
			paginatedCategories.filter(paginatedCategories => category.id !== id)
			console.log('delete')
		});

	}

	function toggleModal(){
        
	}

	const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);
		axios.get("http://localhost:8080/api/categories/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedCategories(response.data.content);
			})
	}
	
	console.log(currentPage);

	const indexOfLastClient = currentPage * categoriesPerPage;
	const indexOfFirstClient = indexOfLastClient - categoriesPerPage;
	const currentClients = categories.slice(indexOfFirstClient, indexOfLastClient); 

    return (
        <div>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Categories</h2>
				<div class="grey-box-wrap reports">
					<a href="#new-member" class="link new-member-popup">Create new category</a>
					<div class="search-page">
						<input type="search" name="search-clients" class="in-search" />
					</div>
				</div>
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
								<a href="javascript:;" class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>
				<div class="alpha">
					<ul>
						<li>
							<a href="javascript:;">a</a>
						</li>
						<li>
							<a href="javascript:;">b</a>
						</li>
						<li>
							<a href="javascript:;">c</a>
						</li>
						<li>
							<a href="javascript:;">d</a>
						</li>
						<li>
							<a href="javascript:;">e</a>
						</li>
						<li class="active">
							<a href="javascript:;">f</a>
						</li>
						<li>
							<a href="javascript:;">g</a>
						</li>
						<li>
							<a href="javascript:;">h</a>
						</li>
						<li>
							<a href="javascript:;">i</a>
						</li>
						<li>
							<a href="javascript:;">j</a>
						</li>
						<li>
							<a href="javascript:;">k</a>
						</li>
						<li>
							<a href="javascript:;">l</a>
						</li>
						<li class="disabled">
							<a href="javascript:;">m</a>
						</li>
						<li>
							<a href="javascript:;">n</a>
						</li>
						<li>
							<a href="javascript:;">o</a>
						</li>
						<li>
							<a href="javascript:;">p</a>
						</li>
						<li>
							<a href="javascript:;">q</a>
						</li>
						<li>
							<a href="javascript:;">r</a>
						</li>
						<li>
							<a href="javascript:;">s</a>
						</li>
						<li>
							<a href="javascript:;">t</a>
						</li>
						<li>
							<a href="javascript:;">u</a>
						</li>
						<li>
							<a href="javascript:;">v</a>
						</li>
						<li>
							<a href="javascript:;">w</a>
						</li>
						<li>
							<a href="javascript:;">x</a>
						</li>
						<li>
							<a href="javascript:;">y</a>
						</li>
						<li class="last">
							<a href="javascript:;">z</a>
						</li>					
					</ul>
				</div>
				<div class="accordion-wrap clients">
                
					
                {paginatedCategories.map((category) => (
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
										<input type="text" value={category.type} class="in-text" />
									</li>								
								
									
								</ul>
								<div class="buttons">
									<div class="inner">
										<a href="javascript:;" onClick={ () => saveCategory(category.id)} class="btn green">Save</a>
										<a href="#" onClick={ () => deleteCategory(category.id)} class="btn green" class="btn red">Delete</a>
									</div>
								</div>
							</div>
					</div>
            	</tr> 
          		))}
        
      
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
						<li><button onClick={() => nextPage()} style={{marginTop:'15px',  marginLeft:'5px'}}>Next</button></li>
					</ul>
				</div>
			</section>			
		</div>
        </div>
    )
}

