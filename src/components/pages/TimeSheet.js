import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../../services/TimeSheetActivityService';
import { CalendarComponent } from '../calendar-feature/CalendarComponent';
import {Calendar} from '../calendar-feature/Calendar'
import { Calendar2 } from '../calendar-feature/Calendar2';

export const TimeSheet = () => {

	const today = new Date();
	const [date, setDate] = useState(new Date());
	const [selectedDate, setSelectedDate] = useState(today);
	const daysInWeek = [1, 2, 3, 4, 5, 6, 0];
	const [timeSheetActivities, setTimeSheetActivities] = useState([]);
	const {
		  firstDayInMonth,
		  daysShort, 
		  monthNames,
		  getNextMonth, 
		  getPrevMonth,
		  selectedMonthLastDate
		} = Calendar();

	useEffect(() => {
			
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
		})

	}, [])

    return (
        <div>
			<br></br><br></br>
			{/*<CalendarComponent />*/}
			<Calendar2/>
        </div>
    )
}
