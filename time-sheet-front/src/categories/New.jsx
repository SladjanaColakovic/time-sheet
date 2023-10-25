import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputText from "../components/input/InputText";
import Button from "../components/buttons/Button";
import { NotificationContainer, NotificationManager } from "react-notifications";

const New = () => {

    const [name, setName] = useState('');
    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL
    
    const handleSave = () => {

        const data = {
            name: name
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
            <div className='row'>
                <div className='col-4'></div>
                <div className='col-4'>
                    <InputText value={name} setValue={setName} labelName={"Name"} placeholder={"Enter name..."} />
                    <Button handleClick={handleSave} className='new-save' buttonName={"Save"} />
                </div>
                <div className='col-4'></div>
            </div>
            <NotificationContainer />
        </div>
    );
}

export default New;