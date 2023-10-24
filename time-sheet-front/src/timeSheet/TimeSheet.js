import { useEffect, useState } from "react";
import { format } from 'date-fns';
import { getRequestWithParams } from "../requests/httpClient";
import { useNavigate } from "react-router-dom";
import CalendarNavigationComponent from "../components/CalendarNavigationComponent";
import DaysComponent from "../components/DaysComponent";
import MonthlyCalendarComponent from "../components/MonthlyCalendarComponent";


const TimeSheet = () => {

    const [date, setDate] = useState(new Date());
    const [formatDate, setFormatDate] = useState(format(new Date(), 'MMMM, yyyy'));
    const [data, setData] = useState();
    const [totalHours, setTotalHours] = useState();
    const navigate = useNavigate();

    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_TIME_SHEET_URL


    const MONDAY = 1;
    const SUNDAY = 7;
    const DATE_FORMAT = 'yyyy-MM-dd';
    const SHOWING_DATE_FORMAT = 'MMMM, yyyy';

    useEffect(() => {
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);

    }, [])

    const getCalendar = (from, to, id) => {
        const params = {
            from: format(from, DATE_FORMAT),
            to: format(to, DATE_FORMAT),
            teamMemberId: id
        }

        getRequestWithParams(URL, params)
            .then((res) => {
                setData(res.data.dailyTimeSheets);
                setTotalHours(res.data.totalHours)
            })
    }


    const getDateRange = () => {
        var userInfo = JSON.parse(window.atob(localStorage.getItem('token').split('.')[1]))
        const firstDateOfMonth = new Date(date.getFullYear(), date.getMonth(), 1)
        const lastDateOfMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        const firstDayOfMonth = firstDateOfMonth.getDay();
        const lastDayOfMonth = lastDateOfMonth.getDay();
        let from = firstDateOfMonth;
        if (firstDayOfMonth !== MONDAY) {
            from = new Date(firstDateOfMonth.setDate((firstDayOfMonth !== 0) ? firstDateOfMonth.getDate() - firstDayOfMonth + 1 : firstDateOfMonth.getDate() - 7 + 1));
        }
        let to = lastDateOfMonth;
        if (lastDayOfMonth !== SUNDAY) {
            to = new Date(lastDateOfMonth.setDate(lastDateOfMonth.getDate() + SUNDAY - lastDateOfMonth.getDay()))
        }
        return [from, to, userInfo.id]
    }


    const handleNext = () => {
        setDate(new Date(date.setMonth(date.getMonth() + 1)));
        setFormatDate(format(date, SHOWING_DATE_FORMAT));
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);
    }

    const handleBack = () => {
        setDate(new Date(date.setMonth(date.getMonth() - 1)));
        setFormatDate(format(date, SHOWING_DATE_FORMAT));
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);
    }

    const showDetails = (selected) => {
        let selectedDate = new Date(selected);
        const dateSelect = new Date(selectedDate.getTime());
        const firstDayOfWeek = selectedDate.getDate() - selectedDate.getDay() + 1;
        const weekStartDate = new Date(selectedDate.setDate(firstDayOfWeek));
        const weekEndDate = new Date(selectedDate.setDate(firstDayOfWeek + 6));
        const formatStartDate = format(weekStartDate, 'MMMM dd, yyyy')
        const formatEndDate = format(weekEndDate, 'MMMM dd, yyyy')
        navigate('/weekView/' + formatStartDate + "/" + formatEndDate + "/" + dateSelect)

    }

    return (
        <div className="main">
            <div className="timeSheet">
                <h1>TimeSheet</h1>
                <div className="row">
                    <div className="col-1"></div>
                    <div className="col-10">
                        <div className="box">
                            <CalendarNavigationComponent back={handleBack} next={handleNext} content={formatDate} />
                            <br />
                            <br />
                            <DaysComponent />
                            <MonthlyCalendarComponent data={data} showDetails={showDetails}/>
                            <label className="total-report">Total hours: {totalHours}</label>
                            <br />
                            <br />
                        </div>
                    </div>
                    <div className="col-1"></div>
                </div>
            </div>
            <br />
        </div>
    );
}

export default TimeSheet;