import React,{useState, useEffect, useRef} from 'react'
import { CalendarDays } from './CalendarDays';
import {Calendar} from './Calendar';
import TimeSheetActivityService from '../../services/TimeSheetActivityService';
import CalendarUtil from '../utils/CalendarUtil'
import TeamMemberService from '../../services/TeamMemberService';
import { AuthenticationService } from '../../services/AuthenticationService';

export const Calendar2 = () => {

    const today = new Date();
    const [selectedDate, setSelectedDate] = useState(today);
    const selectedMonthFirstDate = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), 0);
    const selectedMonthLastDate = new Date(selectedDate.getFullYear(), selectedDate.getMonth() + 1, 0);
    const {daysShort} = Calendar();
    const [hoursPerDay] = useState(0)
    const [timeSheetActivities, setTimeSheetActivities] = useState([]);
    const [totalMonthTime, setTotalMonthTime] = useState(0)
    const startDate = CalendarUtil.findFirstDayOfTheWeek(selectedMonthFirstDate)
    const dates = CalendarUtil.getDatesFromStartPointToEndPoint(startDate, selectedMonthLastDate);
    let totalHours = 0;
    const [username] = useState(AuthenticationService.getUsername());
	const [role] = useState(AuthenticationService.getRole());
	const [setLoggedUser] = useState({})

    useEffect(() => {

		TeamMemberService.getTeamMemberByUsername(username).then((response => {
			setLoggedUser(response.data);
		}))

        let startDate = (dates[0].toISOString().slice(0,10))
        let endDate = (dates[dates.length-1].toISOString().slice(0,10))

        TimeSheetActivityService.getTimeSheetsBetweenStartDateAndEndDate(startDate, endDate).then(response => {
            setTimeSheetActivities(response.data)
        })

        countingTotalHoursInMonth()
        
    }, []);


    const getPrevMonth = () => {

        setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() - 1));
        
        TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
        })
        
    }

    const getNextMonth = () => {

        setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() + 1));
        
        TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
        })

    }


    function countingTotalHoursInMonth(){
        let totalTime = 0;
        for(let a = 0; a< timeSheetActivities.length; a++){
            totalTime = totalTime + (timeSheetActivities[a].time)
            timeSheetActivities[a].time = null;
            setTotalMonthTime(totalMonthTime + totalTime);
        }
    }

    for(let i = 0; i < timeSheetActivities.length; i++){
        totalHours = totalHours + timeSheetActivities[i].time
    }

    return (
        <div class="wrapper">
            <section class="content">
                <h2><i class="ico timesheet"></i>TimeSheet</h2>
                <div class="grey-box-wrap">
                    <div class="top">
                        <a class="prev" onClick={getPrevMonth}><i class="zmdi zmdi-chevron-left"></i>previous month</a>
                            <span class="center"><p>{selectedDate.toString().slice(4,8)} {selectedDate.toString().slice(11,15)}</p></span>
                        <a class="next" onClick={getNextMonth} >next month<i class="zmdi zmdi-chevron-right"></i></a>
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
                        
                        {
                        <CalendarDays dates={dates} hours={hoursPerDay} timeSheetActivities={timeSheetActivities}/>
                        }
                       
                    </tr>
                </table>
                <br></br>
                <div class="total">
                    <span>Total hours: <em></em></span>
                </div>
            </section>
        </div>   
    )
}
