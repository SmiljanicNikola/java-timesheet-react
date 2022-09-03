/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState, useEffect } from 'react'
import ClientService from '../../services/ClientService';
import Pagination from '../utils/Pagination';
import '../../assets/css/popup.css'
import { NewClientForm } from '../forms/NewClientForm';
import PaginationHelper from '../utils/PaginationHelper';
import { Footer } from '../layout/Footer';
import { Header } from '../layout/Header';
import { AuthenticationService } from '../../services/AuthenticationService';
import {ROLE} from '../utils/Constants'


export const Clients = () => {

	const [clients, setClients] = useState([]);
	const [client, setClient] = useState({});
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedClients, setPaginatedClients] = useState([])
	const [clientsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);
	const alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	const [close,setClose] = useState('details');
	const [size] = useState(2);
	const [letters, setLetters] = useState('')
	const [role] = useState(AuthenticationService.getRole());	
	
    useEffect(() => {

		if(role === ROLE.ADMIN){

			fetchPaginatedClientsWithParams();
		
			ClientService.getClientsPaginate()
			.then(response => {
				setClients(response.data.content);
			})
		}

		if(role === ROLE.WORKER){
			fetchPaginatedClientsWithoutParams();
		}

        
	}, []);

	const fetchPaginatedClientsWithParams = async () =>{
		ClientService.getClientsPaginateWithParams(currentPage,size)
		.then(response => {
		setPaginatedClients(response.data.content);
		})
	};

	const fetchPaginatedClientsWithoutParams = async () =>{
		ClientService.getClientsPaginateWithParams()
		.then(response => {
		setPaginatedClients(response.data.content);
		})
	};

	const changeStyle = () =>{
		setClose('details2')
	}

	const openCard = () =>{
		setClose('details')
	}

	function toggleModal(){
		setDisplay(true)
	}
	
	const nextPage = async () => {
		let nextPage = currentPage + 1;
		setCurrentPage(nextPage)
		PaginationHelper.displayPaginated(nextPage, size, ClientService.getClientsPaginateWithParams, setPaginatedClients)
	}	


	const previousPage = async () => {
		let previousPage = currentPage - 1;
		if(currentPage < 0){
			setCurrentPage(0);
		}
		setCurrentPage(previousPage)
		PaginationHelper.displayPaginated(previousPage, size, ClientService.getClientsPaginateWithParams, setPaginatedClients)
	}	

	function handleLetterClick(letter){
		ClientService.filterClientsByFirstLetters(letter).then(response => {
			setPaginatedClients(response.data)
		})
	}

	function handleSearchChange(e){
		setLetters(e.target.value)
		ClientService.filterClientsByFirstLetters(letters).then(response => {
			setPaginatedClients(response.data)
		})
	}

	function saveClient(id){
		ClientService.getClientById(id).then(response => {
			setClient(response.data);
		})

		let updatedClient = {
			id: client.id,
			clientName: client.clientName,
			address: client.address,
			city: client.city,
			zipCode: client.zipCode,
			country: client.country
		}
		ClientService.updateClient(id, updatedClient);
		window.location.reload();
	}

	function deleteClient(id){
		ClientService.deleteClient(id);
		window.location.reload();
	}


	const paginate = (pageNumber) => {

		setCurrentPage(pageNumber);
		
		ClientService.getClientsPaginateWithParams(currentPage,size)
		.then(response => {
			setPaginatedClients(response.data.content);
		})
	}
	
    return (
        <div>
			<Header></Header><br></br><br></br>

           <div class="wrapper">
				<section class="content">
					<h2><i class="ico clients"></i>Clients</h2>
					<div class="grey-box-wrap reports">
					{
						role === ROLE.ADMIN &&
							<a onClick={() => toggleModal()} class="link new-member-popup">Create new client</a>
					}
						<div class="search-page">
							<input type="search" onChange={handleSearchChange} name="search-clients" class="in-search" />
						</div>
					</div>

					<NewClientForm display={display}>
						
					</NewClientForm>
					

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
												<input type="text" name="clientName" defaultValue={client.clientName} onChange={e => setClient({...client, clientName:e.target.value})} class="in-text" />
											</li>								
											<li>
												<label>Address:</label>
												<input type="text" name="address" defaultValue={client.address} onChange={e => setClient({...client, address:e.target.value})} class="in-text" />
											</li>	
										</ul>
										<ul class="form">
											<li>
												<label>City:</label>
												<input type="text" name="city" defaultValue={client.city} onChange={e => setClient({...client, city:e.target.value})} class="in-text" />
											</li>
											<li>
												<label>ZipCode:</label>
												<input type="text" name="zipCode" defaultValue={client.zipCode} onChange={e => setClient({...client, zipCode:e.target.value})} class="in-text" />
											</li>								
										</ul>
										<ul class="form last">
										<li>
												<label>Country:</label>
												<input type="text" defaultValue={client.country.name} onChange={e => setClient({...client, country:e.target.value})} class="in-text" />
											</li>
										</ul>
										{
											role === ROLE.ADMIN &&
											
												<div class="buttons">
													<div class="inner">
														<a onClick={ () => saveClient(client.id)} class="btn green">Save</a>
														<a onClick={ () => deleteClient(client.id)} class="btn red">Delete</a>
													</div>
												</div>
										}	

									</div>
							</div>

						</tr> 
						))}
					</div>

					{role === ROLE.ADMIN &&
						
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
								<li>
									<button onClick={() => nextPage()} style={{marginTop:'15px',  marginLeft:'5px'}}>
										Next
									</button>
								</li>
							</ul>
						</div>
						
					}
				</section>			
			</div>
			<Footer></Footer>
        </div>
    )
}
