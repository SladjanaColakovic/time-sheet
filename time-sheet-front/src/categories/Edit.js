import { useState } from "react";
import putRequest from "../putRequest";
import getRequest from "../getRequest";
import deleteRequest from "../deleteRequest";

const Edit = ({ category, setData }) => {

    const [editCategory, setEditCategory] = useState(category);
    const url = "http://localhost:8080/api/category";


    const handleSave = () => {
        let data = {
            id: editCategory.id,
            name: editCategory.name
        }
        putRequest(url, data).then(res => {
            console.log(res.data);
            getRequest(url).then((res) => {
                setData(res.data.categories)
            })
        })
            .catch(error => {
                console.log(error)
            })

    }

    const handleDelete = () => {
        const params = { id: editCategory.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.categories)
                })
            }
            )
            .catch((error) => {
                console.log(error)
            })

    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
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