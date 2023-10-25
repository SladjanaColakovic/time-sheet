import DailyCalendarTableHeader from "./DailyCalenadarTableHeader";
import NewTimeSheetItem from "./NewTimeSheetItem";
import { useEffect, useState } from "react";
import { getRequest } from "../../requests/httpClient";
import EditTimeSheetItem from "./EditTimeSheetItem";
import { notification } from "../../shared/notification";

const DailyCalendarTable = ({ items, selectedDate, setItems, setTotalHours }) => {

    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();

    const CLIENT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL
    const PROJECT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_PROJECT_URL
    const CATEGORY_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL

    useEffect(() => {
        getRequest(CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients);
            })
            .catch((error) => {
                notification(error.message);
            })

        getRequest(PROJECT_URL)
            .then((res) => {
                setProjects(res.data.projects);
            })
            .catch((error) => {
                notification(error.message);
            })

        getRequest(CATEGORY_URL)
            .then((res) => {
                setCategories(res.data.categories);
            })
            .catch((error) => {
                notification(error.message);
            })
    }, [])


    // const showErrorMessage = (message) => {
    //     NotificationManager.error(message, '', 5000);
    // }


    return (
        <div className="table-box">
            {items && <table>
                <thead>
                    <DailyCalendarTableHeader />
                </thead>
                <tbody>
                    {items.map((item) => (
                        <EditTimeSheetItem item={item} key={item.id} categories={categories} clients={clients} projects={projects} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} items={items} showErrorMessage={notification} />
                    ))}
                    <NewTimeSheetItem clients={clients} projects={projects} categories={categories} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} showErrorMessage={notification} />
                </tbody>
            </table>}
        </div>
    );
}

export default DailyCalendarTable;