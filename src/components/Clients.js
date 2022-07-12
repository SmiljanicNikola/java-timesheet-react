import React, { useState, useEffect } from 'react'
import ClientService from '../services/ClientService';
import TeamMemberService from '../services/TeamMemberService';
import axios from 'axios'
import Pagination from './Pagination';
import { Footer } from './Footer';
import './style.css'
import { NewClientForm } from './NewClientForm';
import { TeamMembers } from './TeamMembers';


export const Clients = () => {

	const [clients, setClients] = useState([]);
	const [client, setClient] = useState({});
	const [loading, setLoading] = useState(false);
	const [pageNumber, setPageNumber] = useState(0);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedClients, setPaginatedClients] = useState([])
	const [clientsPerPage, setClientsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);
	const alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	const [close,setClose] = useState('details');
	const [clientName, setClientName] = useState('');
	const [address, setAddress] = useState('');

	
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

	const changeStyle = () =>{
		console.log('e')
		setClose('details2')
	}

	const openCard = () =>{
		console.log('e')
		setClose('details')
	}

	function toggleModal(){
		setDisplay(true)
	}
	
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


	function saveClient(id){
		ClientService.getClientById(id).then(response => {
			setClient(response.data);
			console.log(client);
		})

		let updatedClient = {
			id: client.id,
			clientName: clientName,
			address: address,
			city: client.city,
			country: client.country
		}

		ClientService.updateClient(id, updatedClient);
		console.log(updatedClient);
	}

	function deleteClient(id){
		ClientService.deleteClient(id).then(response => {
			paginatedClients.pop(client => client.id == id);
			paginatedClients.filter(client => client.id !== id)
		});
	}

	const handleLetterClick = (letter) =>{
		
	}

	const handleZipCodeChange = (e) => {
		
	}

	const handleAddressChange = (e) => {
		setAddress(e.target.value);

	}

	const handleCityChange = (e) => {
		
	}

	const handleClientNameChange = (e) => {
		setClientName(e.target.value);
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
					<a class="link new-member-popup">Create new client</a>
					<div class="search-page">
						<input type="search" name="search-clients" class="in-search" />
					</div>
				</div>
				<NewClientForm display={display}>
					
				</NewClientForm>
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
							{alphabet.map((letter) => (
                				<li>
									<a onClick={handleLetterClick()}>{letter}</a>
								</li>
							))}

						<li class="last">
							<a href="javascript:;">z</a>
						</li>					
					</ul>
				</div>
				<div class="accordion-wrap clients">
					
                {paginatedClients.map((client) => (
                <tr key={client.id}>


					<div class="item" >
						<div class="heading" onClick={changeStyle} onDoubleClick={openCard}>
							<span>{client.clientName}</span>
							<i>+</i>
						</div>
							<div class={close} >
								<ul class="form">
									<li>
										<label>Client Name:</label>
										<input type="text" name="clientName" defaultValue={client.clientName} onChange={handleClientNameChange} class="in-text" />
									</li>								
									<li>
										<label>Address:</label>
										<input type="text" name="address" defaultValue={client.address} onChange={handleAddressChange} class="in-text" />
									</li>
									
								</ul>
								<ul class="form">
									<li>
										<label>City:</label>
										<input type="text" defaultValue={client.city} onChange={handleCityChange} class="in-text" />
									</li>
									<li>
										<label>ZipCode:</label>
										<input type="text" defaultValue={client.zipCode} onChange={handleZipCodeChange} class="in-text" />
									</li>								
								</ul>
								<ul class="form last">
								<li>
										<label>Country:</label>
										<input type="text" defaultValue={client.country.name} onChange={handleClientNameChange} class="in-text" />
									</li>
									
								</ul>
								<div class="buttons">
									<div class="inner">
										<a href="javascript:;" onClick={ () => saveClient(client.id)} class="btn green">Save</a>
										<a href="#" onClick={ () => deleteClient(client.id)} class="btn green" class="btn red">Delete</a>
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
