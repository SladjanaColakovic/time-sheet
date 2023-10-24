const MonthlyCalendarComponent = ({data, showDetails}) => {
    return (
        <div>
            {data &&
                <div className="row">
                    {data.map((item) => (
                        <label key={item.date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                            <div style={{ margin: "2px" }} className="box-date">
                                <label className="date-label">{new Date(item.date).getDate()}.</label>
                                <br />
                                <div style={{ backgroundColor: item.flag === 'FULFILLED' ? '#c5d2d4' : item.flag === 'UNFULFILLED' ? '#ffcccb' : 'white' }}>
                                    <a onClick={() => { showDetails(item.date) }} className="hours">Hours: {item.totalHoursPerDay}</a>
                                </div>
                            </div>
                        </label>
                    ))}
                </div>
            }
        </div>
    );
}

export default MonthlyCalendarComponent;