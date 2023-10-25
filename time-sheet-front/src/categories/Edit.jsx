import { useState } from "react";
import { getRequest, deleteRequest, putRequest } from "../requests/httpClient";
import Button from "../components/buttons/Button";
import InputEdit from "../components/input/InputEdit";
import { NotificationContainer, NotificationManager } from "react-notifications";

const Edit = ({ category, setData }) => {

    const [editCategory, setEditCategory] = useState(category);
    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL


    const handleSave = () => {

        const data = {
            id: editCategory.id,
            name: editCategory.name
        }
        putRequest(URL, data)
            .then(() => {
                getRequest(URL)
                    .then((res) => {
                        setData(res.data.categories);
                    })
                    .catch((error) => {
                        NotificationManager.error(error.message, '', 5000);
                    })

            })
            .catch(error => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    const handleDelete = () => {
        const params = { id: editCategory.id }
        deleteRequest(URL, params)
            .then(() => {
                getRequest(URL)
                    .then((res) => {
                        setData(res.data.categories);
                    })
                    .catch((error) => {
                        NotificationManager.error(error.message, '', 5000);
                    })
            })
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    const changeCategory = (e, property) => {
        setEditCategory({ ...editCategory, [property]: e.target.value });
    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-5">
                    <InputEdit value={editCategory.name} changeValue={changeCategory} property={"name"} labelName={"Name"} />
                </div>
            </div>
            <div className="row">
                <div className="col-2">
                    <Button handleClick={handleSave} className="edit-save" buttonName={"Save"} />
                </div>
                <div className="col-2">
                    <Button handleClick={handleDelete} className="edit-delete" buttonName={"Delete"} />
                </div>
            </div>
            <NotificationContainer />
        </div>
    );
}

export default Edit;