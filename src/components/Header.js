import React from 'react'
import '../assets/css/style.css'
import Logo from '../assets/img/logo.png'

export const Header = () => {
    return (
        <div>
            <header class="header">
			<div class="top-bar"></div>
			<div class="wrapper">
				<a href="index.html" class="logo">
					<img src={Logo} alt="VegaITSourcing Timesheet" />
				</a><br></br>
				<ul class="user right">
					<li>
						<a href="javascript:;">Sladjana Miljanovic</a>
						<div class="invisible"></div>
						<div class="user-menu">
							<ul>
								<li>
									<a href="javascript:;" class="link">Change password</a>
								</li>
								<li>
									<a href="javascript:;" class="link">Settings</a>
								</li>
								<li>
									<a href="javascript:;" class="link">Export all data</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="last">
						<a href="javascript:;">Logout</a>
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
							<a href="projects" class="btn nav active">Projects</a>
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
								<a href="javascript:;">TimeSheet</a>
							</li>
							<li>
								<a href="javascript:;">Clients</a>
							</li>
							<li>
								<a href="javascript:;">Projects</a>
							</li>
							<li>
								<a href="javascript:;">Categories</a>
							</li>
							<li>
								<a href="javascript:;">Team members</a>
							</li>
							<li class="last">
								<a href="javascript:;">Reports</a>
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
