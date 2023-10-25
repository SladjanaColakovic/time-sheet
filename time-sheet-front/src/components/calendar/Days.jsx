const Days = () => {
    const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

    return (
        <div className="row">
            {days.map((day) => (
                <div key={day} className="col-md">{day}</div>
            ))}
        </div>
        
    );
}

export default Days;