import React,{useState, useEffect, useRef} from 'react'
import { CalendarDays } from './CalendarDays';
import {Calendar} from './Calendar';
import TimeSheetActivityService from '../services/TimeSheetActivityService';

export const Calendar2 = () => {

    const today = new Date();
    const todayFormatted = `${today.getDate()}-${+today.getMonth() + 1}-${today.getFullYear()}`;
    const daysInWeek = [1, 2, 3, 4, 5, 6, 0];
    const [selectedDate, setSelectedDate] = useState(today);
    const selectedMonthFirstDate = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), 0);
    const selectedMonthLastDate = new Date(selectedDate.getFullYear(), selectedDate.getMonth() + 1, 0);
    const firstDayInMonth = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), 1).getDay();
    const {daysShort} = Calendar();
    const [hoursPerDay, setHoursPerDay] = useState(0)
    const [timeSheetActivities, setTimeSheetActivities] = useState([]);
    const [rangeOne,setRangeOne] = useState();
    const [rangeTwo,setRangeTwo] = useState();
    const[initialMount, setInitialMount] = useState(true);
    const [totalMonthTime, setTotalMonthTime] = useState(0)

    const firstDayOfTheMonth=new Date(selectedDate.getFullYear(),selectedDate.getMonth(),1);
    console.log(firstDayOfTheMonth)
    console.log('e')
    console.log(selectedMonthLastDate)

    const startDate = firstDayOfTheWeek(selectedMonthFirstDate)
    const dates = getDatesFromStartPointToEndPoint(startDate, selectedMonthLastDate);


    useEffect(() => {
        console.log(dates[0])
        let startDate = (dates[0].toISOString().slice(0,10))
        let endDate = (dates[34].toISOString().slice(0,10))
        TimeSheetActivityService.getTimeSheetsBetweenStartDateAndEndDate(startDate, endDate).then(response => {
			setTimeSheetActivities(response.data)
        })

        countingTotalHoursInMonth()
        
    }, []);


    function firstDayOfTheWeek(selectedDate){//Ovde je svuda bilo date umesto selectedDate

        let dayOfTheWee = selectedDate.getDay();

        let firstDayOfTheWeek = selectedDate.getDate() +1 - dayOfTheWee;
     
        return new Date(selectedDate.setDate(firstDayOfTheWeek))

    }


    function getDatesFromStartPointToEndPoint(startDate, selectedMonthLastDate){
        const dates =[];

        for(let i = startDate; i<= selectedMonthLastDate; i.setDate(i.getDate() + 1)){
            dates.push(new Date(i));
        }
        return dates;
    }

    const getPrevMonth = () => {
        setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() - 1));
        let startDate = (dates[0].toISOString().slice(0,10))
        let endDate = (dates[34].toISOString().slice(0,10))
        TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
		})
    }

    const getNextMonth = () => {
        setSelectedDate(prevValue => new Date(prevValue.getFullYear(), prevValue.getMonth() + 1, 1));
        let startDate = (dates[0].toISOString().slice(0,10))
        let endDate = (dates[34].toISOString().slice(0,10))
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
                        <>
                        {
                        <CalendarDays dates={dates} hours={hoursPerDay} timeSheetActivities={timeSheetActivities}/>
                        }
                        </>
                    </tr>
                </table>
                <br></br>
                <div class="total">
                    <span>Total hours: <em>{totalMonthTime}</em></span>
                </div>
            </section>
        </div>   
    )
}
