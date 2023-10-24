import InputComponent from "./InputComponent";
import SelectComponent from "./SelectComponent";
import { getRequestWithParams, postRequest } from "../requests/httpClient";
import { useState } from "react";
import { format } from "date-fns";

const AddItemComponent = ({ clients, projects, categories, setItems, setTotalHours, selectedDate, showErrorMessage }) => {

    const [client, setClient] = useState('');
    const [project, setProject] = useState('');
    const [category, setCategory] = useState('');
    const [description, setDescription] = useState('');
    const [time, setTime] = useState('');
    const [overtime, setOvertime] = useState('');

    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL

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
                showErrorMessage(error.message);
            })
    }

    return (
        <tr className="tr-margin">
            <td>
                <SelectComponent items={clients} setValue={setClient} value={client} />
            </td>
            <td>
                <SelectComponent items={projects} setValue={setProject} value={project} />
            </td>
            <td>
                <SelectComponent items={categories} setValue={setCategory} value={category} />
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
    );
}

export default AddItemComponent;