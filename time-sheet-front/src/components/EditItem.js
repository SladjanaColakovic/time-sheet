import { getRequestWithParams, putRequest } from "../requests/httpClient";
import InputListItemComponent from "./InputListItemComponent";
import SelectListItemComponent from "./SelectListItemComponent";
import { format } from "date-fns";

const Edititem = ({item, setItems, items, setTotalHours, selectedDate, clients, projects, categories, showErrorMessage}) => {

    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL
    
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
                const params = {
                    teamMemberId: JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id,
                    date: format(new Date(selectedDate), 'yyyy-MM-dd')
                }
                getRequestWithParams(ITEMS_URL + "/teamMember", params)
                    .then((res) => {
                        setItems(res.data.items);
                        setTotalHours(res.data.totalHours);
                    })
            })
            .catch((error) => {
                showErrorMessage(error.message);
            })
    }
    
    return (
        <tr>
            <td>
                <SelectListItemComponent value={item.project.client.id} changeValue={changeClient} itemId={item.id} items={clients} />
            </td>
            <td>
                <SelectListItemComponent value={item.project.id} changeValue={changeProject} itemId={item.id} items={projects} />
            </td>
            <td>
                <SelectListItemComponent value={item.category.id} changeValue={changeCategory} itemId={item.id} items={categories} />
            </td>
            <td>
                <InputListItemComponent value={item.description} changeItem={changeItem} property={"description"} itemId={item.id} />
            </td>
            <td>
                <InputListItemComponent value={item.time} changeItem={changeItem} property={"time"} itemId={item.id} />
            </td>
            <td>
                <InputListItemComponent value={item.overtime} changeItem={changeItem} property={"overtime"} itemId={item.id} />
            </td>
            <td>
                <button onClick={() => { edit(item.id) }} className="add-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" className="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                        <path d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                    </svg>
                </button>
            </td>
        </tr>
    );
}

export default Edititem;