const WeeklyCalendarComponent = ({dates, formatSelectedDate, selectDate}) => {
    return (
        <div>
            {dates && formatSelectedDate && dates.map((date) => (
                <label className="label-btn" onClick={() => { selectDate(date) }} key={date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                    <div style={{ backgroundColor: date === formatSelectedDate ? '#c5d2d4' : 'white', margin: "2px" }} className="box-date">
                        <label className="date-label">{date}</label>
                        <br />
                    </div>
                </label>
            ))}
        </div>
    );
}

export default WeeklyCalendarComponent;