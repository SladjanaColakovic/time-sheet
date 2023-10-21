import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const WeekView = () => {


    const { startDate, endDate, selectedDate } = useParams();
    const [dates, setDates] = useState();

    useEffect(() => {
        const firstDayOfWeek = new Date(startDate)
        const lastDayOfWeek = new Date(endDate)
        const date = new Date(firstDayOfWeek.getTime());

        const range = [];

        while (date <= lastDayOfWeek) {
            range.push(new Date(date).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
            date.setDate(date.getDate() + 1);
        }

        setDates(range);


    }, [])

    return (
        <div className="main">
            <h1>Weekly view</h1>
            <div className="row">
                <div className="col-1"></div>
                <div className="col-10">
                    <div className="box">
                        <div style={{ textAlign: "center" }}>
                            <button className="back">
                                <svg style={{ fill: "#567189" }} xmlns="http://www.w3.org/2000/svg" width="30" height="30" className="bi bi-arrow-left" viewBox="0 0 16 16">
                                    <path d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z" />
                                </svg>
                            </button>
                            {startDate && <label>{startDate} - {endDate}</label>}
                            <button className="next">
                                <svg style={{ fill: "#567189" }} xmlns="http://www.w3.org/2000/svg" width="30" height="30" className="bi bi-arrow-right" viewBox="0 0 16 16">
                                    <path d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z" />
                                </svg>
                            </button>
                        </div>
                        <br />
                        <br />
                        {dates && dates.map((date) => (
                            <label key={date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                                <div style={{ backgroundColor: date === selectedDate ? '#c5d2d4' : 'white', margin: "2px" }} className="box-date">
                                    <label className="date-label">{date}</label>
                                    <br />
                                </div>
                            </label>
                        ))}
                    </div>
                </div>
                <div className="col-1"></div>
            </div>
            <br />
        </div>
    );
}

export default WeekView;