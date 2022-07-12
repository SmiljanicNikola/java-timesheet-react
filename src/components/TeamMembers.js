import React, { useState, useEffect } from 'react'
import TeamMemberService from '../services/TeamMemberService';
import { NewMemberForm } from './NewMemberForm';
import Pagination from './Pagination';
import axios from 'axios';


export const TeamMembers = () => {

    const [teamMembers, setTeamMembers] = useState([]);
	const [display, setDisplay] = useState(false);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedTeamMembers, setPaginatedTeamMembers] = useState([])
	const [teamMembersPerPage, setTeamMembersPerPage] = useState(2);
	const [pageNumber, setPageNumber] = useState(0);

	const [name, setName] = useState('')
	const [hoursPerWeek, setHoursPerWeek] = useState('')
	const [email, setEmail] = useState('')
	const [teamMember, setTeamMember] = useState({})
	let updatedTeamMember = {}

    useEffect(() => {

        console.log(pageNumber);
		const fetchPaginatedTeamMembers = async () =>{
		axios.get("http://localhost:8080/api/teamMembers/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
			})
		};

		fetchPaginatedTeamMembers();
        
		axios.get("http://localhost:8080/api/teamMembers/paginate")
        .then(response => {
			setTeamMembers(response.data.content);
		})
		
    }, []);

    function saveTeamMember(id){
		
		TeamMemberService.getTeamMemberById(id).then(response => {
			setTeamMember(response.data)
			console.log(teamMember.data);

			updatedTeamMember = {
				id:teamMember.id,
				firstname: teamMember.firstname,
				hoursPerWeek: hoursPerWeek,
				username: teamMember.username,
				email: teamMember.email
			}

			TeamMemberService.updateTeamMember(id,updatedTeamMember);

			console.log(updatedTeamMember);
		})
    }

    function deleteTeamMember(id){
		TeamMemberService.deleteTeamMember(id);
		paginatedTeamMembers.pop(teamMember => teamMember.id == id);
		paginatedTeamMembers.filter(teamMember => teamMember.id !== id);

    }

    function resetPassword(id){
        console.log('delete')

    }

    function toggleModal(){
        setDisplay(true)
	}

	const handleNameChange = (e) =>{
		setName(e.target.value)
		console.log(name);
	}

	const handleHoursPerWeekChange = (e) =>{
		setHoursPerWeek(e.target.value)
		console.log(hoursPerWeek);
	}

	
	const nextPage = async () => {

		console.log('NEXT')
		let nextPage = currentPage + 1;
		console.log(nextPage)
		
		axios.get("http://localhost:8080/api/teamMembers/paginate?page="+nextPage+"&size=2")
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
			})
	}	

	const previousPage = async () => {

		console.log('PERVIOUS')
		let previousPage = currentPage - 1;
		console.log(previousPage)
		
		axios.get("http://localhost:8080/api/teamMembers/paginate?page="+previousPage+"&size=2")
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
			})
	}	

    const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);
		axios.get("http://localhost:8080/api/teamMembers/paginate?page="+currentPage+"&size=2")
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
			})
	}

	const indexOfLastClient = currentPage * teamMembersPerPage;
	const indexOfFirstClient = indexOfLastClient - teamMembersPerPage;
	const currentClients = teamMembers.slice(indexOfFirstClient, indexOfLastClient); 

    return (
        <div>
            <div class="wrapper">
			<section class="content">
				<h2><i class="ico team-member"></i>Team members</h2>
				<div class="grey-box-wrap reports ico-member">
					<a onClick={ () => toggleModal()} class="link new-member-popup test">
						<span>Create new member</span>
					</a>
				</div>
                <NewMemberForm display={display}>

                </NewMemberForm>



				<div class="accordion-wrap">
					
                {paginatedTeamMembers.map((member) => (
			<tr key={member.id}>

              <div class="item">
						<div class="heading">
							<span>{member.firstname} {member.lastname}</span>
							<i>+</i>
						</div>
						<div class="details">
							<ul class="form">
								<li>
									<label>Name:</label>
									<input type="text" defaultValue={member.firstname} onChange={e => setTeamMember({...member, firstname:e.target.value})} class="in-text" />
								</li>								
								<li>
									<label>Hours per week:</label>
									<input type="text" defaultValue={member.hoursPerWeek} onChange={handleHoursPerWeekChange} class="in-text" />
								</li>
							</ul>
							<ul class="form">
								<li>
									<label>Username:</label>
									<input type="text" onChange={e => setTeamMember({...member, username:e.target.value})} defaultValue={member.username} class="in-text" />
								</li>
								<li>
									<label>Email:</label>
									<input type="text" defaultValue={member.email} onChange={e => setTeamMember({...member, email:e.target.value})} class="in-text" />
								</li>								
							</ul>
							<ul class="form last">
								<li>
									<label>Status:</label>
									<span class="radio">
										<label for="inactive">Inactive:</label>
										<input type="radio" value="1" name="status" id="inactive" />
									</span>
									<span class="radio">
										<label for="active">Active:</label>
										<input type="radio" value="2" name="status" id="active" />
									</span>
								</li>
								<li>
									<label>Role:</label>
									<span class="radio">
										<label for="admin">Admin:</label>
										<input type="radio" value="1" name="status" id="admin" />
									</span>
									<span class="radio">
										<label for="worker">Worker:</label>
										<input type="radio" value="2" name="status" id="worker" />
									</span>
								</li>
							</ul>
							<div class="buttons">
								<div class="inner">
									<a href="javascript:;" onClick={ () => saveTeamMember(member.id)} class="btn green">Save</a>
									<a href="#" onClick={ () => deleteTeamMember(member.id)} class="btn green" class="btn red">Delete</a>
									<a href="javascript:;" onClick={ () => resetPassword(member.id)} class="btn green" class="btn orange">Reset Password</a>
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
								clientsPerPage={teamMembersPerPage}
								totalClients={teamMembers.length}
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
