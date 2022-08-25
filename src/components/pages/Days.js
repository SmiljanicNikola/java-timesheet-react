import React,{useState, useEffect} from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { AuthenticationService } from '../../services/AuthenticationService';
import CategoryService from '../../services/CategoryService';
import ClientService from '../../services/ClientService';
import ProjectService from '../../services/ProjectService';
import TeamMemberService from '../../services/TeamMemberService';
import TimeSheetActivityService from '../../services/TimeSheetActivityService';
import { WeekComponent } from '../calendar-feature/WeekComponent';
import { Footer } from '../layout/Footer';
import { Header } from '../layout/Header';
import {ROLE} from '../utils/Constants'


export const Days = () => {

	const [timeSheets, setTimeSheets] = useState([])
	const navigate = useNavigate();
	const params = useParams();
	const [clients, setClients] = useState([])
	const [teamMembers, setTeamMembers] = useState([])
	const [role, setRole] = useState(AuthenticationService.getRole());
	const [projects, setProjects] = useState([])
	const [newTimeSheet, setNewTimeSheet] = useState({})
	const [valueProject, setValueProject] = useState('');
	const [valueCategory, setValueCategory] = useState('');
	const [valueTeamMember, setValueTeamMember] = useState('');
	const [valueClient, setValueClient] = useState('');
	const [categories, setCategories] = useState([]);

	useEffect(() => {

		TimeSheetActivityService.searchByDate(params.date).then((response => {
			setTimeSheets(response.data);
		}))

		ClientService.getClients().then((response => {
			setClients(response.data);
		}))

		ProjectService.getProjects().then((response => {
			setProjects(response.data);
		}))
		
	}, []);

	  
	let totalTime = 0;
	for(let i = 0; i < timeSheets.length; i++){
		totalTime = totalTime + timeSheets[i].time
		totalTime = totalTime + timeSheets[i].overtime
	}

	const handleChangeClient = client =>{
		setValueClient(client.target.value);
		console.log(valueClient);
	}

	const handleChangeCategory = e =>{
		setValueCategory(e.target.value);
		console.log(valueCategory);
	}

	const handleChangeProject = project =>{
		setValueProject(project.target.value);
		console.log(valueProject);
	}

	const handleChangeTeamMember = member =>{
		setValueTeamMember(member.target.value);
		console.log(valueTeamMember);
	}


	const fetchProjects = () => {
		ProjectService.getProjects().then(( response ) => {
			setProjects(response.data);
		})
	}

	const fetchCategories = () => {
		CategoryService.getCategories().then(( response ) => {
			setCategories(response.data);
		})
	}

	const fetchTeamMembers = () =>{
		TeamMemberService.getTeamMembers().then(( response ) => {
			setTeamMembers(response.data);
		})
	}

	const fetchClients = () => {
		ClientService.getClients().then(( response ) => {
			setClients(response.data);
		})
	}

	function addActivity(newTimeSheet){
		let newActivity = {
			description: newTimeSheet.description,
			teamMemberId: valueTeamMember,
			projectId: valueProject,
			categoryId: valueCategory,
			time: newTimeSheet.time,
			overtime: newTimeSheet.overtime,
			date: params.date
		}
		console.log(valueCategory)
		console.log(newActivity)
		TimeSheetActivityService.createTimesheetActivity(newActivity);
	}

    return (
		<div>
			<Header></Header><br></br><br></br>
			<div class="container">
				<div class="wrapper">
					<section class="content">
						<h2><i class="ico timesheet"></i>TimeSheet</h2>
						<WeekComponent/>
						<table class="default-table">
							<tr>
								<th>
									Client <em>*</em>
								</th>
								<th>
									Project <em>*</em>
								</th>
								<th>
									Category <em>*</em>
								</th>
								<th>Description</th>
								<th class="small">
									Time <em>*</em>
								</th>
								<th class="small">Overtime</th>
								<th>Actions</th>
							</tr>
							{timeSheets.map((activity) => (	
							<tr>
								<td>
									<select>
									<option>{activity.project.client.clientName}</option>

									</select>
								</td>
								<td>
									<select>
										<option>{activity.project.projectName}</option>
									</select>
								</td>
								<td>
									<select>
										<option>{activity.category.type}</option>
									</select>
								</td>
								<td>
									<input type="text" defaultValue={activity.description} class="in-text medium" />
								</td>
								<td class="small">
									<input type="text" defaultValue={activity.time} class="in-text xsmall" />
								</td>
								<td class="small">
									<input type="text" defaultValue={activity.overtime} class="in-text xsmall" />
								</td>
								
							</tr>
							))}
							{role === ROLE.WORKER ?
								(
								<tr>
									
									<td>
										<select name="teamMember"
											onChange={handleChangeTeamMember}
											onClick={fetchTeamMembers}
										>
											<option>Select TeamMember</option>
											{
												teamMembers.map((member) => (
													<option
														onClick={handleChangeTeamMember}
														getOptionValue={member => member.id}
														value={member.id}
													key={member.id}> {member.username} </option>
												))
											}
										</select>
									</td>
									<td>
										<select name="project"
											onChange={handleChangeProject}
											onClick={fetchProjects}
										>
											<option>Select Project</option>
											{
												projects.map((project) => (
													<option
													onClick={handleChangeProject}
													getOptionValue={project => project.id}
													value={project.id}
													key={project.id}
													> {project.projectName} </option>
												))
											}
										</select>
									</td>
									<td>
										<select name="category"
											onChange={handleChangeCategory}
											onClick={fetchCategories}
										>
											<option>Select Category</option>
											{
												categories.map((category) => (
													<option
													onClick={handleChangeCategory}
													value={category.id}
													getOptionValue={category => category.type}
													key={category.id}> {category.type} </option>
												))
											}
										</select>
									</td>
									<td>
										<input name="description" id="description" onChange={e => setNewTimeSheet({...newTimeSheet, description: e.target.value})} type="text" class="in-text medium" />
									</td>
									<td class="small">
										<input name="time" id="time" type="text" onChange={e => setNewTimeSheet({...newTimeSheet, time: e.target.value})} class="in-text xsmall" />
									</td>
									<td class="small">
										<input name="overtime" id="overtime" type="text" onChange={e => setNewTimeSheet({...newTimeSheet, overtime: e.target.value})} class="in-text xsmall" />
									</td>
									<td>
										<button class="btn-success" onClick={() => addActivity(newTimeSheet)}>Add</button>
									</td>
								</tr>
								)
								: null
								
							}
							
						</table>
						<div class="total">
							<a href="index.html"><i></i>back to monthly view</a>
							<span>Total hours: <em>{totalTime}</em></span>
						</div>
					</section>			
				</div>
			</div>
		<Footer></Footer>
		</div>
    )
}
