import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputText from "../components/input/InputText";
import Select from "../components/select/Select";
import Button from "../components/buttons/Button";
import { NotificationContainer, NotificationManager } from "react-notifications";

const New = ({ countries }) => {

    const [country, setCountry] = useState();
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');

    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL

    const handleSave = () => {

        const data = {
            name: name,
            address: address,
            city: city,
            postalCode: postalCode,
            country: {
                id: country
            }
        }

        postRequest(URL, data)
            .then(() => {
                window.location.reload();
            })
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    return (
        <div className='new-item'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <InputText labelName={"Name"} placeholder={"Enter name..."} value={name} setValue={setName} />
                    <InputText labelName={"Address"} placeholder={"Enter address..."} value={address} setValue={setAddress} />
                    <InputText labelName={"City"} placeholder={"Enter city..."} value={city} setValue={setCity} />
                    <InputText labelName={"Postal code"} placeholder={"Enter postal code..."} value={postalCode} setValue={setPostalCode} />
                    <Select labelName={"Country"} items={countries} setValue={setCountry} />
                    <Button handleClick={handleSave} buttonName={"Save"} className="new-save" />
                </div>
                <div className="col-4"></div>
            </div>
            <NotificationContainer/>
        </div>
    );
}

export default New;