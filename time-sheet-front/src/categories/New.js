import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputComponent from "../components/InputComponent";
import ButtonComponent from "../components/ButtonComponent";

const New = () => {

    const url = "http://localhost:8080/api/category";
    const [name, setName] = useState('');

    const handleSave = () => {

        const data = {
            name: name
        }
        postRequest(url, data)
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
                    <InputComponent value={name} setValue={setName} labelName={"Name"} placeholder={"Enter name..."}></InputComponent>
                    <ButtonComponent handleClick={handleSave} className='new-save' buttonName={"Save"}></ButtonComponent>
                </div>
                <div className='col-4'></div>
            </div>
        </div>
    );
}

export default New;