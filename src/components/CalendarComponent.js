import React, {useEffect,useState,Fragment} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import {Calendar} from './Calendar'
import {format} from 'date-fns'

export const CalendarComponent = () => {
    const {calendarRows, selectedDate, todayFormatted, daysShort, monthNames, getNextMonth, getPrevMonth} = Calendar();
    const [days, setDays] = useState([]);
    const [timeSheetActivities, setTimeSheetActivities] = useState([]);

    const dateClickHandler = date =>{
        console.log(date);
    }

    useEffect(() => {
		
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
		})
		console.log(timeSheetActivities)

	}, [])

    return (
        <Fragment>
            <div class="wrapper">
                <section class="content">
                <h2><i class="ico timesheet"></i>TimeSheet</h2>
				<div class="grey-box-wrap">
					<div class="top">
						<a class="prev" onClick={getPrevMonth}><i class="zmdi zmdi-chevron-left"></i>previous month</a>
							<span class="center"><p>{`${monthNames[selectedDate.getMonth()]} - ${selectedDate.getFullYear()}`}</p></span>
						<a class="next" onClick={getNextMonth}>next month<i class="zmdi zmdi-chevron-right"></i></a>
					</div>
					<div class="bottom">
						
					</div>
				</div>


                
                <table className="table">
                    <thead>
                        <tr>
                            {daysShort.map(day => (
                                <th key={day}>{day}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {
                        Object.values(calendarRows).map
                            (cols =>{
                                return <tr key={cols[0].date}>
                                            {cols.map(col => (
                                                col.date === todayFormatted
                                                ?
                                                <td key={col.date} className={`${col.classes} today`} onClick={() => dateClickHandler(col.date)}>
                                                    {col.value}
                                                </td>
                                                :
                                                <td key={col.date} className={col.classes} onClick={() => dateClickHandler(col.date)}>
                                                    {timeSheetActivities.map(activity => (
                                                        <div>
                                                            {console.log((col.date.split('-').reverse().join('-')).split(7,8)[0]+'0' + col.date.split(1,6)[0] + '-' + col.date.split('-')[0])}
                                                            {console.log('Col date')}
                                                            {console.log(col.date.split(4,1)[1])}
                                                           {console.log((col.date.split('-').reverse().join('-')).slice(0,5)[0]+'0' + col.date.split(1,6)[0] + '-' + col.date.split('-')[0])}
                                                            {activity.date.toLocaleString() == (col.date.split('-').reverse().join('-')).split(7,8)[0]+'0' + col.date.split(1,6)[0] + '-' + col.date.split('-')[0] ? (
                                                                <div><p>AAAAAAA</p></div>
                                                            
                                                            ) : (<div></div>)}
                                                            {console.log('activity date')}
                                                            {console.log(activity.date.toLocaleString())}
                                                        </div> 

                                                    ))}
                                                    {col.value+'.'}
                                                    <br></br>
                                                    {'Hours: '}
                                                
                                                </td>
                                            ))}
                                        </tr>
                            })
                        }
                    </tbody>
                </table><br></br>
                <div class="total">
					<span>Total hours: <em>90</em></span>
				</div>
                </section>
            </div>
        </Fragment>
    )
}
