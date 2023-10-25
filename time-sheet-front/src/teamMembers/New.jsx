import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputText from "../components/input/InputText";
import Button from "../components/buttons/Button";
import RadioButtons from "../components/buttons/RadioButtons";
import { NotificationContainer, NotificationManager } from "react-notifications";


const New = () => {

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [hoursPerWeek, setHoursPerWeek] = useState('');
    const [status, setStatus] = useState("ACTIVE");
    const [role, setRole] = useState("WORKER");

    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_TEAM_MEMBER_URL

    const handleSave = () => {

        const data = {
            name: name,
            username: username,
            email: email,
            hoursPerWeek: hoursPerWeek,
            status: status,
            role: role,
            password: "Lozinka123"
        }

        postRequest(URL, data)
            .then(() => {
                window.location.reload()
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
                    <InputText labelName={"Username"} placeholder={"Enter username..."} value={username} setValue={setUsername} />
                    <InputText labelName={"Email"} placeholder={"Enter email..."} value={email} setValue={setEmail} />
                    <InputText labelName={"Hours per week"} placeholder={"Enter hours per week..."} value={hoursPerWeek} setValue={setHoursPerWeek} />
                    <div className="row">
                        <div className="col-6">
                            <RadioButtons value={status} radioName={"new-member-status"} radioLabelName={"Status"} radioValues={["ACTIVE", "INACTIVE"]} setValue={setStatus} radioLabelValues={["Active", "Inactive"]} />
                        </div>
                        <div className="col-6">
                            <RadioButtons value={role} radioName={"new-member-role"} radioLabelName={"Role"} radioValues={["WORKER", "ADMIN"]} setValue={setRole} radioLabelValues={["Worker", "Admin"]} />
                        </div>
                    </div>
                    <Button handleClick={handleSave} className='new-save' buttonName={"Save"} />
                </div>
                <div className="col-4"></div>
            </div>
            <NotificationContainer/>
        </div>
    );
}

export default New;