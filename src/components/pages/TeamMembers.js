import React, { useState, useEffect } from 'react'
import TeamMemberService from '../../services/TeamMemberService';
import { NewMemberForm } from '../forms/NewMemberForm';
import Pagination from '../utils/Pagination';
import axios from 'axios';

export const TeamMembers = () => {

    const [teamMembers, setTeamMembers] = useState([]);
	const [display, setDisplay] = useState(false);
	const [currentPage, setCurrentPage] = useState(1);
	const [paginatedTeamMembers, setPaginatedTeamMembers] = useState([])
	const [teamMembersPerPage, setTeamMembersPerPage] = useState(2);
	const [pageNumber, setPageNumber] = useState(0);
	const [hoursPerWeek, setHoursPerWeek] = useState('')
	const [teamMember, setTeamMember] = useState({})
	const [size, setSize] = useState(2);
	const [blocked, setBlocked] = useState(false)
	let updatedTeamMember = {}

    useEffect(() => {

		const fetchPaginatedTeamMembers = async () =>{
			TeamMemberService.getMembersPaginateWithParams(currentPage, size)
			.then(response => {
			setPaginatedTeamMembers(response.data.content);
			})
		};

		fetchPaginatedTeamMembers();
        
		TeamMemberService.getTeamMembersPaginate()
		.then(response => {
			setTeamMembers(response.data.content);
		})

    }, []);

    function saveTeamMember(id){
		
		TeamMemberService.getTeamMemberById(id).then(response => {
			setTeamMember(response.data)

			updatedTeamMember = {
				id:teamMember.id,
				firstname: teamMember.firstname,
				username: teamMembers.username,
				hoursPerWeek: hoursPerWeek,
				username: teamMember.username,
				email: teamMember.email
			}

			TeamMemberService.updateTeamMember(id,updatedTeamMember);
			window.location.reload();
		})

    }

    function deleteTeamMember(id){
		TeamMemberService.deleteTeamMember(id);
		window.location.reload();
    }

    function resetPassword(id){
    }

    function toggleModal(){
        setDisplay(true)
	}

	const handleHoursPerWeekChange = (e) =>{
		setHoursPerWeek(e.target.value)
	}
	
	const nextPage = async () => {

		let nextPage = currentPage + 1;
		displayPaginated(nextPage, size, TeamMemberService.getMembersPaginateWithParams, setPaginatedTeamMembers)
	}	


	const previousPage = async () => {

		let previousPage = currentPage - 1;
		displayPaginated(previousPage, size, TeamMemberService.getMembersPaginateWithParams, setPaginatedTeamMembers)

	}	

	const displayPaginated = async (pageNumber, size, getItemsFunc, displayItemsFunc) => {
		setCurrentPage(pageNumber);

		getItemsFunc(pageNumber, size).then(response => {
			displayItemsFunc(response.data.content);
		});
	}

	/*const nextPage = async () => {   

		let nextPage = currentPage + 1;
		setCurrentPage(nextPage);
		
		TeamMemberService.getMembersPaginateWithParams(nextPage, size)
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
		})

	}	

	const previousPage = async () => {

		let previousPage = currentPage - 1;
		setCurrentPage(previousPage);
		
		TeamMemberService.getMembersPaginateWithParams(previousPage, size)
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
		})

	}*/


    const paginate = (pageNumber) => {
		setCurrentPage(pageNumber);

		TeamMemberService.getMembersPaginateWithParams(currentPage, size)
		.then(response => {
			setPaginatedTeamMembers(response.data.content);
		})

	}

	const handleRoleInputWorker = () => {

	}

	const handleRoleInputAdmin = () => {

	}

	const handleActiveInput = () => {
		setBlocked(true);
	}

	const handleInactiveInput = () => {
		setBlocked(false);
	}

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
									<input type="radio" checked={member.blocked == true} onChange={handleInactiveInput} id="inactive" />
								</span>
								<span class="radio">
									<label for="active">Active:</label>
									<input type="radio" onChange={handleActiveInput} checked={member.blocked == false}  id="active" />
								</span>
							</li>
							<li>
								<label>Role:</label>
								<span class="radio">
									<label for="admin">Admin:</label>
									<input type="radio" onChange={handleRoleInputAdmin} checked={member.role == 'ADMIN'}  id="admin" />
								</span>
								<span class="radio">
									<label for="worker">Worker:</label>
									<input type="radio" onChange={handleRoleInputWorker} checked={member.role == 'WORKER'} id="worker" />
								</span>
							</li>
						</ul>
						<div class="buttons">
							<div class="inner">
								<a onClick={ () => saveTeamMember(member.id)} class="btn green">Save</a>
								<a onClick={ () => deleteTeamMember(member.id)} class="btn red">Delete</a>
								<a onClick={ () => resetPassword(member.id)} class="btn orange">Reset Password</a>
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
							<button onClick={() => previousPage()} style={{marginTop:'15px', marginRight:'5px'}}>
								Pervious
							</button>
						</li>
						<li>
							<Pagination
								clientsPerPage={teamMembersPerPage}
								totalClients={teamMembers.length}
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
        </div>
    )
}
