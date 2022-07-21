import React, {useEffect, useState} from 'react'
import '../../assets/css/popup.css'
import {useNavigate} from 'react-router-dom'
import ClientService from '../../services/ClientService';
import TeamMemberService from '../../services/TeamMemberService';
import ProjectService from '../../services/ProjectService';

export const NewProjectForm = (props) => {
	
    const [takenProps, setTakenProps] = useState(props.display);
	const [display, setDisplay] = useState(false);
	const [clients, setClients] = useState([])
	const [teamMembers, setTeamMembers] = useState([])
	const [project, setProject] = useState({})
	const navigate = useNavigate();
	const [valueClient, setValueClient] = useState('');
	const [valueTeamMember, setValueTeamMember] = useState('');

    useState(() =>{
        setTakenProps(props.display)
    })

	const fetchClients = () => {
		ClientService.getClients().then(( response ) => {
			setClients(response.data);
		})
	}

	const fetchTeamMembers = () =>{
		TeamMemberService.getTeamMembers().then(( response ) => {
			setTeamMembers(response.data);
		})
	}

	function closePopup(){
		props.display = false;
    }
    

	const handleChangeClient = client =>{
		setValueClient(client.target.value);
		console.log(valueClient);
	}

	
	const handleChangeTeamMember = member =>{
		setValueTeamMember(member.target.value);
		console.log(valueTeamMember);
	}

	function saveProject(project){
		
		let newProject = {
			projectName: project.projectName,
			description: project.description,
			clientId: valueClient,
			teamMemberId: valueTeamMember
		}

		ProjectService.createProject(newProject);

	}


    return (
        (props.display == true)?(
            
        <div>
            <div class="popup">
					<div id="new-project" class="popup-inner">
						<h2>Create new project</h2>
						<ul class="form">
							<li>
								<label>Project name:</label>
								<input name="projectName" id="projectName" value={project.projectName} onChange={e => setProject({...project, projectName:e.target.value})} type="text" class="in-text" />
							</li>								
							<li>
								<label>Description:</label>
								<input name="description" id="description" value={project.description} onChange={e => setProject({...project, description:e.target.value})} type="text" class="in-text" />
							</li>
							<li>
								<label>Customer:</label>
								<select name="client"
									onChange={handleChangeClient}
									onClick={fetchClients}
									>

									<option>Select Client</option>
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
							<li>
								<label>Lead:</label>
								<select
									name="teamMember"
									onChange={handleChangeTeamMember}
									onClick={fetchTeamMembers}
								>
									
									<option>Select Lead</option>
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
                        <button className="close-btn" onClick={ () => closePopup()}>
							Close
						</button>
						<div class="buttons">
							<div class="inner">
								<a href="javascript:;" onClick={() => saveProject(project)} class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>
        </div>):<div></div>
    )
}
