import React, {useEffect,useState,Fragment} from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import {Calendar} from './Calendar'
import {format} from 'date-fns'
import { useNavigate } from "react-router-dom";


export const CalendarComponent = () => {
    
    const {calendarRows, selectedDate, todayFormatted, daysShort, monthNames, getNextMonth, getPrevMonth} = Calendar();
    const [days, setDays] = useState([]);
    const [timeSheetActivities, setTimeSheetActivities] = useState([]);
    const [totalHours, setTotalHours] = useState(0);
    const navigate = useNavigate();
    const [hours, setHours] = useState(0);

    const dateClickHandler = date =>{
        console.log(date);
    }

    const viewDays = date => {
        console.log(date);
        navigate(`/day/${date}`, JSON.stringify(date))
    }

    useEffect(() => {
		
		TimeSheetActivityService.getTimeSheets().then(response => {
			setTimeSheetActivities(response.data)
		})
		console.log(timeSheetActivities)

    }, [])
    
    for(let a = 0; a< Object.keys(timeSheetActivities).length; a++){
        for(let b = a+1; b < Object.keys(timeSheetActivities).length; b++){
            if(timeSheetActivities[a].date == timeSheetActivities[b].date ){
                timeSheetActivities[a].time += timeSheetActivities[b].time;
                timeSheetActivities[b].time = null;
            }
        }
    }

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
                                                    {col.value+'.'}
                                                    
                                                    <br></br><br></br>
                                                    <div style={{textAlign:'center'}}>
                                                        <p>Hours:</p>
                                                    </div>
                                                    {timeSheetActivities.map(activity => (
                                                        <div>
                                                           
                                                            {/*{console.log('////////////////Col date:')}*/}
                                                            {/*{console.log((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,9)))}*/}
                                                            {/*{console.log(((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,7)+'0')+col.date.slice(7,8)))}*/}
                                                           {/*{console.log(activity.date.toLocaleString() == ((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,9))))}*/}
                                                           {console.log(activity.date.toLocaleString() == ((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,9))))}

                                                            {(activity.date.toLocaleString() == ((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,9))))
                                                                || (activity.date.toLocaleString() == ((col.date.split('-').reverse().join('-').slice(0,4)+'-0'+col.date.split('-').reverse().join('-').slice(5,7)+'0')+col.date.slice(7,8)))
                                                            ? (
                                                                <div>
                                                                    

                                                                    <div style={{color:'black',backgroundColor:'#90EE90'}} onClick={() => viewDays(activity.date)}>{activity.time}</div>

                                                                {/*{activity.time > 4 ?
                                                                        (
                                                                        <div style={{color:'black',backgroundColor:'#90EE90'}} onClick={() => viewDays(activity.date)}>{'Hours:'+activity.time}</div>
                                                                        ):
                                                                        <div style={{color:'black',backgroundColor:'#FA8072'}}  onClick={() => viewDays(activity.date)}>{'Hours:'+activity.time}</div>    
                                                                
                                                                }*/}
                                                                    
                                                                </div>
                                                            
                                                            ) : (
                                                            <div>
                                                                <div></div>

                                                            </div>
                                                            )}                                                     
                                                        </div> 
                                                    ))}
                                                    {}
                                                
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
