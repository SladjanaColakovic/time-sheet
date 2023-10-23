import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputComponent from "../components/InputComponent";
import ButtonComponent from "../components/ButtonComponent";

const New = () => {

    const [name, setName] = useState('');
    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL
    const handleSave = () => {

        const data = {
            name: name
        }
        postRequest(URL, data)
            .then((res) => {
                window.location.reload();
            })
            .catch((error) => {
                console.log(error);
            })
    }

    return (
        <div className='new-item'>
            <div className='row'>
                <div className='col-4'></div>
                <div className='col-4'>
                    <InputComponent value={name} setValue={setName} labelName={"Name"} placeholder={"Enter name..."} />
                    <ButtonComponent handleClick={handleSave} className='new-save' buttonName={"Save"} />
                </div>
                <div className='col-4'></div>
            </div>
        </div>
    );
}

export default New;