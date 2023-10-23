import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputComponent from "../components/InputComponent";
import ButtonComponent from "../components/ButtonComponent";
import SelectComponent from "../components/SelectComponent";

const New = ({ clients, teamMembers }) => {


    const [client, setClient] = useState(1);
    const [lead, setLead] = useState(1);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_PROJECT_URL

    const handleSave = () => {

        const data = {
            name: name,
            description: description,
            client: {
                id: client
            },
            lead: {
                id: lead
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
                    <InputComponent labelName={"Name"} placeholder={"Enter name..."} value={name} setValue={setName} />
                    <InputComponent labelName={"Description"} placeholder={"Enter description..."} value={description} setValue={setDescription} />
                    <SelectComponent labelName={"Client"} items={clients} setValue={setClient} />
                    <SelectComponent labelName={"Team member"} items={teamMembers} setValue={setLead} />
                    <ButtonComponent handleClick={handleSave} className='new-save' buttonName={"Save"} />
                </div>
                <div className="col-4"></div>
            </div>
        </div>
    );
}

export default New;