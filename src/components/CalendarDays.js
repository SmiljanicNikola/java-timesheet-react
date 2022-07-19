import React, { useEffect, useState } from 'react'
import TimeSheetActivityService from '../services/TimeSheetActivityService';
import { useNavigate } from "react-router-dom";


export const CalendarDays = (props) => {
    
    const [dates, setDates] = useState(props.dates)
    const [startDate, setStartDate] = useState()
    const [endDate, setEndDate] = useState()
    const [timeSheetActivities, setTimeSheetActivities] = useState([]);
    const navigate = useNavigate();

    for(let a = 0; a< (props.timeSheetActivities).length; a++){
        for(let b = a+1; b < (props.timeSheetActivities).length; b++){
            if(props.timeSheetActivities[a].date == props.timeSheetActivities[b].date ){
                props.timeSheetActivities[a].time += props.timeSheetActivities[b].time;
                props.timeSheetActivities[b].time = null;
            }   
        }  
    }

    const viewDays = date => {
        console.log(date);
        navigate(`/day/${date}`, JSON.stringify(date))
    }

    return (
        props.dates.map((date) => (
            <td key={date} onClick={() => viewDays(date.toISOString().slice(0,10))}>
                <div>
                    <span >{date.toISOString().slice(0,10)}</span>
                    {/*<span>{date.localeDate().slice(0,10)}</span>*/}<br></br>
                    <span style={{textAlign:'center'}}><p>Hours:</p> 
                        {props.timeSheetActivities.map((activity) => (
                            <div>
                                {
                                activity.date == (date.toISOString().slice(0,10)) ? 
                                    (
                                    <>
                                        {activity.time > 7 ? 
                                        (
                                            <div>
                                                <div style={{color:'black',backgroundColor:'#90EE90'}}>{activity.time}</div>
                                            </div>
                                        ):
                                        <div>
                                            {activity.time != '' ?    
                                                (
                                                <div style={{color:'black',backgroundColor:'#FA8072'}}>{activity.time}</div>
                                                ):
                                                (
                                                <div><p></p></div>
                                                )
                                            } 
                                        </div>
                                        }
                                    </>
                                    ): 
                                    (<div></div>)
                                }
                            </div>
                        ))} 
                    </span>
                </div>
            </td>
        ))
    )
}
