import React, {useState, useEffect} from 'react'
import ProjectService from '../../services/ProjectService';
import Pagination from '../utils/Pagination';
import { NewProjectForm } from '../forms/NewProjectForm';
import ClientService from '../../services/ClientService';
import TeamMemberService from '../../services/TeamMemberService';
import PaginationHelper from '../utils/PaginationHelper';
import { Footer } from '../layout/Footer';
import { Header } from '../layout/Header';
import { AuthenticationService } from '../../services/AuthenticationService';
import {ROLE} from '../utils/Constants'


export const Projects = () => {
    
    const [projects, setProjects] = useState([]);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedProjects, setPaginatedProjects] = useState([])
	const [projectsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);
	const [project, setProject] = useState({})
	const alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	const [clients, setClients] = useState([])
	const [teamMembers, setTeamMembers] = useState([])
	const [valueTeamMember, setValueTeamMember] = useState('')
	const [valueClient, setValueClient] = useState('');
	const [size] = useState(2);
	const [letters, setLetters] = useState('')
	let updatedProject = {}
	const [username] = useState(AuthenticationService.getUsername());
	const [role] = useState(AuthenticationService.getRole());
	const [loggedUser, setLoggedUser] = useState({});

	const handleChangeClient = client =>{
		setValueClient(client.target.value);
	}

	const handleChangeTeamMember = teamMember =>{
		setValueTeamMember(teamMember.target.value);
	}

	const fetchClients = () => {
		ClientService.getClients().then(( response ) => {
			setClients(response.data);
		})
	}

	const fetchTeamMembers = () => {
		TeamMemberService.getTeamMembers().then(( response ) => {
			setTeamMembers(response.data);
		})
	}

	const fetchPaginatedProjects = async () =>{
		ProjectService.getProjectsPaginateWithParams(currentPage, size)
		.then(response => {
		setPaginatedProjects(response.data.content.filter(project => project.deleted === false));
		})
	};

	const findLoggedUser = () => {
		TeamMemberService.getTeamMemberByUsername(username).then((response => {
			setLoggedUser(response.data);
			console.log('TeamMemberId:')
			console.log(loggedUser.id)
		}))

	}

    useEffect(() => {

		findLoggedUser()

			fetchPaginatedProjects();
			
			ProjectService.getProjectsPaginate()
			.then(response => {
				setProjects(response.data.content.filter(project => project.deleted === false));
			})  
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	function deleteProject(id){
		ProjectService.deleteProject(id);
		window.location.reload();
	}

	const nextPage = async () => {
		let nextPage = currentPage + 1;
		setCurrentPage(nextPage)
		if(role === ROLE.ADMIN)
		PaginationHelper.displayPaginated(nextPage, size, ProjectService.getProjectsPaginateWithParams, setPaginatedProjects)

		if(role === ROLE.WORKER)
		PaginationHelper.displayPaginatedByTeamMember(loggedUser.id, nextPage, size, ProjectService.getProjectsByTeamMemberIdPaginated, setPaginatedProjects)

	}	


	const previousPage = async () => {
		let previousPage = currentPage - 1;
		if(currentPage < 0){
			setCurrentPage(0)
		}
		setCurrentPage(previousPage)
		if(role === ROLE.ADMIN)
		PaginationHelper.displayPaginated(previousPage, size, ProjectService.getProjectsPaginateWithParams, setPaginatedProjects)

		if(role === ROLE.WORKER)
		PaginationHelper.displayPaginatedByTeamMember(loggedUser.id,previousPage, size, ProjectService.getProjectsByTeamMemberIdPaginated, setPaginatedProjects)

	}	

	function updateProject(id){
		
		ProjectService.getProjectById(id).then(response => {
			setProject(response.data)

			updatedProject = {
				id:project.id,
				projectName: project.projectName,
				description: project.description,
				clientId: valueClient,
				teamMemberId: valueTeamMember
			}

			ProjectService.updateProject(id, updatedProject);
			window.location.reload();
		})
		
	}

	function handleLetterClick(letter){
		console.log(letter);
		ProjectService.filterProjectsByFirstLetters(letter).then(response => {
			setPaginatedProjects(response.data)
		})

	}

	function handleSearchChange(e){
		setLetters(e.target.value)
		ProjectService.filterProjectsByFirstLetters(letters).then(response => {
			setPaginatedProjects(response.data)
		})
		if(e.target.value === ''){
			fetchPaginatedProjects();
		}
	}

	const handleInactiveInput = () => {
	}

	const handleActiveInput = () => {
	}

	function toggleModal(){
		setDisplay(true)
	}

	const paginate = (pageNumber) => {

		setCurrentPage(pageNumber);

		ProjectService.getProjectsPaginateWithParams(currentPage,size)
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted === false));
		})

	}

    return (
        <div>
			<Header></Header><br></br><br></br>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Projects</h2>
				<div class="grey-box-wrap reports">

					{role === ROLE.ADMIN &&
						(
							<div onClick={() => toggleModal()} className="link new-member-popup">Create new Project</div>
						)
					}
					<div class="search-page">
						<input type="search" onChange={handleSearchChange} name="search-clients" class="in-search" />
					</div>
				</div>
				<NewProjectForm display={display}>

				</NewProjectForm>
			
				<div class="alpha">
					<ul>	
							{alphabet.map((letter) => (
                				<li>	
									{/* eslint-disable-next-line jsx-a11y/anchor-is-valid*/}
									<a onClick={() => handleLetterClick(letter)}>{letter}</a>
								</li>
							))}
						<li class="last">
							<div onClick={() => {}}>z</div>
						</li>					
					</ul>
				</div>

				<div class="accordion-wrap clients">	
                {paginatedProjects.map((project) => (
                <tr key={project.id}>

					<div class="item">
						<div class="heading">
							<span>{project.projectName}</span> <span><em>(Nina Media)</em></span>
							<i>+</i>
						</div>
						<div class="details">
							<ul class="form">
								<li>
									<label>Project Name:</label>
									<input type="text" name="projectName" onChange={e => setProject({...project, projectName:e.target.value})} defaultValue={project.projectName}  class="in-text" />
								</li>
								<li>
									<label>Lead:</label>
									<select name="teamMember"
										onChange={handleChangeTeamMember}
										onClick={fetchTeamMembers}

									>
										<option>{project.lead.firstname}</option>
										{
											teamMembers.map((teamMember) => (
												<option
												onClick={handleChangeTeamMember}
												getOptionValue={teamMember => teamMember.id}
												value={teamMember.id}
												key={teamMember.id}
												> {teamMember.firstname} </option>
											))
										}
									</select>
								</li>
							</ul>
							<ul class="form">
								<li>
									<label>Description:</label>
									<input type="text" name="description" defaultValue={project.description} onChange={e => setProject({...project, description:e.target.value})} class="in-text" />
								</li>
								
							</ul>
							<ul class="form last">
								<li>
									<label>Customer:</label>
									<select name="client"
										onChange={handleChangeClient}
										onClick={fetchClients}

									>
										<option>{project.client.clientName}</option>
										{
											clients.map((client) => (
												<option
												onClick={handleChangeClient}
												getOptionValue={client => client.id}
												value={client.id}
												key={client.id}
												> {client.clientName} </option>
											))
										}
									</select>
								</li>
								Status:
								<li class="inline">
								
								<span class="radio">
									<label for="inactive">Inactive:</label>
								
									<input type="radio" checked={project.finished === true} onChange={handleInactiveInput} id="inactive" />
								</span>
								<span class="radio">
									<label for="active">Active:</label>
									<input type="radio" checked={project.finished === false} onChange={handleActiveInput} id="active" />
								</span>
								
							</li>
							</ul>
							{
								role === ROLE.ADMIN &&
								
								<div class="buttons">
									<div class="inner">
										{/* eslint-disable-next-line jsx-a11y/anchor-is-valid*/}
										<a class="btn green" onClick={() => updateProject(project.id)}>Save</a>
										{/* eslint-disable-next-line jsx-a11y/anchor-is-valid*/}
										<a class="btn red" onClick={() => deleteProject(project.id)}>Delete</a>
									</div>
								</div>
								
							}
						</div>
					</div>

            	</tr> 
          		))}
        
				</div>
				
				<div class="pagination">
					<ul>
						<li>
							<button onClick={() => previousPage()} style={{marginTop:'15px', marginRight:'5px'}}>Previous</button>
						</li>
						<li>
							<Pagination
								clientsPerPage={projectsPerPage}
								totalClients={projects.length}
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
		<Footer />
    </div>
    )
}
