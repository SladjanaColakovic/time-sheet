const DaysComponent = () => {
    const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

    return (
        <div className="row">
            {days.map((day) => (
                <div className="col-md">{day}</div>
            ))}
        </div>
        
    );
}

export default DaysComponent;