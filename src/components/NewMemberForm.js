import React, { useEffect, useState } from 'react'
import '../assets/css/popup.css'
import {useNavigate} from 'react-router-dom'
import TeamMemberService from '../services/TeamMemberService';


export const NewMemberForm = (props) => {

	const [display, setDisplay] = useState(props.value);
	const navigate = useNavigate();
	const [member, setMember] = useState({})


	function closePopup(){
		setDisplay(false);
	}

	function saveTeamMember(member){
		let newMember = {
			firstname: member.firstname,
			lastname: member.lastname,
			username: member.username,
			password: member.password,
			hoursPerWeek: member.hoursPerWeek,
			email: member.email,
			blocked: false
		}

		TeamMemberService.createTeamMember(newMember);
    }

    return (
		(props.display == true)? (
        <div>
            <div class="popup">
					<div class="popup-inner">
						<h2>Create new team member</h2>
						<ul class="form">
							<li>
								<label>Name:</label>
								<input type="text" name="firstname" id="firstname" value={member.firstname} onChange={e => setMember({...member, firstname:e.target.value})} class="in-text" />
							</li>
							<li>
								<label>Lastname:</label>
								<input type="text" name="lastname" id="lastname" value={member.lastname} onChange={e => setMember({...member, lastname:e.target.value})} class="in-text" />
							</li>								
							<li>
								<label>Username:</label>
								<input type="text"  name="username" id="username" value={member.username} onChange={e => setMember({...member, username:e.target.value})} class="in-text" />
							</li>
							<li>
								<label>Passsword:</label>
								<input name="password" id="password" value={member.password} onChange={e => setMember({...member, password:e.target.value})} type="password" class="in-text" />
							</li>
							<li>
								<label>HoursPerWeek:</label>
								<input  name="hoursPerWeek" id="hoursPerWeek" value={member.hoursPerWeek} onChange={e => setMember({...member, hoursPerWeek:e.target.value})} type="text" class="in-text" />
							</li>
							<li>
								<label>Email:</label>
								<input  name="email" id="email" value={member.email} onChange={e => setMember({...member, email:e.target.value})} type="text" class="in-text" />
							</li>
							<li class="inline">
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
							<li class="inline">
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
						<button className="close-btn" onClick={ () => closePopup()}>
							Close
						</button>
						
						<div class="buttons">
							<div class="inner">
								<a class="btn green" onClick={() => saveTeamMember(member)}>Invite team member</a>
							</div>
						</div>
					</div>
				</div>
        </div>) : <div></div>
    )
}
