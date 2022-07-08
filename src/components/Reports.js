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


	const [selectedValue, setSelectedValue] = useState(null);
	const [selectedValueProject, setSelectedValueProject] = useState(null);
	const [selectedValueCategory, setSelectedValueCategory] = useState(null);
	const [selectedValueTeamMember, setSelectedValueTeamMember] = useState(null);


	const [timeSheets, setTimeSheets] = useState([]);

	useEffect(() => {
		TeamMemberService.getTeamMembers().then(( response ) => {
			setTeamMembers(response.data);
		})

		ProjectService.getProjects().then(( response ) => {
			setProjects(response.data);
		})

		CategoryService.getCategories().then(( response ) => {
			setCategories(response.data);
		})
	})

	function reset(){

	}

	const handleChangeProject = project =>{
		setSelectedValueProject(project.target);
		console.log(selectedValueProject);
	}

	const handleChangeTeamMember = member =>{
		setValueTeamMember(member.target.value);
		console.log(valueTeamMember);
	}
	

	function search(selectedValueProject){
		
		TimeSheetActivityService.getTimeSheetsByProjectId(selectedValueProject.id).then(response => {
			setTimeSheets(response.data);
			console.log(timeSheets);
		})
	}

	function searchByThreeParameters(selectedValue){
		console.log(selectedValue.id);
		TimeSheetActivityService.getTimeSheetsByProjectId(selectedValue.id).then(response => {
			setTimeSheets(response.data);
			console.log(timeSheets);
		})
	}


    return (
        <div class="container">
		<div class="wrapper">
			<section class="content">
				<h2><i class="ico report"></i>Reports</h2>
				<div class="grey-box-wrap reports">
					<ul class="form">
						<li>
							<select name="teamMember"
								onChange={handleChangeTeamMember}
								getOptionLabel={ member => member.username}
								getOptionValue={member => member.username}
								defaultOptions
								value={selectedValue}
							>
								{
									teamMembers.map((member) => (
										<option key={member.id}> {member.id} </option>
									))
								}
								
							</select>
						</li>
						<li>
							<select name="project"
								onChange={handleChangeProject}
							>
								{
									projects.map((project) => (
										<option key={project.id}
										value={project.projectName}> {project.projectName} </option>
									))
								}
							</select>
						</li>			
					</ul>
					<ul class="form">
						<li>
							
						</li>						
						<li>
							<label>Start date:</label>
							<input type="text" type="datepicker" class="in-text datepicker" />
						</li>
					</ul>
					<ul class="form last">
						<li>
							<select name="category">
								<option>Select Category</option>
								{
									categories.map((category) => (
										<option key={category.id}> {category.projectName} </option>
									))
								}
							</select>
						</li>
						<li>
							<label>End date:</label>
							<input type="text" class="in-text datepicker" />
						</li>
						<li>
							<a onClick={() => reset()} class="btn orange right">Reset</a>
							<a onClick={() => search(selectedValueProject,selectedValueCategory, selectedValueTeamMember)} class="btn green right">Search</a>
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
