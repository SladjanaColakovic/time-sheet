const Report = ({ items, totalHours }) => {
    return (
        <div className="row">
            <div className="col-1"></div>
            <div className="col-10">
                <div className="table-div">
                    {items && <table>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Team member</th>
                                <th>Project</th>
                                <th>Category</th>
                                <th>Description</th>
                                <th>Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            {items.map((item) => (
                                <tr key={item.id}>
                                    <td>{item.date}</td>
                                    <td>{(item.teamMember)? item.teamMember.name: ''}</td>
                                    <td>{(item.project)? item.project.name: ''}</td>
                                    <td>{(item.category)? item.category.name : ''}</td>
                                    <td>{item.description}</td>
                                    <td>{item.time + item.overtime}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>}
                </div>
                <label className="total-report">Total report: {totalHours}</label>
            </div>
            <div className="col-1"></div>
        </div>
    );
}

export default Report;