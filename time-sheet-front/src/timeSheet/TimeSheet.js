import { useEffect, useState } from "react";
import { format } from 'date-fns';
import { getRequestWithParams } from "../requests/httpClient";


const TimeSheet = () => {

    const [date, setDate] = useState(new Date());
    const [formatDate, setFormatDate] = useState(format(new Date(), 'MMMM, yyyy'));
    const [data, setData] = useState();
    const [totalHours, setTotalHours] = useState();

    const MONDAY = 1;
    const SUNDAY = 7;

    useEffect(() => {
        //let formatCurrentDate = format(date, 'MMMM, yyyy');
        //setFormatDate(formatCurrentDate);
        const [from, to, id] = getDateRange();
        const params = {
            from: format(from, 'yyyy-MM-dd'),
            to: format(to, 'yyyy-MM-dd'),
            teamMemberId: id
        }
        console.log(params)

        getRequestWithParams("http://localhost:8080/api/timeSheet", params)
            .then((res) => {
                console.log(res);
                setData(res.data.dailyTimeSheets);
                setTotalHours(res.data.totalHours)
            })

    }, [])


    const getDateRange = () => {
        var userInfo = JSON.parse(window.atob(localStorage.getItem('token').split('.')[1]))
        const firstDateOfMonth = new Date(date.getFullYear(), date.getMonth(), 1)
        const lastDateOfMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        const firstDayOfMonth = firstDateOfMonth.getDay();
        const lastDayOfMonth = lastDateOfMonth.getDay();
        let from = firstDateOfMonth;
        if (firstDayOfMonth != MONDAY) {
            from = new Date(firstDateOfMonth.setDate((firstDayOfMonth != 0) ? firstDateOfMonth.getDate() - firstDayOfMonth + 1 : firstDateOfMonth.getDate() - 7 + 1));
        }
        let to = lastDateOfMonth;
        if (lastDayOfMonth != SUNDAY) {
            to = new Date(lastDateOfMonth.setDate(lastDateOfMonth.getDate() + SUNDAY - lastDateOfMonth.getDay()))
        }
        return [from, to, userInfo.id]
    }


    const handleNext = () => {
        setDate(new Date(date.setMonth(date.getMonth() + 1)));
        setFormatDate(format(date, 'MMMM, yyyy'));
        const [from, to, id] = getDateRange();
        const params = {
            from: format(from, 'yyyy-MM-dd'),
            to: format(to, 'yyyy-MM-dd'),
            teamMemberId: id
        }
        getRequestWithParams("http://localhost:8080/api/timeSheet", params)
            .then((res) => {
                console.log(res)
            })
    }

    const handleBack = () => {
        setDate(new Date(date.setMonth(date.getMonth() - 1)));
        setFormatDate(format(date, 'MMMM, yyyy'));
        const [from, to, id] = getDateRange();
        const params = {
            from: format(from, 'yyyy-MM-dd'),
            to: format(to, 'yyyy-MM-dd'),
            teamMemberId: id
        }
        getRequestWithParams("http://localhost:8080/api/timeSheet", params)
            .then((res) => {
                console.log(res)
            })
    }

    return (
        <div className="main">
            <h1>TimeSheet</h1>
            <div className="row">
                <div className="col-1"></div>
                <div className="col-10">
                    <div className="box">
                        <div style={{ textAlign: "center" }}>
                            <button className="back" onClick={handleBack}>
                                <svg style={{ fill: "#567189" }} xmlns="http://www.w3.org/2000/svg" width="30" height="30" className="bi bi-arrow-left" viewBox="0 0 16 16">
                                    <path d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z" />
                                </svg>
                            </button>
                            {formatDate && <label>{formatDate}</label>}
                            <button className="next" onClick={handleNext}>
                                <svg style={{ fill: "#567189" }} xmlns="http://www.w3.org/2000/svg" width="30" height="30" className="bi bi-arrow-right" viewBox="0 0 16 16">
                                    <path d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z" />
                                </svg>
                            </button>
                        </div>
                        <br />
                        <br />
                        <div className="row">
                            <div className="col-md">Monday</div>
                            <div className="col-md">Tuesday</div>
                            <div className="col-md">Wendesday</div>
                            <div className="col-md">Thurstday</div>
                            <div className="col-md">Friday</div>
                            <div className="col-md">Saturday</div>
                            <div className="col-md">Sunday</div>
                        </div>
                        {data &&
                            <div className="row">
                                {data.map((item) => (
                                    <label key={item.date} style={{ width: "14.28%" }}>
                                        <div style={{ margin: "5px" }} className="box-date">
                                            {new Date(item.date).getDate()}
                                            <br />
                                            <br />
                                            <div>Hours: {item.totalHoursPerDay}</div>
                                        </div>
                                    </label>
                                ))}
                            </div>
                        }
                    </div>
                </div>
                <div className="col-1"></div>
            </div>
            <br />
        </div>
    );
}

export default TimeSheet;