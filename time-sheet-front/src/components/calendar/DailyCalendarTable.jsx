import DailyCalendarTableHeader from "./DailyCalenadarTableHeader";
import NewTimeSheetItem from "./NewTimeSheetItem";
import { useEffect, useState } from "react";
import { getRequest } from "../../requests/httpClient";
import { NotificationContainer, NotificationManager } from "react-notifications";
import EdititemComponent from "../EditItemComponent";

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
                showErrorMessage(error.message);
            })

        getRequest(PROJECT_URL)
            .then((res) => {
                setProjects(res.data.projects);
            })
            .catch((error) => {
                showErrorMessage(error.message);
            })

        getRequest(CATEGORY_URL)
            .then((res) => {
                setCategories(res.data.categories);
            })
            .catch((error) => {
                showErrorMessage(error.message);
            })
    }, [])


    const showErrorMessage = (message) => {
        NotificationManager.error(message, '', 5000);
    }


    return (
        <div className="table-box">
            {items && <table>
                <thead>
                    <DailyCalendarTableHeader />
                </thead>
                <tbody>
                    {items.map((item) => (
                        <EdititemComponent item={item} key={item.id} categories={categories} clients={clients} projects={projects} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} items={items} showErrorMessage={showErrorMessage} />
                    ))}
                    <NewTimeSheetItem clients={clients} projects={projects} categories={categories} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} showErrorMessage={showErrorMessage} />
                </tbody>
            </table>}
            <NotificationContainer />
        </div>
    );
}

export default DailyCalendarTable;