import { useState } from "react";
import { getRequest, deleteRequest, putRequest } from "../requests/httpClient";
import ButtonComponent from "../components/ButtonComponent";
import InputEditComponent from "../components/InputEditComponent";

const Edit = ({ category, setData }) => {

    const [editCategory, setEditCategory] = useState(category);
    const url = "http://localhost:8080/api/category";


    const handleSave = () => {

        const data = {
            id: editCategory.id,
            name: editCategory.name
        }
        putRequest(url, data)
            .then(res => {
                getRequest(url).then((res) => {
                    setData(res.data.categories);
                })
            })
            .catch(error => {
                console.log(error);
            })
    }

    const handleDelete = () => {
        const params = { id: editCategory.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.categories);
                })
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const changeCategory = (e, property) => {
        setEditCategory({ ...editCategory, [property]: e.target.value });
    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-5">
                    <InputEditComponent value={editCategory.name} changeValue={changeCategory} property={"name"} labelName={"Name"}></InputEditComponent>
                </div>
            </div>
            <div className="row">
                <div className="col-2">
                    <ButtonComponent handleClick={handleSave} className="edit-save" buttonName={"Save"}></ButtonComponent>
                </div>
                <div className="col-2">
                    <ButtonComponent handleClick={handleDelete} className="edit-delete" buttonName={"Delete"}></ButtonComponent>
                </div>
            </div>
        </div>
    );
}

export default Edit;