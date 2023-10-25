import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";
import InputEditComponent from "../components/InputEditComponent";
import SelectEditComponent from "../components/SelectEditComponent";
import ButtonComponent from "../components/ButtonComponent";
import { NotificationContainer, NotificationManager } from "react-notifications";

const Edit = ({ countries, client, setData }) => {

    const [editClient, setEditClient] = useState(client);

    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL


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
        putRequest(URL, data)
            .then(() => {
                getRequest(URL)
                .then((res) => {
                    setData(res.data.clients)
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

        const params = { id: editClient.id }
        deleteRequest(URL, params)
            .then(() => {
                getRequest(URL)
                .then((res) => {
                    setData(res.data.clients)
                })
                .catch((error) => {
                    NotificationManager.error(error.message, '', 5000);
                })
            }
            )
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);
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
                    <InputEditComponent labelName={"Name"} changeValue={changeClient} value={editClient.name} property={"name"} />
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Address"} changeValue={changeClient} value={editClient.address} property={"address"} />
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"City"} changeValue={changeClient} value={editClient.city} property={"city"} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Postal code"} changeValue={changeClient} value={editClient.postalCode} property={"postalCode"} />
                </div>
                <div className="col-4">
                    <SelectEditComponent labelName={"Country"} items={countries} selectedValue={editClient.country.id} setValue={changeClientCountry} />
                </div>
                <div className="col-4"></div>
            </div>
            <br />
            <div className="row">
                <div className="col-2">
                    <ButtonComponent handleClick={handleSave} buttonName={"Save"} className="edit-save" />
                </div>
                <div className="col-2">
                    <ButtonComponent handleClick={handleDelete} buttonName={"Delete"} className="edit-delete" />
                </div>
            </div>
            <NotificationContainer/>
        </div>
    );
}

export default Edit;