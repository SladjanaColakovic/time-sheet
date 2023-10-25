import { useEffect, useState } from "react";
import { format } from 'date-fns';
import { getRequestWithParams } from "../requests/httpClient";
import { useNavigate } from "react-router-dom";
import CalendarNavigation from "../components/calendar/CalendarNavigation";
import Days from "../components/calendar/Days";
import MonthlyCalendar from "../components/calendar/MonthlyCalendar";
import * as Constants from '../constants/TimeSheetConstants'
import { useSelector } from "react-redux";
import { selectUser } from "../auth/userSlice";
import { notification } from "../shared/notification";

const TimeSheet = () => {

    const [date, setDate] = useState(new Date());
    const [formatDate, setFormatDate] = useState(format(new Date(), Constants.SHOWING_DATE_FORMAT_MONTH));
    const [data, setData] = useState();
    const [totalHours, setTotalHours] = useState();
    const navigate = useNavigate();
    const user = useSelector(selectUser)

    useEffect(() => {
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);

    }, [])

    const getCalendar = (from, to, id) => {
        const params = {
            from: format(from, Constants.DATE_FORMAT),
            to: format(to, Constants.DATE_FORMAT),
            teamMemberId: id
        }
        getRequestWithParams(Constants.TIME_SHEET_URL, params)
            .then((res) => {
                setData(res.data.dailyTimeSheets);
                setTotalHours(res.data.totalHours)
            })
            .catch((error) => {
                notification(error.message);
            })
    }

    const getDateRange = () => {
        var id = user.id
        const firstDateOfMonth = new Date(date.getFullYear(), date.getMonth(), 1)
        const lastDateOfMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        const firstDayOfMonth = firstDateOfMonth.getDay();
        const lastDayOfMonth = lastDateOfMonth.getDay();
        let from = firstDateOfMonth;
        if (firstDayOfMonth !== Constants.MONDAY) {
            from = new Date(firstDateOfMonth.setDate((firstDayOfMonth !== 0) ? firstDateOfMonth.getDate() - firstDayOfMonth + 1 : firstDateOfMonth.getDate() - 7 + 1));
        }
        let to = lastDateOfMonth;
        if (lastDayOfMonth !== Constants.SUNDAY) {
            to = new Date(lastDateOfMonth.setDate(lastDateOfMonth.getDate() + 7 - lastDateOfMonth.getDay()))
        }
        return [from, to, id]
    }

    const handleNext = () => {
        setDate(new Date(date.setMonth(date.getMonth() + 1)));
        setFormatDate(format(date, Constants.SHOWING_DATE_FORMAT_MONTH));
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);
    }

    const handleBack = () => {
        setDate(new Date(date.setMonth(date.getMonth() - 1)));
        setFormatDate(format(date, Constants.SHOWING_DATE_FORMAT_MONTH));
        const [from, to, id] = getDateRange();
        getCalendar(from, to, id);
    }

    const showDetails = (selected) => {
        let selectedDate = new Date(selected);
        const dateSelect = new Date(selectedDate.getTime());
        const firstDayOfWeek = selectedDate.getDate() - selectedDate.getDay() + 1;
        const weekStartDate = new Date(selectedDate.setDate(firstDayOfWeek));
        const weekEndDate = new Date(selectedDate.setDate(firstDayOfWeek + 6));
        const formatStartDate = format(weekStartDate, Constants.SHOWING_DATE_FORMAT_WEEK)
        const formatEndDate = format(weekEndDate, Constants.SHOWING_DATE_FORMAT_WEEK)
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
                            <CalendarNavigation back={handleBack} next={handleNext} content={formatDate} />
                            <br />
                            <br />
                            <Days />
                            <MonthlyCalendar data={data} showDetails={showDetails} />
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