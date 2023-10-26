const MonthlyCalendar = ({data, showDetails}) => {
    return (
        <div>
            {data &&
                <div className="row">
                    {data.map((item) => (
                        <label key={item.date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                            <div className="box-date">
                                <label className="date-label">{new Date(item.date).getDate()}.</label>
                                <br />
                                <div className={item.flag === 'FULFILLED' ? 'fulfilled' : item.flag === 'UNFULFILLED' ? 'unfulfilled' : 'not-filled' }>
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

export default MonthlyCalendar;