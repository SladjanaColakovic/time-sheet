import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";

const Edit = ({ countries, client, setData }) => {

    const [editClient, setEditClient] = useState(client);

    const url = "http://localhost:8080/api/client";


    const handleSave = () => {
        console.log(editClient.country.id)
        let data = {
            id: editClient.id,
            name: editClient.name,
            address: editClient.address,
            city: editClient.city,
            postalCode: editClient.postalCode,
            country: {
                id: editClient.country.id
            }
        }
        putRequest(url, data)
            .then(res => {
                getRequest(url).then((res) => {
                    setData(res.data.clients)
                })

            })
            .catch(error => {
                console.log(error)
            })

    }

    const handleDelete = () => {
        const params = { id: editClient.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.clients)
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
                    <label>Name</label>
                    <input type="text" value={editClient.name} onChange={(e) => { setEditClient({ ...editClient, name: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Address</label>
                    <input type="text" value={editClient.address} onChange={(e) => { setEditClient({ ...editClient, address: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>City</label>
                    <input type="text" value={editClient.city} onChange={(e) => { setEditClient({ ...editClient, city: e.target.value }) }} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <label>Postal code</label>
                    <input type="text" value={editClient.postalCode} onChange={(e) => { setEditClient({ ...editClient, postalCode: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Country</label>
                    <select value={editClient.country.id} onChange={(e) => { setEditClient({ ...editClient, country: { ...editClient.country, id: e.target.value } }) }}>
                        {countries && countries.map((country) => (
                            <option key={country.id} value={country.id}>{country.name}</option>
                        ))}
                    </select>
                </div>
                <div className="col-4"></div>
            </div>
            <br />
            <div className="row">
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