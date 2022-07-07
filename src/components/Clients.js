import React, { useState, useEffect } from 'react'
import ClientService from '../services/ClientService';
import TeamMemberService from '../services/TeamMemberService';
import axios from 'axios'
import Pagination from './Pagination';

export const Clients = () => {

	const [clients, setClients] = useState([]);
	const [loading, setLoading] = useState(false);
	const [pageNumber, setPageNumber] = useState(0);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedClients, setPaginatedClients] = useState([])
	const [clientsPerPage, setClientsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);

    
    useEffect(() => {

		console.log(pageNumber);
		const fetchPaginatedClients = async () =>{
		axios.get("http://localhost:8080/api/clients/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedClients(response.data.content);
			setLoading(false);
			})
		};

		fetchPaginatedClients();
        
		axios.get("http://localhost:8080/api/clients/paginate")
        .then(response => {
			setClients(response.data.content);
			setLoading(false);
        })
        
	}, []);
	
	const nextPage = async () => {

		console.log('NEXT')
		let nextPage = currentPage + 1;
		console.log(nextPage)
		
		axios.get("http://localhost:8080/api/clients/paginate?page="+nextPage+"&size=2")
		.then(response => {
			setPaginatedClients(response.data.content);
			})
	}	

	const previousPage = async () => {

		console.log('PERVIOUS')
		let previousPage = currentPage - 1;
		console.log(previousPage)
		
		axios.get("http://localhost:8080/api/clients/paginate?page="+previousPage+"&size=2")
		.then(response => {
			setPaginatedClients(response.data.content);
			})
	}	


	function saveTeamMember(id){
		console.log('save')
	}

	function deleteTeamMember(id){
		console.log('delete')

	}

	function resetPassword(id){
		console.log('delete')

	}

	function toggleModal(){
		setDisplay(true)
	}

	const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);
		axios.get("http://localhost:8080/api/clients/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedClients(response.data.content);
			})
	}
	
	console.log(currentPage);

	const indexOfLastClient = currentPage * clientsPerPage;
	const indexOfFirstClient = indexOfLastClient - clientsPerPage;
	const currentClients = clients.slice(indexOfFirstClient, indexOfLastClient); 

    return (
        <div>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Clients</h2>
				<div class="grey-box-wrap reports">
					<a href="#new-member" class="link new-member-popup">Create new client</a>
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
                
					
                {paginatedClients.map((client) => (
                <tr key={client.id}>


					<div class="item">
						<div class="heading">
							<span>{client.clientName}</span>
							<i>+</i>
						</div>
							<div class="details">
								<ul class="form">
									<li>
										<label>Name:</label>
										<input type="text" value={client.clientName} class="in-text" />
									</li>								
									<li>
										<label>Hours per week:</label>
										<input type="text" value={client.address} class="in-text" />
									</li>
								</ul>
								<ul class="form">
									<li>
										<label>Username:</label>
										<input type="text" value={client.city} class="in-text" />
									</li>
									<li>
										<label>Email:</label>
										<input type="text" value={client.zipCode} class="in-text" />
									</li>								
								</ul>
								<ul class="form last">
									<li>
										<label>Status:</label>
										<span class="radio">
											<label for="inactive">Inactive:</label>
											<input type="radio" value="1" name="status" id="inactive" />
										</span>
										<span class="radio">
											<label for="active">Active:</label>
											<input type="radio" value="2" name="status" id="active" />
										</span>
									</li>
									<li>
										<label>Role:</label>
										<span class="radio">
											<label for="admin">Admin:</label>
											<input type="radio" value="1" name="status" id="admin" />
										</span>
										<span class="radio">
											<label for="worker">Worker:</label>
											<input type="radio" value="2" name="status" id="worker" />
										</span>
									</li>
								</ul>
								<div class="buttons">
									<div class="inner">
										<a href="javascript:;" onClick={ () => saveTeamMember(client.id)} class="btn green">Save</a>
										<a href="#" onClick={ () => deleteTeamMember(client.id)} class="btn green" class="btn red">Delete</a>
										<a href="javascript:;" onClick={ () => resetPassword(client.id)} class="btn green" class="btn orange">Reset Password</a>
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
								clientsPerPage={clientsPerPage}
								totalClients={clients.length}
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
