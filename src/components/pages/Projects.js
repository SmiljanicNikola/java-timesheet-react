import React, {useState, useEffect} from 'react'
import ProjectService from '../../services/ProjectService';
import axios from 'axios'
import Pagination from '../Pagination';
import { NewProjectForm } from '../forms/NewProjectForm';
import ClientService from '../../services/ClientService';
import TeamMemberService from '../../services/TeamMemberService';


export const Projects = () => {
    
    const [projects, setProjects] = useState([]);
	const [pageNumber, setPageNumber] = useState(0);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedProjects, setPaginatedProjects] = useState([])
	const [projectsPerPage, setprojectsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);
	const [project, setProject] = useState({})
	const alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	const [clients, setClients] = useState([])
	const [teamMembers, setTeamMembers] = useState([])
	const [valueTeamMember, setValueTeamMember] = useState('')
	const [valueClient, setValueClient] = useState('');
	const [letters, setLetters] = useState('')
	let updatedProject = {}

	
	const handleChangeClient = client =>{
		setValueClient(client.target.value);
		console.log(valueClient);
	}

	const handleChangeTeamMember = teamMember =>{
		setValueTeamMember(teamMember.target.value);
		console.log(valueTeamMember);
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

    useEffect(() => {

		console.log(pageNumber);
		const fetchPaginatedProjects = async () =>{
		axios.get("http://localhost:8080/api/projects/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
			})
		};

		fetchPaginatedProjects();
        
		axios.get("http://localhost:8080/api/projects/paginate")
        .then(response => {
			setProjects(response.data.content.filter(project => project.deleted == false));
        })
        
	}, []);

	/*function updateProject(id){
		ProjectService.getProjectById(id).then(response => {
			setProject(response.data)
			console.log(project);
		})
	}*/

	function deleteProject(id){
		ProjectService.deleteProject(id).then(response => {
			paginatedProjects.filter(paginatedProjects => project.id !== id)
			console.log('delete')
		});
	}
	
	const nextPage = async () => {

		console.log('NEXT')
		let nextPage = currentPage + 1;
		console.log(nextPage)
		
		axios.get("http://localhost:8080/api/projects/paginate?page="+nextPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
		})
	}	

	const previousPage = async () => {

		console.log('PERVIOUS')
		let previousPage = currentPage - 1;
		console.log(previousPage)
		
		axios.get("http://localhost:8080/api/projects/paginate?page="+previousPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted = false));
		})
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

			console.log(updatedProject);
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
	}

	const handleInactiveInput = () => {
	}

	const handleActiveInput = () => {
	}

	function resetPassword(id){
		console.log('delete')
	}

	function toggleModal(){
		setDisplay(true)
	}

	const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);
		axios.get("http://localhost:8080/api/projects/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
		})
	}

	const indexOfLastClient = currentPage * projectsPerPage;
	const indexOfFirstClient = indexOfLastClient - projectsPerPage;

    return (
        <div>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Projects</h2>
				<div class="grey-box-wrap reports">
					<a onClick={() => toggleModal()} class="link new-member-popup">Create new Project</a>
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
									<a onClick={() => handleLetterClick(letter)}>{letter}</a>
								</li>
							))}
						<li class="last">
							<a href="javascript:;">z</a>
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
								
									<input type="radio" checked={project.finished == true} onChange={handleInactiveInput} id="inactive" />
								</span>
								<span class="radio">
									<label for="active">Active:</label>
									<input type="radio" checked={project.finished == false} onChange={handleActiveInput} id="active" />
								</span>
								
							</li>
							</ul>
							<div class="buttons">
								<div class="inner">
									<a  href="javascript:;" class="btn green" onClick={() => updateProject(project.id)}>Save</a>
									<a class="btn red" onClick={() => deleteProject(project.id)}>Delete</a>
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
								clientsPerPage={projectsPerPage}
								totalClients={projects.length}
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
