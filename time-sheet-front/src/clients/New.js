import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputComponent from "../components/InputComponent";
import SelectComponent from "../components/SelectComponent";
import ButtonComponent from "../components/ButtonComponent";

const New = ({ countries }) => {

    const [country, setCountry] = useState(1);
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
            .then((res) => {
                window.location.reload();
            })
            .catch((error) => {
                console.log(error.message);
            })
    }

    return (
        <div className='new-item'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <InputComponent labelName={"Name"} placeholder={"Enter name..."} value={name} setValue={setName}></InputComponent>
                    <InputComponent labelName={"Address"} placeholder={"Enter address..."} value={address} setValue={setAddress}></InputComponent>
                    <InputComponent labelName={"City"} placeholder={"Enter city..."} value={city} setValue={setCity}></InputComponent>
                    <InputComponent labelName={"Postal code"} placeholder={"Enter postal code..."} value={postalCode} setValue={setPostalCode}></InputComponent>
                    <SelectComponent labelName={"Country"} items={countries} setValue={setCountry}></SelectComponent>
                    <ButtonComponent handleClick={handleSave} buttonName={"Save"} className="new-save"></ButtonComponent>
                </div>
                <div className="col-4"></div>
            </div>
        </div>
    );
}

export default New;