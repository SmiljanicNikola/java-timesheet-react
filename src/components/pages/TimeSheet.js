import React,{useState, useEffect} from 'react'
import TimeSheetActivityService from '../../services/TimeSheetActivityService';
import { CalendarComponent } from '../calendar-feature/CalendarComponent';
import {Calendar} from '../calendar-feature/Calendar'
import { Calendar2 } from '../calendar-feature/Calendar2';
import { Header } from '../layout/Header';
import { Footer } from '../layout/Footer';
import { AuthenticationService } from '../../services/AuthenticationService';
import TeamMemberService from '../../services/TeamMemberService';

export const TimeSheet = () => {

	const today = new Date();
	const [date, setDate] = useState(new Date());
	const [selectedDate, setSelectedDate] = useState(today);
	const daysInWeek = [1, 2, 3, 4, 5, 6, 0];
	const [timeSheetActivities, setTimeSheetActivities] = useState([]);
	const [username, setUsername] = useState(AuthenticationService.getUsername());
	const [role, setRole] = useState(AuthenticationService.getRole());
	const [loggedUser, setLoggedUser] = useState({})


	useEffect(() => {

	

	}, [])

    return (
        <div>
			<Header></Header><br></br><br></br>
			{/*<CalendarComponent />*/}
			<Calendar2/>

			<Footer></Footer>
        </div>
    )
}
