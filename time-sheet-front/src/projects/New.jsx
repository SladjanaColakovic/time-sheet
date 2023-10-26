import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputText from "../components/input/InputText";
import Button from "../components/buttons/Button";
import Select from "../components/select/Select";
import * as Constants from "../constants/ProjectConstants";
import { errorNotification } from "../shared/notification";

const New = ({ clients, teamMembers }) => {

    const [client, setClient] = useState();
    const [lead, setLead] = useState();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

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

        postRequest(Constants.PROJECT_URL, data)
            .then(() => {
                window.location.reload();
            })
            .catch((error) => {
                errorNotification(error.message);
            })
    }

    return (
        <div className='new-item'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <InputText labelName={"Name"} placeholder={"Enter name..."} value={name} setValue={setName} />
                    <InputText labelName={"Description"} placeholder={"Enter description..."} value={description} setValue={setDescription} />
                    <Select labelName={"Client"} items={clients} setValue={setClient} />
                    <Select labelName={"Team member"} items={teamMembers} setValue={setLead} />
                    <Button handleClick={handleSave} className='new-save' buttonName={"Save"} />
                </div>
                <div className="col-4"></div>
            </div>
        </div>
    );
}

export default New;