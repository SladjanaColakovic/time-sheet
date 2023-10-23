import { useState } from "react";
import Search from "./Search";
import Report from "./Report";


const Reports = () => {

    const [data, setData] = useState();

    return (
        <div className="main">
            <h1>Reports</h1>
            <Search setData={setData} />
            <br />
            <br />
            {data && <Report items={data.items} totalHours={data.totalReport} />}
        </div>
    );
}

export default Reports;