import React,{useState, useEffect} from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import ClientService from '../services/ClientService';
import ProjectService from '../services/ProjectService';
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import { Calendar } from './Calendar';

export const Days = () => {

	const [timeSheets, setTimeSheets] = useState([])
	const navigate = useNavigate();
	const params = useParams();
	const [clients, setClients] = useState([])
	const [projects, setProjects] = useState([])

	useEffect(() => {
		TimeSheetActivityService.searchByDate(params.date).then((response => {
			setTimeSheets(response.data);
		}))

		ClientService.getClients().then((response => {
			setClients(response.data);
		}))

		ProjectService.getProjects().then((response => {
			setProjects(response.data);
		}))
		
	  }, []);

    return (
		<div class="container">
			<div class="wrapper">
				<section class="content">
					<h2><i class="ico timesheet"></i>TimeSheet</h2>
					<div class="grey-box-wrap">
						<div class="top">
							<a href="javascript:;" class="prev"><i class="zmdi zmdi-chevron-left"></i>previous week</a>
							<span class="center">February 04 - February 10, 2013 (week 6)</span>
							<a href="javascript:;" class="next">next week<i class="zmdi zmdi-chevron-right"></i></a>
						</div>
						<div class="bottom">
							<ul class="days">
								<li>
									<a href="javascript:;">
										<b>Feb 04</b>
										<i>7.5</i>
										<span>monday</span>
									</a>
								</li>
								<li>
									<a href="javascript:;">
										<b>Feb 06</b>
										<i>7.5</i>
										<span>tuesday</span>
									</a>
								</li>
								<li>
									<a href="javascript:;">
										<b>Feb 06</b>
										<i>7.5</i>
										<span>wednesday</span>
									</a>
								</li>
								<li class="active">
									<a href="javascript:;">
										<b>Feb 07</b>
										<i>7.5</i>
										<span>thursday</span>
									</a>
								</li>
								<li>
									<a href="javascript:;">
										<b>Feb 08</b>
										<i>7.5</i>
										<span>friday</span>
									</a>
								</li>
								<li>
									<a href="javascript:;">
										<b>Feb 09</b>
										<i>0.0</i>
										<span>saturday</span>
									</a>
								</li>
								<li class="last">
									<a href="javascript:;">
										<b>Feb 10</b>
										<i>0.0</i>
										<span>sunday</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
					<table class="default-table">
						<tr>
							<th>
								Client <em>*</em>
							</th>
							<th>
								Project <em>*</em>
							</th>
							<th>
								Category <em>*</em>
							</th>
							<th>Description</th>
							<th class="small">
								Time <em>*</em>
							</th>
							<th class="small">Overtime</th>
						</tr>
						{timeSheets.map((activity) => (	
						<tr>
							<td>
								<select>
								<option>{activity.project.client.clientName}</option>

								</select>
							</td>
							<td>
								<select>
									<option>{activity.project.projectName}</option>
								</select>
							</td>
							<td>
								<select>
									<option>{activity.category.type}</option>
								</select>
							</td>
							<td>
								<input type="text" defaultValue={activity.description} class="in-text medium" />
							</td>
							<td class="small">
								<input type="text" defaultValue={activity.time} class="in-text xsmall" />
							</td>
							<td class="small">
								<input type="text" defaultValue={activity.overtime} class="in-text xsmall" />
							</td>
						</tr>
						))}
						<tr>
							<td>
								<select>
									<option>Choose client</option>
									<option>Client 1</option>
									<option>Client 2</option>
								</select>
							</td>
							<td>
								<select>
									<option>Choose project</option>
									<option>Project 1</option>
									<option>Project 2</option>
								</select>
							</td>
							<td>
								<select>
									<option>Choose category</option>
									<option>Front-End Development</option>
									<option>Design</option>
								</select>
							</td>
							<td>
								<input type="text" class="in-text medium" />
							</td>
							<td class="small">
								<input type="text" class="in-text xsmall" />
							</td>
							<td class="small">
								<input type="text" class="in-text xsmall" />
							</td>
						</tr>
					</table>
					<div class="total">
						<a href="index.html"><i></i>back to monthly view</a>
						<span>Total hours: <em>7.5</em></span>
					</div>
				</section>			
			</div>
		</div>
    )
}
