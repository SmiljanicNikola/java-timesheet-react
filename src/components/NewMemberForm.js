import React, { useEffect, useState } from 'react'
import './style.css'
import {useNavigate} from 'react-router-dom'


export const NewMemberForm = (props) => {
	const [display, setDisplay] = useState(props.value);
	const navigate = useNavigate();

	function closePopup(){
		setDisplay(false);
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
								<input type="text" class="in-text" />
							</li>								
							<li>
								<label>Hours per week:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Username:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Email:</label>
								<input type="text" class="in-text" />
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
								<a class="btn green">Invite team member</a>
							</div>
						</div>
					</div>
				</div>
        </div>) : <div></div>
    )
}
