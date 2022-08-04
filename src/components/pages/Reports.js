import axios from 'axios';
import React, {useEffect, useState} from 'react'
import CategoryService from '../../services/CategoryService';
import ClientService from '../../services/ClientService';
import ProjectService from '../../services/ProjectService';
import TeamMemberService from '../../services/TeamMemberService';
import TimeSheetActivityService from '../../services/TimeSheetActivityService';
import { Footer } from '../layout/Footer';
import { Header } from '../layout/Header';

export const Reports = () => {

	const [clients, setClients] = useState([]);
	const [projects, setProjects] = useState([]);
	const [teamMembers, setTeamMembers] = useState([]);
	const [categories, setCategories] = useState([]);
	const [valueProject, setValueProject] = useState('');
	const [valueStartDate, setValueStartDate] = useState('');
	const [valueEndDate, setValueEndDate] = useState('');
	const [valueTeamMember, setValueTeamMember] = useState('');
	const [valueCategory, setValueCategory] = useState('');
	const [valueClient, setValueClient] = useState('');
	const [timeSheets, setTimeSheets] = useState([]);


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

		setValueProject('');
		setValueTeamMember('');
		setValueCategory('');
		setValueStartDate('');
		setValueEndDate('');

		setProjects([])
		setTeamMembers([])
		setProjects([])
		setCategories([])

		TimeSheetActivityService.emptySearchTimeSheets().then(response => {
			setTimeSheets(response.data)
		});
	}

	const handleChangeProject = project =>{
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
	
	function searchByProjectMemberCategoryAndDates(valueProject, valueTeamMember, valueCategory, valueStartDate, valueEndDate){		
		TimeSheetActivityService.searchTimeSheetsActivities(valueProject, valueTeamMember, valueCategory, valueStartDate, valueEndDate)
		.then(response => {
			setTimeSheets(response.data)
		})	
	}

	const exportPDFListOfTimeSheets = async (timeSheets) => {
		TimeSheetActivityService.exportPDFReport(timeSheets).then((response) => {
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement('a');
			link.href = url;
			link.setAttribute('download', 'file.pdf'); //or any other extension
			document.body.appendChild(link);
			link.click();
		}).catch(err => alert(err));
	}

	let totalTime = 0;
	for(let i = 0; i < timeSheets.length; i++){
		{
			totalTime = totalTime + timeSheets[i].time
			totalTime = totalTime + timeSheets[i].overtime
		}
	}

    return (
		<div>
			<Header></Header><br></br><br></br>
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
						<table class="default-table" border="1">
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
							</tr>
							))}
							</tbody>
						</table>
						<div class="total">
							<span>Report total: <em>{totalTime}</em></span>
						</div>
						<div class="grey-box-wrap reports">
							<div class="btns-inner">
								<a class="btn white">
									<span>Print report</span>
								</a>
								<a class="btn white" onClick={() => exportPDFListOfTimeSheets(timeSheets)}>
									<span>Create PDF</span>
								</a>
								<a class="btn white">
									<span>Export to excel</span>
								</a>
							</div>
						</div>
					</section>			
				</div>
			</div>
			<Footer></Footer>
		</div>
    )
}
