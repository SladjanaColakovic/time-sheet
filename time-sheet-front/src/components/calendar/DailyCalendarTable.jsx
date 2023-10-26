import DailyCalendarTableHeader from "./DailyCalenadarTableHeader";
import NewTimeSheetItem from "./NewTimeSheetItem";
import { useEffect, useState } from "react";
import { getRequest } from "../../requests/httpClient";
import EditTimeSheetItem from "./EditTimeSheetItem";
import { errorNotification } from "../../shared/notification";
import * as Constants from '../../constants/TimeSheetConstants'

const DailyCalendarTable = ({ items, selectedDate, setItems, setTotalHours }) => {

    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();


    useEffect(() => {
        getRequest(Constants.CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients);
            })
            .catch((error) => {
                errorNotification(error.message);
            })

        getRequest(Constants.PROJECT_URL)
            .then((res) => {
                setProjects(res.data.projects);
            })
            .catch((error) => {
                errorNotification(error.message);
            })

        getRequest(Constants.CATEGORY_URL)
            .then((res) => {
                setCategories(res.data.categories);
            })
            .catch((error) => {
                errorNotification(error.message);
            })
    }, [])

    return (
        <div className="table-box">
            {items && <table>
                <thead>
                    <DailyCalendarTableHeader />
                </thead>
                <tbody>
                    {items.map((item) => (
                        <EditTimeSheetItem item={item} key={item.id} categories={categories} clients={clients} projects={projects} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} items={items} />
                    ))}
                    <NewTimeSheetItem clients={clients} projects={projects} categories={categories} setItems={setItems} setTotalHours={setTotalHours} selectedDate={selectedDate} />
                </tbody>
            </table>}
        </div>
    );
}

export default DailyCalendarTable;