import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRequestWithParams } from "../requests/httpClient";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import Button from "../components/buttons/Button";
import CalendarNavigation from "../components/calendar/CalendarNavigation";
import WeeklyCalendar from "../components/calendar/WeeklyCalendar";
import DailyCalendarTable from "../components/calendar/DailyCalendarTable";
import * as Constants from '../constants/TimeSheetConstants'
import { useSelector } from "react-redux";
import { selectUser } from "../auth/userSlice";
import { errorNotification } from "../shared/notification";

const WeekView = () => {

    const { startDateFormat, endDateFormat, date } = useParams();
    const [selectedDate, setSelectedDate] = useState(date)
    const [startDate, setStartDate] = useState(startDateFormat)
    const [endDate, setEndDate] = useState(endDateFormat)
    const [dates, setDates] = useState();
    const [formatSelectedDate, setFormatSelectedDate] = useState(null);
    const navigate = useNavigate();
    const [items, setItems] = useState();
    const [totalHours, setTotalHours] = useState();
    const user = useSelector(selectUser)

    const teamMemberId = user.id;


    useEffect(() => {
        setFormatSelectedDate(new Date(selectedDate).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const firstDayOfWeek = new Date(startDate)
        const lastDayOfWeek = new Date(endDate)
        findDateRange(firstDayOfWeek, lastDayOfWeek);
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(selectedDate), Constants.DATE_FORMAT)
        }
        getItems(params);

    }, [])

    const findDateRange = (start, end) => {
        const date = new Date(start.getTime());
        const range = [];

        while (date <= end) {
            range.push(new Date(date).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
            date.setDate(date.getDate() + 1);
        }

        setDates(range);
    }

    const showMonthlyView = () => {
        navigate('/timeSheet', { replace: true });
    }

    const handleNext = () => {
        let firstDateOfNextWeek = new Date(new Date(endDate));
        firstDateOfNextWeek.setDate(new Date(endDate).getDate() + 1);
        let lastDateOfNextWeek = new Date(endDate);
        lastDateOfNextWeek.setDate(new Date(endDate).getDate() + 7);
        findDateRange(firstDateOfNextWeek, lastDateOfNextWeek);
        setStartDate(format(firstDateOfNextWeek, Constants.SHOWING_DATE_FORMAT_WEEK));
        setEndDate(format(lastDateOfNextWeek, Constants.SHOWING_DATE_FORMAT_WEEK));
        setSelectedDate(firstDateOfNextWeek);
        setFormatSelectedDate(new Date(firstDateOfNextWeek).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(firstDateOfNextWeek), Constants.DATE_FORMAT)
        }
        getItems(params);
    }

    const handleBack = () => {
        let lastDateOfPreviousWeek = new Date(new Date(startDate));
        lastDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 1);
        let firstDateOfPreviousWeek = new Date(startDate);
        firstDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 7);
        findDateRange(firstDateOfPreviousWeek, lastDateOfPreviousWeek);
        setStartDate(format(firstDateOfPreviousWeek, Constants.SHOWING_DATE_FORMAT_WEEK));
        setEndDate(format(lastDateOfPreviousWeek, Constants.SHOWING_DATE_FORMAT_WEEK));
        setSelectedDate(firstDateOfPreviousWeek);
        setFormatSelectedDate(new Date(firstDateOfPreviousWeek).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(firstDateOfPreviousWeek), Constants.DATE_FORMAT)
        }
        getItems(params);

    }

    const flagSelectedDate = (date) => {
        const iterator = new Date(new Date(startDate).getTime());
        while (iterator <= new Date(endDate)) {
            if (new Date(date).getDate() === iterator.getDate()) {
                setSelectedDate(iterator);
                setFormatSelectedDate(new Date(iterator).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
                break;
            }
            iterator.setDate(iterator.getDate() + 1);
        }
        return iterator;
    }

    const selectDate = (date) => {
        const showingDate = flagSelectedDate(date);
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(showingDate), Constants.DATE_FORMAT)
        }
        getItems(params);
    }

    const getItems = (params) => {
        getRequestWithParams(Constants.ITEMS_URL + "/teamMember", params)
            .then((res) => {
                setItems(res.data.items);
                setTotalHours(res.data.totalHours);
            })
            .catch((error) => {
                errorNotification(error.message);
            })
    }

    return (
        <div className="main">
            <div className="weeklyView">
                <h1>Weekly view</h1>
                <div className="row">
                    <div className="col-1"></div>
                    <div className="col-10">
                        <div className="box">
                            <CalendarNavigation back={handleBack} next={handleNext} content={startDate + ' - ' + endDate} />
                            <br />
                            <br />
                            <WeeklyCalendar dates={dates} formatSelectedDate={formatSelectedDate} selectDate={selectDate} />
                            <br />
                            <br />
                            <DailyCalendarTable items={items} selectedDate={selectedDate} setItems={setItems} setTotalHours={setTotalHours} />
                            <br />
                            <div className="row">
                                <div className="col-3">
                                    <Button handleClick={showMonthlyView} className="monthly-view" buttonName={"back to monthly view"} />
                                </div>
                                <div className="col-9">
                                    <label className="total-hours">Total hours: {totalHours}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-1"></div>
                </div>
                <br />
            </div>
        </div>
    );
}

export default WeekView;