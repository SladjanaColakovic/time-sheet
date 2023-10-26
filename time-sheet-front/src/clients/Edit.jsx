import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";
import InputEdit from "../components/input/InputEdit";
import SelectEdit from "../components/select/SelectEdit";
import Button from "../components/buttons/Button";
import * as Constants from "../constants/ClientConstants";
import { errorNotification, successNotification } from "../shared/notification";

const Edit = ({ countries, client, setData }) => {

    const [editClient, setEditClient] = useState(client);

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
        putRequest(Constants.CLIENT_URL, data)
            .then(() => {
                getRequest(Constants.CLIENT_URL)
                    .then((res) => {
                        setData(res.data.clients)
                        successNotification("Successfully edited");
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

        const params = { id: editClient.id }
        deleteRequest(Constants.CLIENT_URL, params)
            .then(() => {
                getRequest(Constants.CLIENT_URL)
                    .then((res) => {
                        setData(res.data.clients);
                        successNotification("Successfully deleted")
                    })
                    .catch((error) => {
                        errorNotification(error.message);
                    })
            }
            )
            .catch((error) => {
                errorNotification(error.message);
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
                    <InputEdit labelName={"Name"} changeValue={changeClient} value={editClient.name} property={"name"} />
                </div>
                <div className="col-4">
                    <InputEdit labelName={"Address"} changeValue={changeClient} value={editClient.address} property={"address"} />
                </div>
                <div className="col-4">
                    <InputEdit labelName={"City"} changeValue={changeClient} value={editClient.city} property={"city"} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEdit labelName={"Postal code"} changeValue={changeClient} value={editClient.postalCode} property={"postalCode"} />
                </div>
                <div className="col-4">
                    <SelectEdit labelName={"Country"} items={countries} selectedValue={editClient.country.id} setValue={changeClientCountry} />
                </div>
                <div className="col-4"></div>
            </div>
            <br />
            <div className="row">
                <div className="col-2">
                    <Button handleClick={handleSave} buttonName={"Save"} className="edit-save" />
                </div>
                <div className="col-2">
                    <Button handleClick={handleDelete} buttonName={"Delete"} className="edit-delete" />
                </div>
            </div>
        </div>
    );
}

export default Edit;