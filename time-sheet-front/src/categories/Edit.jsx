import { useState } from "react";
import { getRequest, deleteRequest, putRequest } from "../requests/httpClient";
import Button from "../components/buttons/Button";
import InputEdit from "../components/input/InputEdit";
import * as Constants from '../constants/CategoryConstants'
import { errorNotification, successNotification } from "../shared/notification";

const Edit = ({ category, setData }) => {

    const [editCategory, setEditCategory] = useState(category);

    const handleSave = () => {

        const data = {
            id: editCategory.id,
            name: editCategory.name
        }
        putRequest(Constants.URL, data)
            .then(() => {
                getRequest(Constants.URL)
                    .then((res) => {
                        setData(res.data.categories);
                        successNotification("Successfully edited")
                    })
                    .catch((error) => {
                        errorNotification(error.message);
                    })

            })
            .catch(error => {
                errorNotification(error.message);
            })
    }

    const handleDelete = () => {
        const params = { id: editCategory.id }
        deleteRequest(Constants.URL, params)
            .then(() => {
                getRequest(Constants.URL)
                    .then((res) => {
                        setData(res.data.categories);
                        successNotification("Successfully deleted")
                    })
                    .catch((error) => {
                        errorNotification(error.message);
                    })
            })
            .catch((error) => {
                errorNotification(error.message);
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
        </div>
    );
}

export default Edit;