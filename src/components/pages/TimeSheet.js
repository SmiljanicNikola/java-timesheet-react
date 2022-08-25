import React from 'react'
import { Calendar2 } from '../calendar-feature/Calendar2';
import { Header } from '../layout/Header';
import { Footer } from '../layout/Footer';

export const TimeSheet = () => {

    return (
        <div>
			<Header></Header><br></br><br></br>
			{/*<CalendarComponent />*/}
			<Calendar2/>

			<Footer></Footer>
        </div>
    )
}
