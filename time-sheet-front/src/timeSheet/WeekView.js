import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRequest, getRequestWithParams, postRequest, putRequest } from "../requests/httpClient";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import ButtonComponent from "../components/ButtonComponent";
import { NotificationContainer, NotificationManager } from "react-notifications";
import CalendarNavigationComponent from "../components/CalendarNavigationComponent";
import DailyCalendarComponent from "../components/DailyCalendarComponent";
import ItemsTable from "../components/ItemsTable";


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
    const [client, setClient] = useState('');
    const [project, setProject] = useState('');
    const [category, setCategory] = useState('');
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
                                        console.log(res.data.items)
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
                        setCategory('')
                        setClient('')
                        setProject('')

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

    const changeItem = (e, property, id) => {
        const newState = items.map(obj => {
            if (obj.id === id) {
                return { ...obj, [property]: e.target.value };
            }
            return obj;
        });

        setItems(newState);
    }

    const changeClient = (e, id) => {
        const newState = items.map(obj => {
            if (obj.id === id) {
                return { ...obj, project: { ...obj.project, client: { ...obj.project.client, id: e.target.value } } };
            }
            return obj;
        });

        setItems(newState);
    }

    const changeProject = (e, id) => {
        const newState = items.map(obj => {
            if (obj.id === id) {
                return { ...obj, project: { ...obj.project, id: e.target.value } };
            }
            return obj;
        });

        setItems(newState);
    }

    const changeCategory = (e, id) => {
        const newState = items.map(obj => {
            if (obj.id === id) {
                return { ...obj, category: { ...obj.category, id: e.target.value } };
            }
            return obj;
        });

        setItems(newState);
    }

    const edit = (id) => {
        let editing;
        items.map(obj => {
            if (obj.id === id) {
                editing = obj;
            }
        })
        console.log(editing);
        const data = {
            id: id,
            description: editing.description,
            time: editing.time,
            overtime: editing.overtime,
            project: {
                id: editing.project.id
            },
            category: {
                id: editing.category.id
            }
        }
        putRequest(ITEMS_URL, data)
            .then((res) => {
                console.log(res);
                const params = {
                    teamMemberId: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id,
                    date: format(new Date(selectedDate), 'yyyy-MM-dd')
                }
                getRequestWithParams(ITEMS_URL + "/teamMember", params)
                    .then((res) => {
                        setItems(res.data.items);
                        console.log(res.data.items)
                        setTotalHours(res.data.totalHours)
                    })

            })
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);

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
                            <DailyCalendarComponent dates={dates} formatSelectedDate={formatSelectedDate} selectDate={selectDate} />
                            <br />
                            <br />
                            <ItemsTable items={items} categories={categories} clients={clients} projects={projects} client={client} project={project} category={category} description={description} time={time} overtime={overtime} setClient={setClient} setProject={setProject} setCategory={setCategory} setDescription={setDescription} setTime={setTime} setOvertime={setOvertime} changeCategory={changeCategory} changeItem={changeItem} changeClient={changeClient} changeProject={changeProject} edit={edit} addItem={addItem}/>
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