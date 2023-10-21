import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";
import InputEditComponent from "../components/InputEditComponent";
import SelectEditComponent from "../components/SelectEditComponent";
import ButtonComponent from "../components/ButtonComponent";

const Edit = ({ countries, client, setData }) => {

    const [editClient, setEditClient] = useState(client);

    const url = "http://localhost:8080/api/client";


    const handleSave = () => {

        const data = {
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

    const changeClient = (e, property) => {
        setEditClient({ ...editClient, [property]: e.target.value })
    }

    const changeClientCountry = (e) => {
        setEditClient({ ...editClient, country: { ...editClient.country, id: e.target.value } })
    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Name"} changeValue={changeClient} value={editClient.name} property={"name"}></InputEditComponent>
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Address"} changeValue={changeClient} value={editClient.address} property={"address"}></InputEditComponent>
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"City"} changeValue={changeClient} value={editClient.city} property={"city"}></InputEditComponent>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Postal code"} changeValue={changeClient} value={editClient.postalCode} property={"postalCode"}></InputEditComponent>
                </div>
                <div className="col-4">
                    <SelectEditComponent labelName={"Country"} items={countries} selectedValue={editClient.country.id} setValue={changeClientCountry}></SelectEditComponent>
                </div>
                <div className="col-4"></div>
            </div>
            <br />
            <div className="row">
                <div className="col-2">
                    <ButtonComponent handleClick={handleSave} buttonName={"Save"} className="edit-save"></ButtonComponent>
                </div>
                <div className="col-2">
                    <ButtonComponent handleClick={handleDelete} buttonName={"Delete"} className="edit-delete"></ButtonComponent>
                </div>
            </div>
        </div>
    );
}

export default Edit;