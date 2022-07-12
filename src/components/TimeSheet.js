import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import Calendar from "react-calendar"

export const TimeSheet = () => {
	const [date, setDate] = useState(new Date());
	const [month, setMonth] = useState(date.getMonth())
	const [year, setYeat] = useState(date.getFullYear())
	const [days, setDays] = useState([]);
	const [weeks, setWeeks] = useState([1,2,3,4,5]);
	const [timeSheetActivities, setTimeSheetActivities] = useState([]);

	useEffect(() => {
		
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
			console.log(timeSheetActivities);

			console.log(date)
		})
		
		getDaysInMonth(month,year)
		console.log('Odavde krece')
		console.log(month)
		console.log(year)
		console.log(days)

	}, [])

	function nextMonth(){

		let date = new Date(year,month+1);
		let days =[];
		while(date.getMonth() === month){
			days.push(new Date(date));
			
			date.setDate(date.getDate() +1);
		}
		console.log(days)
		setDays(days);
	}

	function getDaysInMonth(month,year){
		let date = new Date(year,month);
		let days =[];
		while(date.getMonth() === month){
			days.push(new Date(date));
			
			date.setDate(date.getDate() +1);
		}
		console.log(days)
		setDays(days);
		return days
	}

	function renderDays(){
		{days.map((day) => ( 	
			{day}
		))}
	}

    return (
        <div>
            <div class="wrapper">
				
			<section class="content">
				<h2><i class="ico timesheet"></i>TimeSheet</h2>
				<div class="grey-box-wrap">
					<div class="top">
						<a class="prev"><i class="zmdi zmdi-chevron-left"></i>previous month</a>
						<span class="center">{date.toString()}</span>
						<a class="next" onClick={() => nextMonth()}>next month<i class="zmdi zmdi-chevron-right"></i></a>
					</div>
					<div class="bottom">
						
					</div>
				</div>
				<table class="month-table">
					<tr class="head">
						<th style={{width:'128px'}}><span>monday</span></th>
						<th style={{width:'129px'}}>tuesday</th>
						<th>wednesday</th>
						<th>thursday</th>
						<th>friday</th>
						<th>saturday</th>
						<th>sunday</th>
					</tr>
					<tr class="mobile-head">
						<th>mon</th>
						<th>tue</th>
						<th>wed</th>
						<th>thu</th>
						<th>fri</th>
						<th>sat</th>
						<th>sun</th>
					</tr>
					<tr>
						<>
							{days.map((day) => (
								<td onClick={() => console.log(day)}>
									{day.toDateString()}
									<br>
									</br>
									{"8 Hours"}
								</td>	
							))}
						</>
					</tr>
				</table>
				<table style={{height:'300px'}} >
				{/*<Calendar value={date}  onChange={date => setDate(date)}/>*/}

				</table>
				<div class="total">
					<span>Total hours: <em>90</em></span>
				</div>
			</section>			
		</div>
        </div>
    )
}
