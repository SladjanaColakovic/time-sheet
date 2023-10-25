import { getRequestWithParams, putRequest } from "../../requests/httpClient";
import ListItemInput from "../input/ListItemInput";
import ListItemSelect from "../select/ListItemSelect";
import { format } from "date-fns";
import SvgButton from "../buttons/SvgButton";

const EditTimeSheetItem = ({item, setItems, items, setTotalHours, selectedDate, clients, projects, categories, showErrorMessage}) => {

    const ITEMS_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_ITEMS_URL
    const teamMemberId = JSON.parse(window.atob(localStorage.getItem('token').split('.')[1])).id;

    
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
            .then(() => {
                const params = {
                    teamMemberId: teamMemberId,
                    date: format(new Date(selectedDate), 'yyyy-MM-dd')
                }
                getRequestWithParams(ITEMS_URL + "/teamMember", params)
                    .then((res) => {
                        setItems(res.data.items);
                        setTotalHours(res.data.totalHours);
                    })
                    .catch((error) => {
                        showErrorMessage(error.message);
                    })
            })
            .catch((error) => {
                showErrorMessage(error.message);
            })
    }
    
    return (
        <tr>
            <td>
                <ListItemSelect value={item.project.client.id} changeValue={changeClient} itemId={item.id} items={clients} />
            </td>
            <td>
                <ListItemSelect value={item.project.id} changeValue={changeProject} itemId={item.id} items={projects} />
            </td>
            <td>
                <ListItemSelect value={item.category.id} changeValue={changeCategory} itemId={item.id} items={categories} />
            </td>
            <td>
                <ListItemInput value={item.description} changeItem={changeItem} property={"description"} itemId={item.id} />
            </td>
            <td>
                <ListItemInput value={item.time} changeItem={changeItem} property={"time"} itemId={item.id} />
            </td>
            <td>
                <ListItemInput value={item.overtime} changeItem={changeItem} property={"overtime"} itemId={item.id} />
            </td>
            <td>
                <SvgButton handleClick={() => {edit(item.id)}} className="add-btn" icon={"edit"}/>
            </td>
        </tr>
    );
}

export default EditTimeSheetItem;