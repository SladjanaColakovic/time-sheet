import InputText from "../input/InputText";
import Select from "../select/Select";
import { getRequestWithParams, postRequest } from "../../requests/httpClient";
import { useState } from "react";
import { format } from "date-fns";
import SvgButton from "../buttons/SvgButton";

const NewTimeSheetItem = ({ clients, projects, categories, setItems, setTotalHours, selectedDate, showErrorMessage }) => {

    const [client, setClient] = useState('');
    const [project, setProject] = useState('');
    const [category, setCategory] = useState('');
    const [description, setDescription] = useState('');
    const [time, setTime] = useState('');
    const [overtime, setOvertime] = useState('');

    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL
    const teamMemberId = JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id;


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
                id: teamMemberId
            },
            category: {
                id: category
            }
        }

        postRequest(ITEMS_URL, data)
            .then(() => {
                const params = {
                    teamMemberId: teamMemberId,
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
                    .catch((error) => {
                        showErrorMessage(error.message);

                    })

            }).catch((error) => {
                showErrorMessage(error.message);
            })
    }

    return (
        <tr className="tr-margin">
            <td>
                <Select items={clients} setValue={setClient} value={client} />
            </td>
            <td>
                <Select items={projects} setValue={setProject} value={project} />
            </td>
            <td>
                <Select items={categories} setValue={setCategory} value={category} />
            </td>
            <td>
                <InputText value={description} setValue={setDescription} />
            </td>
            <td>
                <InputText value={time} setValue={setTime} />
            </td>
            <td>
                <InputText value={overtime} setValue={setOvertime} />
            </td>
            <td>
                <SvgButton handleClick={addItem} className="add-btn" icon={"add"} />
            </td>
        </tr>
    );
}

export default NewTimeSheetItem;