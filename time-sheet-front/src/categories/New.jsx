import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputText from "../components/input/InputText";
import Button from "../components/buttons/Button";
import * as Constants from '../constants/CategoryConstants'
import { notification } from "../shared/notification";

const New = () => {

    const [name, setName] = useState('');
    
    const handleSave = () => {

        const data = {
            name: name
        }
        postRequest(Constants.URL, data)
            .then(() => {
                window.location.reload();
            })
            .catch((error) => {
                notification(error.message);
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
        </div>
    );
}

export default New;