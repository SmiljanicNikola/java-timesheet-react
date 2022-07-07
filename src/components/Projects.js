import React, {useState, useEffect} from 'react'
import ProjectService from '../services/ProjectService';
import axios from 'axios'
import Pagination from './Pagination';

export const Projects = () => {
    
    const [projects, setProjects] = useState([]);
	const [loading, setLoading] = useState(false);
	const [pageNumber, setPageNumber] = useState(0);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedProjects, setPaginatedProjects] = useState([])
	const [projectsPerPage, setprojectsPerPage] = useState(2);
	const [display, setDisplay] = useState(false);
	const [project, setProject] = useState({})

    
    useEffect(() => {

		console.log(pageNumber);
		const fetchPaginatedProjects = async () =>{
		axios.get("http://localhost:8080/api/projects/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
			setLoading(false);
			})
		};

		

		fetchPaginatedProjects();
        
		axios.get("http://localhost:8080/api/projects/paginate")
        .then(response => {
			setProjects(response.data.content.filter(project => project.deleted == false));
			setLoading(false);
        })
        
	}, []);

	function updateProject(id){
		ProjectService.getProjectById(id).then(response => {
			setProject(response.data)
			console.log(project);
		})
	}

	function deleteProject(id){
		ProjectService.deleteProject(id).then(response => {
			paginatedProjects.filter(paginatedProjects => project.id !== id)
			console.log('delete')
		});
	}

	function HandleProjectName(e){
		setProject({
			...project,
			[e.target.projectName]: e.target.value
		})
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
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
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
		axios.get("http://localhost:8080/api/projects/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedProjects(response.data.content.filter(project => project.deleted == false));
		})
	}
	
	console.log(currentPage);

	const indexOfLastClient = currentPage * projectsPerPage;
	const indexOfFirstClient = indexOfLastClient - projectsPerPage;
	const currentProjects = projects.slice(indexOfFirstClient, indexOfLastClient); 

    return (
        <div>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Projects</h2>
				<div class="grey-box-wrap reports">
					<a href="#new-member" class="link new-member-popup">Create new Project</a>
					<div class="search-page">
						<input type="search" name="search-clients" class="in-search" />
					</div>
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
									<input type="text" onChange={HandleProjectName} value={project.projectName}  class="in-text" />
								</li>
								<li>
									<label>Lead:</label>
									<select>
										<option>Select lead</option>
										<option>Sasa Popovic</option>
										<option>Sladjana Miljanovic</option>
									</select>
								</li>
							</ul>
							<ul class="form">
								<li>
									<label>Description:</label>
									<input type="text" value={project.description} class="in-text" />
								</li>
								
							</ul>
							<ul class="form last">
								<li>
									<label>Customer:</label>
									<select>
										<option>Select customer</option>
										<option>Adam Software NV</option>
										<option>Clockwork</option>
										<option>Emperor Design</option>
									</select>
								</li>
								<li class="inline">
								<label>Status:</label>
								<span class="radio">
									<label for="inactive">Active:</label>
									<input type="radio" value="1" name="status" id="inactive" />
								</span>
								<span class="radio">
									<label for="active">Inactive:</label>
									<input type="radio" value="2" name="status" id="active" />
								</span>
								<span class="radio">
									<label for="active">Archive:</label>
									<input type="radio" value="3" name="status" id="active" />
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
