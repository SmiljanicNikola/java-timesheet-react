import axios from 'axios';
import React, {useEffect, useState} from 'react'
import AsyncSelect from 'react-select/async'
import CategoryService from '../services/CategoryService';
import ClientService from '../services/ClientService';
import ProjectService from '../services/ProjectService';
import TeamMemberService from '../services/TeamMemberService';
import TimeSheetActivityService from '../services/TimeSheetActivityService';

export const Reports = () => {

	const [clients, setClients] = useState([]);
	const [projects, setProjects] = useState([]);
	const [teamMembers, setTeamMembers] = useState([]);
	const [categories, setCategories] = useState([]);
	const [inputValue, setValue]= useState('');

	const [valueProject, setValueProject] = useState('');
	const [valueTeamMember, setValueTeamMember] = useState('');
	const [valueCategory, setValueCategory] = useState('');
	const [valueClient, setValueClient] = useState('');


	const [selectedValue, setSelectedValue] = useState(null);
	const [selectedValueProject, setSelectedValueProject] = useState(null);
	const [selectedValueCategory, setSelectedValueCategory] = useState(null);
	const [selectedValueTeamMember, setSelectedValueTeamMember] = useState(null);
	const [timeSheets, setTimeSheets] = useState([]);

	useEffect(() => {
		
		
	})

	const fetchTeamMembers = () =>{
		TeamMemberService.getTeamMembers().then(( response ) => {
			setTeamMembers(response.data);
		})
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

	const fetchClients = () => {
		ClientService.getClients().then(( response ) => {
			setClients(response.data);
		})
	}

	function reset(){
		console.log('click')
		setValueProject('');
		setValueTeamMember('');
		setValueCategory('');
		setProjects([])
		setTeamMembers([])
		setProjects([])
		setCategories([])

		axios.get("http://localhost:8080/api/timeSheetActivities/search?").then(response => {
			setTimeSheets(response.data)
		})

	}

	const handleChangeProject = project =>{
		/*setSelectedValueProject(project.target.value);
		console.log(selectedValueProject);*/
		setValueProject(project.target.value);
		console.log(valueProject);
	}

	const handleChangeTeamMember = member =>{
		setValueTeamMember(member.target.value);
		console.log(valueTeamMember);
	}

	const handleChangeClient = client =>{
		setValueClient(client.target.value);
		console.log(valueClient);
	}

	const handleChangeCategory = e =>{
		setValueCategory(e.target.value);
		console.log(valueCategory);
	}
	
	function search(selectedValueProject){
		TimeSheetActivityService.getTimeSheetsByProjectId(selectedValueProject.id).then(response => {
			setTimeSheets(response.data);
			console.log(timeSheets);
		})
	}

	function searchByThreeParameters(valueProject, valueTeamMember, valueCategory){
		console.log(valueProject, valueTeamMember, valueCategory);
		let TARGETED_API = "http://localhost:8080/api/timeSheetActivities/search?";
		if(valueProject != null && valueProject != '' && valueProject != 'Select Project'){
			TARGETED_API = TARGETED_API + "projectId=" + valueProject;
			console.log('PROJECT')
			console.log(TARGETED_API);
		}else{
			TARGETED_API = TARGETED_API
		}

		if(valueTeamMember != null && valueTeamMember != '' && valueTeamMember != 'Select TeamMember'){
			TARGETED_API = TARGETED_API + "&teamMemberId=" + valueTeamMember;
			console.log('TEAM')

			console.log(TARGETED_API);
		}else{
			TARGETED_API = TARGETED_API;
		}
		
		if(valueCategory != null && valueCategory != '' && valueCategory != 'Select Category'){
			TARGETED_API = TARGETED_API + "&categoryId=" + valueCategory;
			console.log(TARGETED_API);
		}else{
			TARGETED_API = TARGETED_API;
		}
		console.log(TARGETED_API)
		axios.get(TARGETED_API).then(response => {
			setTimeSheets(response.data)
		})
		/*axios.get("http://localhost:8080/api/timeSheetActivities/search?projectId="+valueProject+"&teamMemberId="+valueTeamMember+"&categoryId="+ valueCategory).then(response => {
			setTimeSheets(response.data);
			console.log(timeSheets);
		})*/
	}

	

    return (
        <div class="container">
		<div class="wrapper">
			<section class="content">
				<h2><i class="ico report"></i>Reports</h2>
				<div class="grey-box-wrap reports">
					<ul class="form">
						<li>
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
						</li>	
						<li>
							<select name="teamMember" style={{marginTop:'22px'}}
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
						</li>
							
					</ul>
					<ul class="form">
						<li>
							<select name="client"
								onChange={handleChangeClient}
								onClick={fetchClients}
							>
								<option>Select Client</option>

								{
									clients.map((client) => (
										<option key={client.id}> {client.clientName} </option>
									))
								}
							</select>
						</li>						
						<li>
							<label>Start date:</label>
							<input type="date" class="in-text datepicker" />
						</li>
					</ul>
					<ul class="form last">
						<li>
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
						</li>
						<li>
							<label>End date:</label>
							<input type="date" class="in-text datepicker" />
						</li>
						<li>
							<a onClick={() => reset()} class="btn orange right">Reset</a>
							<a onClick={() => searchByThreeParameters(valueProject,valueTeamMember, valueCategory)} class="btn green right">Search</a>
						</li>
					</ul>
				</div>
				<table class="default-table" border="1" style={{}}>
					<tr>
						<th>
							Date
						</th>
						<th>
							Team member
						</th>
						<th>
							Projects
						</th>
						<th>Categories</th>
						<th>Description</th>
						<th class="small">Time</th>
						<th>Actions</th>
					</tr>
					<tbody>{timeSheets.map((timeSheet) => (
					<tr key={timeSheet.id}>
						<td>
							{timeSheet.date}
						</td>
						<td>
							{timeSheet.teamMember.firstname + timeSheet.teamMember.lastname}
						</td>
						<td>
							{timeSheet.project.projectName}
						</td>
						<td>
							{timeSheet.category.type}
						</td>
						<td>
							{timeSheet.description}
						</td>
						<td class="small">
							{timeSheet.time}
						</td>
						<td>
							<button>Download PDF</button>
						</td>
					</tr>
				
					))}
					</tbody>
				</table>
				<div class="total">
					<span>Report total: <em>7.5</em></span>
				</div>
				<div class="grey-box-wrap reports">
					<div class="btns-inner">
						<a href="javascript:;" class="btn white">
							<span>Print report</span>
						</a>
						<a href="javascript:;" class="btn white">
							<span>Create PDF</span>
						</a>
						<a href="javascript:;" class="btn white">
							<span>Export to excel</span>
						</a>
					</div>
				</div>
			</section>			
		</div>
        </div>
    )
}
