import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';

export const TimeSheet = () => {
	const [date, setDate] = useState(new Date());
	const [month, setMonth] = useState(date.getMonth())
	const [time, setTime] = useState(date.getTime())
	const [year, setYeat] = useState(date.getFullYear())

	const [days, setDays] = useState([]);
	const [timeSheetActivities, setTimeSheetActivities] = useState([]);

	useEffect(() => {
		
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)

		})
		
		console.log(month)

		getDaysInMonth(month,year)
		//setDays(getDaysInMonth(month,year));

	}, [])

	function nextMonth(date){
		
		setMonth(month + 1);
		getDaysInMonth(month,year)
		while(date.getMonth() === month){
			//console.log('evo')
			//days.push(new Date(date));
			
			date.setDate(date.getDate() +1);
			
		}
	}

	function perviousMonth(date){
		setMonth(month - 1)
		getDaysInMonth(month,year)

		while(date.getMonth() === month){
			//days.push(new Date(date));
			/*console.log('PRE')
			console.log(date)*/
			date.setDate(date.getDate() -1);
			/*console.log('POSLE')
			console.log(date)*/

		}
	}

	function getDaysInMonth(month,year){
		let date = new Date(year,month);
		let days =[];
		console.log('ee')
		while(date.getMonth() === month){
			days.push(new Date(date));
			console.log('oo')
			
			date.setDate(date.getDate() +1);
		}
		console.log(days)
		setDays(days);
		return days
	}

    return (
        <div>
            <div class="wrapper">
				
			<section class="content">
				<h2><i class="ico timesheet"></i>TimeSheet</h2>
				<div class="grey-box-wrap">
					<div class="top">
						<a class="prev" onClick={() => perviousMonth(date)}><i class="zmdi zmdi-chevron-left"></i>previous month</a>
							<span class="center">{date.toString().slice(4,8)} {date.toString().slice(11,15)}</span>
						<a class="next" onClick={() => nextMonth(date)}>next month<i class="zmdi zmdi-chevron-right"></i></a>
					</div>
					<div class="bottom">
						
					</div>
				</div>
				<table class="month-table">
					<tr class="head">
						<th style={{width:'129px'}}>monday</th>
						<th style={{width:'129px'}}>tuesday</th>
						<th style={{width:'129px'}}>wednesday</th>
						<th style={{width:'129px'}}>thursday</th>
						<th style={{width:'129px'}}>friday</th>
						<th style={{width:'129px'}}>saturday</th>
						<th style={{width:'129px'}}>sunday</th>
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
								<td onClick={() => console.log(day.toLocaleString())} style={{textAlign:'center'}}>
									<div style={{textAlign:'left'}}>{day.toDateString().slice(7,10)+"."}</div>	
									
									<div style={{backgroundColor:'#ECFFDC',height:'40px', alignContent:'center',marginTop:'5px'}}>{"8 Hours"}</div>
								</td>	
							))}
						</>
					</tr>
				</table>
				<table >
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
