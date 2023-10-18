import { useState } from "react";
import axiosInstance from "../axiosIntance";
import { useLocation } from "react-router-dom";

const Edit = ({ category }) => {

    const [editCategory, setEditCategory] = useState(category);
    const url = "http://localhost:8080/api/category";
    const location = useLocation();


    const handleSave = () => {
        let data = {
            id: editCategory.id,
            name: editCategory.name
        }
        axiosInstance.put(url, data)
            .then((res) => {
                console.log(res);
                location.reload(true)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const handleDelete = () => { 
        const params = {id: editCategory.id}
        axiosInstance.delete(url, {params})
        .then(
            location.reload(true)
        )
        .catch((error) => {
            console.log(error)
        })

    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-5">
                    <input type="text" value={editCategory.name} onChange={(e) => { setEditCategory({ ...editCategory, name: e.target.value }) }} />
                </div>
                <div className="col-2">
                    <button onClick={handleSave} className="save">Save</button>
                </div>
                <div className="col-2">
                    <button onClick={handleDelete} className="delete">Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Edit;