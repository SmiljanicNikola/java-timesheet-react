import React, { useState, useEffect } from 'react'
import '../../assets/css/style.css'
import {AuthenticationService} from '../../services/AuthenticationService';
import Logo from '../../assets/img/logo.png'
import TeamMemberService from '../../services/TeamMemberService';
import { useNavigate } from "react-router-dom";


export const Header = () => {
	const [username, setUsername] = useState("");
	const [loggedUser, setLoggedUser] = useState({})

	useEffect(() => {

		const username = AuthenticationService.getUsername()
		if(username != null){
			setUsername(username);
		}
		console.log(username)

		TeamMemberService.getTeamMemberByUsername(username).then((response => {
			setLoggedUser(response.data);
			console.log(loggedUser)
		}))

	}, []);

	const logout = () =>{
		AuthenticationService.logout();
	}


    return (
        <div>
            <header class="header">
				<div class="top-bar">

				</div>
				<div class="wrapper">
					<a href="index.html" class="logo">
						<img src={Logo} alt="VegaITSourcing Timesheet" />
					</a>
					
					<ul class="user right">
						<li>
							{loggedUser.firstname != null || loggedUser.lastname != undefined
							?
							(
								<a>{loggedUser.firstname + " " + loggedUser.lastname}</a>
							)
							:
							(
								<a></a>
							)
							}
							
							<div class="invisible"></div>
							<div class="user-menu">
								<ul>
									<li>
										<a class="link">Change password</a>
									</li>
									<li>
										<a class="link">Settings</a>
									</li>
									<li>
										<a class="link">Export all data</a>
									</li>
								</ul>
							</div>
						</li>
						<li class="last">
							{loggedUser.username != null || loggedUser.username != undefined
							?
							(
								<a href="javascript:;" onClick={logout}>Logout</a>
							)
							:
							(
								<a href="javascript:;" onClick={logout}>Login</a>
							)
							}
							
						</li>
					</ul>
					<br></br>
					<nav>
						<ul class="menu">
							<li>
								<a href="timesheet" class="btn nav">TimeSheet</a>
							</li>
							<li>
								<a href="clients" class="btn nav">Clients</a>
							</li>
							<li>
								<a href="projects" class="btn nav">Projects</a>
							</li>
							<li>
								<a href="categories" class="btn nav">Categories</a>
							</li>
							<li>
								<a href="teamMembers" class="btn nav">Team members</a>
							</li>
							<li class="last">
								<a href="reports" class="btn nav">Reports</a>
							</li>
						</ul>
						<div class="mobile-menu">
							<a href="javascript:;" class="menu-btn">
								<i class="zmdi zmdi-menu"></i>
							</a>
							<ul>
								<li>
									<a>TimeSheet</a>
								</li>
								<li>
									<a >Clients</a>
								</li>
								<li>
									<a>Projects</a>
								</li>
								<li>
									<a>Categories</a>
								</li>
								<li>
									<a>Team members</a>
								</li>
								<li class="last">
									<a>Reports</a>
								</li>
							</ul>
						</div>					
						<span class="line"></span>
					</nav>
				</div>
			</header>
        </div>
    )
}
