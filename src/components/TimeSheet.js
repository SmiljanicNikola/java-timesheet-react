import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import { CalendarComponent } from './CalendarComponent';
import {Calendar} from './Calendar'
import {format} from 'date-fns'

export const TimeSheet = () => {

	const today = new Date();
	//const [selectedDate, setSelectedDate] = useState(today);
	const [date, setDate] = useState(new Date());
	const [selectedDate, setSelectedDate] = useState(today);
	const daysInWeek = [1, 2, 3, 4, 5, 6, 0];

	const [month, setMonth] = useState(date.getMonth())
	const [time, setTime] = useState(date.getTime())
	const [year, setYeat] = useState(date.getFullYear())
	const [days, setDays] = useState([]);
	const [timeSheetActivities, setTimeSheetActivities] = useState([]);
	const {firstDayInMonth,
		todayFormatted,
		daysShort, 
		monthNames,
		getNextMonth, 
		getPrevMonth,
		daysInMonth, 
		selectedMonthLastDate} = Calendar();
	const startingPoint = daysInWeek.indexOf(firstDayInMonth) + 1;

	useEffect(() => {	
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
			
		})
		
		getDaysInMonth(month,year)
		
		
	}, [])

	function nextMonth(){
		setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() + 1, 1));
		getDaysInMonth(selectedDate.getMonth(), selectedDate.getFullYear())
		console.log(days);

		
	}

	function perviousMonth(){
		setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() - 1, 1));
		getDaysInMonth(selectedDate.getMonth(), selectedDate.getFullYear())
		console.log(days);
	}

	function getDaysInMonth(month,year){
		let date = new Date(year,month);
		let days =[];
		while(date.getMonth() === month){
			days.push(new Date(date));			
			date.setDate(date.getDate() +1);
		}

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
						<a class="prev" onClick={() => perviousMonth()}><i class="zmdi zmdi-chevron-left"></i>previous month</a>
							<span class="center">{selectedDate.toString().slice(4,8)} {selectedDate.toString().slice(11,15)}</span>
						<a class="next" onClick={() => nextMonth()}>next month<i class="zmdi zmdi-chevron-right"></i></a>
					</div>
					<div class="bottom">
						
					</div>
				</div>
				<table class="month-table">
					<tr class="head">
						{daysShort.map((dayShort) => ( 
							<th style={{width:'130px'}}>{dayShort}</th>
						))}
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
							{days.map((date) => (
								
								<td onClick={() => console.log(date.toLocaleString())} style={{textAlign:'center'}}>
									<div style={{textAlign:'left'}}>{date.toDateString().slice(7,10)+"."}</div>	
									
									<div style={{backgroundColor:'#ECFFDC',height:'40px', alignContent:'center',marginTop:'5px'}}>{"8 Hours"}</div>
									{timeSheetActivities.map(activity => (
                                                        <div>
                                                           
                                    						
                                                            {(activity.date.toLocaleString() == (format(date, 'yyyy-dd-mm')).slice(0,7)+'-888'+(format(date, 'yyyy-dd-mm')).slice(9,10))
                                                                || (activity.date.toLocaleString() == date)
                                                            ? (
                                                                <div>
                                                                    

                                                                    <div style={{color:'black',backgroundColor:'#90EE90'}}>{activity.time}</div>

                                                               
                                                                    
                                                                </div>
                                                            
                                                            ) : (
                                                            <div>
                                                                <div></div>

                                                            </div>
                                                            )}                                                     
                                                        </div> 
                                                    ))}

								</td>	
							))}
						</>
					</tr>
				</table>
				<table >
				

				</table>
				<div class="total">
					<span>Total hours: <em>90</em></span>
				</div>
			</section>			
			</div>


		<br></br><br></br>

		
			{/*<CalendarComponent />*/}
			
		
        </div>
    )
}
