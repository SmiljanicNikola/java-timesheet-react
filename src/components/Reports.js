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
	const [valueStartDate, setValueStartDate] = useState('');
	const [valueEndDate, setValueEndDate] = useState('');
	const [valueTeamMember, setValueTeamMember] = useState('');
	const [valueCategory, setValueCategory] = useState('');
	const [valueClient, setValueClient] = useState('');


	const [selectedValue, setSelectedValue] = useState(null);
	const [selectedValueProject, setSelectedValueProject] = useState(null);
	const [selectedValueCategory, setSelectedValueCategory] = useState(null);
	const [selectedValueTeamMember, setSelectedValueTeamMember] = useState(null);
	const [timeSheets, setTimeSheets] = useState([]);
	const [API_SEARCH, SET_API_SEARCH] = useState('')

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

	const handleStartDateChange = e =>{
		setValueStartDate(e.target.value);
		console.log(valueStartDate);
	}

	const handleEndDateChange = e =>{
		setValueEndDate(e.target.value);
		console.log(valueEndDate);
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

	function searchByProjectMemberCategoryAndDates(valueProject, valueTeamMember, valueCategory, valueStartDate, valueEndDate){

		let TARGETED_API = "http://localhost:8080/api/timeSheetActivities/search?";

		if(valueProject != null && valueProject != '' && valueProject != 'Select Project'){
			TARGETED_API = TARGETED_API + "projectId=" + valueProject;
		}

		if(valueTeamMember != null && valueTeamMember != '' && valueTeamMember != 'Select TeamMember'){
			TARGETED_API = TARGETED_API + "&teamMemberId=" + valueTeamMember;

		}
		
		if(valueCategory != null && valueCategory != '' && valueCategory != 'Select Category'){
			TARGETED_API = TARGETED_API + "&categoryId=" + valueCategory;
		}

		if(valueStartDate != null && valueStartDate != '' && valueStartDate != 'Select Category'){
			TARGETED_API = TARGETED_API + "&startDate=" + valueStartDate;
		}

		if(valueEndDate != null && valueEndDate != '' && valueEndDate != 'Select Category'){
			TARGETED_API = TARGETED_API + "&endDate=" + valueEndDate;
		}
		
		axios.get(TARGETED_API).then(response => {
			setTimeSheets(response.data)
		})
		
	}

	/*function buildInitialTargetedSearchAPI(argument, value){
		SET_API_SEARCH("http://localhost:8080/api/timeSheetActivities/search?")
		let TARGET_API_SEARCH = "http://localhost:8080/api/timeSheetActivities/search?" + `${argument}=` + `${value}`;
		SET_API_SEARCH(TARGET_API_SEARCH);
		console.log(API_SEARCH)
	}

	function buildTargetedSearchAPI(path, argument, value){
		let additionalAttribute = `${argument}=` + `${value}`;
		SET_API_SEARCH(path + additionalAttribute);
	}*/

	

	const exportPDF = async (timeSheet) => {

		let report = {
			id:1,
			description: timeSheet.description,
			teamMember: timeSheet.teamMember,
			client: timeSheet.project.client,
			project: timeSheet.project,
			category: timeSheet.category,
			time: timeSheet.time,
			overtime: timeSheet.overtime,
			date: timeSheet.date

		}

		const requestOptions = {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({report})
		};

		console.log('REP')
		console.log(report)
		axios.post(`http://localhost:8080/api/timeSheetActivities/reports/export`,requestOptions,{
		//TimeSheetActivityService.exportPDFReport(report), {
			params: {
				cacheBustTimestamp: Date.now(),
				
			},
			  responseType: 'blob',
			  timeout: 120,
		}).then((response) => {
			console.log('>>>', { response });
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement('a');
			link.href = url;
			link.setAttribute('download', 'file.pdf'); //or any other extension
			document.body.appendChild(link);
			link.click();
		}).catch(err => alert(err));
		console.log('click')
	}

	const exportPDFListOfTimeSheets = async (timeSheets) => {

	

		const requestOptions = {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({timeSheets})
		};


		axios.post(`http://localhost:8080/api/timeSheetActivities/export`,timeSheets,{
		//TimeSheetActivityService.exportPDFReport(report), {
			params: {
				cacheBustTimestamp: Date.now(),
				
			},
			  responseType: 'blob',
			  timeout: 120,
		}).then((response) => {
			console.log('>>>', { response });
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement('a');
			link.href = url;
			link.setAttribute('download', 'file.pdf'); //or any other extension
			document.body.appendChild(link);
			link.click();
		}).catch(err => alert(err));
		console.log('click')
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
							<input onClick={handleStartDateChange} onChange={handleStartDateChange}  type="date" class="in-text datepicker" />
						</li>
					</ul>
					<ul class="form last">
						<li>
							<select name="category"
								onChange={handleChangeCategory}
								onClick={fetchCategories}
							>
								<option value={undefined}>Select Category</option>

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
							<input type="date" onChange={handleEndDateChange} class="in-text datepicker" />
						</li>
						<li>
							<a onClick={() => reset()} class="btn orange right">Reset</a>
							<a onClick={() => searchByProjectMemberCategoryAndDates(valueProject,valueTeamMember, valueCategory, valueStartDate, valueEndDate)} class="btn green right">Search</a>
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
							<button onClick={() => exportPDF(timeSheet)}>Download PDF</button>
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
						<a href="javascript:;" class="btn white" onClick={() => exportPDFListOfTimeSheets(timeSheets)}>
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
