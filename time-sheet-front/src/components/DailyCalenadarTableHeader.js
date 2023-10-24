const DailyCalendarTableHeader = () => {
    return (
        <tr>
            <th>Client</th>
            <th>Project</th>
            <th>Category</th>
            <th>Description</th>
            <th className="custom-width">Time</th>
            <th className="custom-width">Overtime</th>
            <th className="custom-width">&nbsp;</th>
        </tr>
    );
}

export default DailyCalendarTableHeader;