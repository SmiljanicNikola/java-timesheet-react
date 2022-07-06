import React, { useState, useEffect } from 'react'
import TeamMemberService from '../services/TeamMemberService';
import { NewMemberForm } from './NewMemberForm';
import {useNavigate} from 'react-router-dom'

export const TeamMembers = () => {

    const [teamMembers, setTeamMembers] = useState([]);
    const navigate = useNavigate();
    const [display, setDisplay] = useState(false);


    useEffect(() => {
        TeamMemberService.getTeamMembers().then((response ) => setTeamMembers(response.data))
    }, []);

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
					
                {teamMembers.map((member) => (
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
									<input type="text" value={member.firstname} class="in-text" />
								</li>								
								<li>
									<label>Hours per week:</label>
									<input type="text" value={member.hoursPerWeek} class="in-text" />
								</li>
							</ul>
							<ul class="form">
								<li>
									<label>Username:</label>
									<input type="text" value={member.username} class="in-text" />
								</li>
								<li>
									<label>Email:</label>
									<input type="text" value={member.email} class="in-text" />
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

              <td>
              {/*<button className="btn btn-success" onClick={ () => skiniPDGradjanina(gradjanin.korisnickoIme)}>Preuzmi PDF Zaposlenja</button>*/}
              </td>
            </tr> 
            
          ))}
        

				</div>
				<div class="pagination">
					<ul>
						<li>
							<a href="javascript:;">1</a>
						</li>
						<li>
							<a href="javascript:;">2</a>
						</li>
						<li>
							<a href="javascript:;">3</a>
						</li>
						<li class="last">
							<a href="javascript:;">Next</a>
						</li>
					</ul>
				</div>
			</section>			
		</div>
        </div>
    )
}
