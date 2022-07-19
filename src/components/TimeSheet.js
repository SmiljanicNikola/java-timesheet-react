import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import { CalendarComponent } from './CalendarComponent';
import {Calendar} from './Calendar'
import {format} from 'date-fns'
import { Calendar2 } from './Calendar2';

export const TimeSheet = () => {

	const today = new Date();
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
			<br></br><br></br>
			{/*<CalendarComponent />*/}
			<Calendar2/>
        </div>
    )
}
