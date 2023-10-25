import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRequestWithParams } from "../requests/httpClient";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import ButtonComponent from "../components/ButtonComponent";
import CalendarNavigation from "../components/calendar/CalendarNavigation";
import WeeklyCalendarComponent from "../components/WeeklyCalendarComponent";
import DailyCalendarTable from "../components/calendar/DailyCalendarTable";


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

    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL
    const teamMemberId = JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id;


    useEffect(() => {
        setFormatSelectedDate(new Date(selectedDate).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const firstDayOfWeek = new Date(startDate)
        const lastDayOfWeek = new Date(endDate)
        findDateRange(firstDayOfWeek, lastDayOfWeek);
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(selectedDate), 'yyyy-MM-dd')
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

    const next = () => {
        let firstDateOfNextWeek = new Date(new Date(endDate));
        firstDateOfNextWeek.setDate(new Date(endDate).getDate() + 1);
        let lastDateOfNextWeek = new Date(endDate);
        lastDateOfNextWeek.setDate(new Date(endDate).getDate() + 7);
        findDateRange(firstDateOfNextWeek, lastDateOfNextWeek);
        setStartDate(format(firstDateOfNextWeek, 'MMMM dd, yyyy'));
        setEndDate(format(lastDateOfNextWeek, 'MMMM dd, yyyy'));
        setSelectedDate(firstDateOfNextWeek);
        setFormatSelectedDate(new Date(firstDateOfNextWeek).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(firstDateOfNextWeek), 'yyyy-MM-dd')
        }
        getItems(params);
    }

    const back = () => {
        let lastDateOfPreviousWeek = new Date(new Date(startDate));
        lastDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 1);
        let firstDateOfPreviousWeek = new Date(startDate);
        firstDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 7);
        findDateRange(firstDateOfPreviousWeek, lastDateOfPreviousWeek);
        setStartDate(format(firstDateOfPreviousWeek, 'MMMM dd, yyyy'));
        setEndDate(format(lastDateOfPreviousWeek, 'MMMM dd, yyyy'));
        setSelectedDate(firstDateOfPreviousWeek);
        setFormatSelectedDate(new Date(firstDateOfPreviousWeek).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const params = {
            teamMemberId: teamMemberId,
            date: format(new Date(firstDateOfPreviousWeek), 'yyyy-MM-dd')
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
            date: format(new Date(showingDate), 'yyyy-MM-dd')
        }
        getItems(params);
    }

    const getItems = (params) => {
        getRequestWithParams(ITEMS_URL + "/teamMember", params)
            .then((res) => {
                setItems(res.data.items);
                setTotalHours(res.data.totalHours);
            })
            .catch((error) => {
                console.log(error.message)
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
                            <CalendarNavigation back={back} next={next} content={startDate + ' - ' + endDate} />
                            <br />
                            <br />
                            <WeeklyCalendarComponent dates={dates} formatSelectedDate={formatSelectedDate} selectDate={selectDate} />
                            <br />
                            <br />
                            <DailyCalendarTable items={items} selectedDate={selectedDate} setItems={setItems} setTotalHours={setTotalHours} />
                            <br />
                            <div className="row">
                                <div className="col-3">
                                    <ButtonComponent handleClick={showMonthlyView} className="monthly-view" buttonName={"back to monthly view"} />
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