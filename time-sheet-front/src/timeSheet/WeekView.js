import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRequest } from "../requests/httpClient";
import SelectComponent from "../components/SelectComponent";
import InputComponent from '../components/InputComponent'

const WeekView = () => {


    const { startDate, endDate, selectedDate } = useParams();
    const [dates, setDates] = useState();
    const [formatSelectedDate, setFormatSelectedDate] = useState(null);
    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();
    const [client, setClient] = useState();
    const [project, setProject] = useState();
    const [category, setCategory] = useState();
    const [description, setDescription] = useState();
    const [time, setTime] = useState();
    const [overtime, setOvertime] = useState();

    const CLIENT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL
    const PROJECT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_PROJECT_URL
    const CATEGORY_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL

    useEffect(() => {
        setFormatSelectedDate(new Date(selectedDate).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const firstDayOfWeek = new Date(startDate)
        const lastDayOfWeek = new Date(endDate)
        const date = new Date(firstDayOfWeek.getTime());

        const range = [];

        while (date <= lastDayOfWeek) {
            range.push(new Date(date).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
            date.setDate(date.getDate() + 1);
        }

        setDates(range);

        getRequest(CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients);
                getRequest(PROJECT_URL)
                    .then((res) => {
                        setProjects(res.data.projects);
                        getRequest(CATEGORY_URL)
                            .then((res) => {
                                setCategories(res.data.categories)
                            })
                    })
            })


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
                        {dates && formatSelectedDate && dates.map((date) => (
                            <label key={date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                                <div style={{ backgroundColor: date === formatSelectedDate ? '#c5d2d4' : 'white', margin: "2px" }} className="box-date">
                                    <label className="date-label">{date}</label>
                                    <br />
                                </div>
                            </label>
                        ))}
                        <br />
                        <br />
                        <div className="row">
                            <div className="col-2">Client</div>
                            <div className="col-2">Project</div>
                            <div className="col-2">Category</div>
                            <div className="col-4">Description</div>
                            <div className="col-1">Time</div>
                            <div className="col-1">Overtime</div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-2">
                                <SelectComponent items={clients} setValue={setClient}></SelectComponent>
                            </div>
                            <div className="col-2">
                                <SelectComponent items={projects} setValue={setProject}></SelectComponent>
                            </div>
                            <div className="col-2">
                                <SelectComponent items={categories} setValue={setCategory}></SelectComponent>
                            </div>
                            <div className="col-4">
                                <InputComponent value={description} setValue={setDescription}></InputComponent>
                            </div>
                            <div className="col-1">
                                <InputComponent value={time} setValue={setTime}></InputComponent>
                            </div>
                            <div className="col-1">
                                <InputComponent value={overtime} setValue={setOvertime}></InputComponent>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-1"></div>
            </div>
            <br />
        </div>
    );
}

export default WeekView;