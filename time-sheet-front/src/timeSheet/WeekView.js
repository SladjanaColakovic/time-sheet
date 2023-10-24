import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRequest, getRequestWithParams, postRequest } from "../requests/httpClient";
import SelectComponent from "../components/SelectComponent";
import InputComponent from '../components/InputComponent'
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import ButtonComponent from "../components/ButtonComponent";
import { NotificationContainer, NotificationManager } from "react-notifications";
import CalendarNavigationComponent from "../components/CalendarNavigationComponent";


const WeekView = () => {


    const { startDateFormat, endDateFormat, date } = useParams();
    const [selectedDate, setSelectedDate] = useState(date)
    const [startDate, setStartDate] = useState(startDateFormat)
    const [endDate, setEndDate] = useState(endDateFormat)
    const [dates, setDates] = useState();
    const [formatSelectedDate, setFormatSelectedDate] = useState(null);
    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();
    const [client, setClient] = useState(1);
    const [project, setProject] = useState(1);
    const [category, setCategory] = useState(1);
    const [description, setDescription] = useState('');
    const [time, setTime] = useState('');
    const [overtime, setOvertime] = useState('');
    const navigate = useNavigate();
    const [items, setItems] = useState();
    const [totalHours, setTotalHours] = useState();

    const CLIENT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL
    const PROJECT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_PROJECT_URL
    const CATEGORY_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL
    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL


    useEffect(() => {
        setFormatSelectedDate(new Date(selectedDate).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }))
        const firstDayOfWeek = new Date(startDate)
        const lastDayOfWeek = new Date(endDate)

        findDateRange(firstDayOfWeek, lastDayOfWeek);

        getRequest(CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients);
                getRequest(PROJECT_URL)
                    .then((res) => {
                        setProjects(res.data.projects);
                        getRequest(CATEGORY_URL)
                            .then((res) => {
                                setCategories(res.data.categories);
                                const params = {
                                    teamMemberId: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id,
                                    date: format(new Date(selectedDate), 'yyyy-MM-dd')
                                }
                                getRequestWithParams(ITEMS_URL + "/teamMember", params)
                                    .then((res) => {
                                        setItems(res.data.items);
                                        setTotalHours(res.data.totalHours)
                                    })
                            })
                    })
            })

    }, [])

    const findDateRange = (start, end) => {
        const date = new Date(start.getTime());
        const range = [];

        while (date <= end) {
            range.push(new Date(date).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
            date.setDate(date.getDate() + 1);
        }

        setDates(range);
    }

    const showMonthlyView = () => {
        navigate('/timeSheet', { replace: true });
    }

    const addItem = () => {
        const data = {
            date: format(new Date(selectedDate), 'yyyy-MM-dd'),
            description: description,
            time: time,
            overtime: overtime,
            project: {
                id: project
            },
            teamMember: {
                id: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id
            },
            category: {
                id: category
            }
        }

        postRequest(ITEMS_URL, data)
            .then((res) => {
                console.log(res.data);
                const params = {
                    teamMemberId: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id,
                    date: format(new Date(selectedDate), 'yyyy-MM-dd')
                }
                getRequestWithParams(ITEMS_URL + "/teamMember", params)
                    .then((res) => {
                        setItems(res.data.items);
                        setTotalHours(res.data.totalHours)
                        setDescription('')
                        setTime('')
                        setOvertime('')
                    })

            }).catch((error) => {
                NotificationManager.error(error.message, '', 5000);

            })
    }

    const next = () => {
        let firstDateOfNextWeek = new Date(new Date(endDate));
        firstDateOfNextWeek.setDate(new Date(endDate).getDate() + 1);
        let lastDateOfNextWeek = new Date(endDate);
        lastDateOfNextWeek.setDate(new Date(endDate).getDate() + 7);

        findDateRange(firstDateOfNextWeek, lastDateOfNextWeek);

        setStartDate(format(firstDateOfNextWeek, 'MMMM dd, yyyy'));
        setEndDate(format(lastDateOfNextWeek, 'MMMM dd, yyyy'));
    }

    const back = () => {
        let lastDateOfPreviousWeek = new Date(new Date(startDate));
        lastDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 1);
        let firstDateOfPreviousWeek = new Date(startDate);
        firstDateOfPreviousWeek.setDate(new Date(startDate).getDate() - 7);

        findDateRange(firstDateOfPreviousWeek, lastDateOfPreviousWeek);

        setStartDate(format(firstDateOfPreviousWeek, 'MMMM dd, yyyy'));
        setEndDate(format(lastDateOfPreviousWeek, 'MMMM dd, yyyy'));
    }

    const flagSelectedDate = (date) => {
        const iterator = new Date(new Date(startDate).getTime());
        while (iterator <= new Date(endDate)) {
            if (new Date(date).getDate() === iterator.getDate()) {
                setSelectedDate(iterator);
                setFormatSelectedDate(new Date(iterator).toLocaleDateString('en-us', { weekday: "long", day: "numeric", month: "short" }));
                break;
            }
            iterator.setDate(iterator.getDate() + 1);
        }
        return iterator;
    }

    const selectDate = (date) => {
        const iterator = flagSelectedDate(date)
        const params = {
            teamMemberId: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id,
            date: format(new Date(iterator), 'yyyy-MM-dd')
        }
        getRequestWithParams(ITEMS_URL + "/teamMember", params)
            .then((res) => {
                setItems(res.data.items);
                setTotalHours(res.data.totalHours);
            })
    }

    return (
        <div className="main">
            <div className="weeklyView">
                <h1>Weekly view</h1>
                <div className="row">
                    <div className="col-1"></div>
                    <div className="col-10">
                        <div className="box">
                            <CalendarNavigationComponent back={back} next={next} content={startDate + ' - ' + endDate} />
                            <br />
                            <br />
                            {dates && formatSelectedDate && dates.map((date) => (
                                <label className="label-btn" onClick={() => { selectDate(date) }} key={date} style={{ width: "14.28%", paddingRight: "0", paddingLeft: "0" }}>
                                    <div style={{ backgroundColor: date === formatSelectedDate ? '#c5d2d4' : 'white', margin: "2px" }} className="box-date">
                                        <label className="date-label">{date}</label>
                                        <br />
                                    </div>
                                </label>
                            ))}
                            <br />
                            <br />
                            <div className="table-box">
                                {items && <table>
                                    <thead>
                                        <tr>
                                            <th>Client</th>
                                            <th>Project</th>
                                            <th>Category</th>
                                            <th>Description</th>
                                            <th className="custom-width">Time</th>
                                            <th className="custom-width">Overtime</th>
                                            <th className="custom-width">&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {items.map((item) => (
                                            <tr key={item.id}>
                                                <td>{item.project.client.name}</td>
                                                <td>{item.project.name}</td>
                                                <td>{item.category.name}</td>
                                                <td>{item.description}</td>
                                                <td className="custom-width">{item.time}</td>
                                                <td className="custom-width">{item.overtime}</td>
                                                <td className="custom-width">&nbsp;</td>
                                            </tr>
                                        ))}

                                        <tr className="tr-margin">
                                            <td>
                                                <SelectComponent items={clients} setValue={setClient} />
                                            </td>
                                            <td>
                                                <SelectComponent items={projects} setValue={setProject} />
                                            </td>
                                            <td>
                                                <SelectComponent items={categories} setValue={setCategory} />
                                            </td>
                                            <td>
                                                <InputComponent value={description} setValue={setDescription} />
                                            </td>
                                            <td>
                                                <InputComponent value={time} setValue={setTime} />
                                            </td>
                                            <td>
                                                <InputComponent value={overtime} setValue={setOvertime} />
                                            </td>
                                            <td>
                                                <button onClick={addItem} className="add-btn">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" className="bi bi-plus-square-fill" viewBox="0 0 16 16">
                                                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z" />
                                                    </svg>
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>}
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-3">
                                    <ButtonComponent handleClick={showMonthlyView} className="monthly-view" buttonName={"back to monthly view"} />
                                </div>
                                <div className="col-9">
                                    <label className="total-hours">Total hours: {totalHours}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-1"></div>
                </div>
                <br />
                <NotificationContainer />
            </div>
        </div>
    );
}

export default WeekView;