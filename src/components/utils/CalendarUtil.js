function getDatesFromStartPointToEndPoint(startDate, lastCalendarDate){
        
    const dates =[];

    for(let i = startDate; i<= lastCalendarDate; i.setDate(i.getDate() + 1)){
        dates.push(new Date(i));
    }
    return dates;

}

function findFirstDayOfTheWeek(date){

    let dayOfTheWeek = date.getDay() -1;

    let firstDayOfTheWeek = date.getDate() + 1 - dayOfTheWeek;
 
    return new Date(date.setDate(firstDayOfTheWeek))

}

function findLastDayOfTheWeek(date){

    let dayOfTheWeek = date.getDay()

    let lastDayOfTheWeek = date.getDate() + 7 - dayOfTheWeek;
 
    return new Date(date.setDate(lastDayOfTheWeek))

}

export default{
    getDatesFromStartPointToEndPoint,
    findFirstDayOfTheWeek,
    findLastDayOfTheWeek
};