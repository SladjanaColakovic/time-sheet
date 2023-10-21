import { useState } from "react";
import { postRequest } from "../requests/httpClient";
import InputComponent from "../components/InputComponent";
import ButtonComponent from "../components/ButtonComponent";
import RadioComponent from "../components/RadioComponent";

const New = () => {

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [hoursPerWeek, setHoursPerWeek] = useState('');
    const [status, setStatus] = useState("ACTIVE");
    const [role, setRole] = useState("WORKER");


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

        postRequest("http://localhost:8080/api/teamMember", data)
            .then((res) => {
                console.log(res.data)
                window.location.reload()
            })
            .catch((error) => {
                console.log(error.message)
            })
    }


    return (
        <div className='new-item'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <InputComponent labelName={"Name"} placeholder={"Enter name..."} value={name} setValue={setName}></InputComponent>
                    <InputComponent labelName={"Username"} placeholder={"Enter username..."} value={username} setValue={setUsername}></InputComponent>
                    <InputComponent labelName={"Email"} placeholder={"Enter email..."} value={email} setValue={setEmail}></InputComponent>
                    <InputComponent labelName={"Hours per week"} placeholder={"Enter hours per week..."} value={hoursPerWeek} setValue={setHoursPerWeek}></InputComponent>
                    <div className="row">
                        <div className="col-6">
                            <RadioComponent value={status} radioName={"new-member-status"} radioLabelName={"Status"} radioValues={["ACTIVE", "INACTIVE"]} setValue={setStatus} radioLabelValues={["Active", "Inactive"]}></RadioComponent>
                        </div>
                        <div className="col-6">
                            <RadioComponent value={role} radioName={"new-member-role"} radioLabelName={"Role"} radioValues={["WORKER", "ADMIN"]} setValue={setRole} radioLabelValues={["Worker", "Admin"]}></RadioComponent>
                        </div>
                    </div>
                    <ButtonComponent handleClick={handleSave} className='new-save' buttonName={"Save"}></ButtonComponent>
                </div>
                <div className="col-4"></div>
            </div>
        </div>
    );
}

export default New;