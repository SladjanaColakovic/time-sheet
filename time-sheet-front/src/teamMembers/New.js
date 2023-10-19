import { useState } from "react";
import { postRequest } from "../requests/httpClient";

const New = () => {

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [hoursPerWeek, setHoursPerWeek] = useState('');
    const [status, setStatus] = useState("ACTIVE");
    const [role, setRole] = useState("WORKER");


    const handleSave = () => {
        let data = {
            name: name,
            username: username,
            email: email,
            hoursPerWeek: hoursPerWeek,
            status: status,
            role: role,
            password: "Lozinka123"
        }
        console.log()
        console.log(status)
        console.log(role)

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
        <div className='span-top'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <label>Name</label>
                    <input type="text" placeholder="Enter name..." value={name} onChange={(e) => { setName(e.target.value) }} />
                    <label>Username</label>
                    <input type="text" placeholder="Enter username..." value={username} onChange={(e) => { setUsername(e.target.value) }} />
                    <label>Email</label>
                    <input type="text" placeholder="Enter email..." value={email} onChange={(e) => { setEmail(e.target.value) }} />
                    <label>Hours per week</label>
                    <input type="text" placeholder="Enter hours per week..." value={hoursPerWeek} onChange={(e) => { setHoursPerWeek(e.target.value) }} />
                    <div className="row">
                        <div className="col-6">
                            <label style={{ display: "block" }}>Status</label>
                            <input type="radio" value={status} onChange={(e) => { setStatus(e.target.value) }} checked/>
                            <span style={{ marginRight: "10px" }}></span>
                            <label>Active</label>
                            <span style={{ marginRight: "15px" }}></span>
                            <input type="radio" value={status} onChange={(e) => { setStatus(e.target.value) }} />
                            <span style={{ marginRight: "10px" }}></span>
                            <label>Inactive</label>
                        </div>
                        <div className="col-6">
                            <label style={{ display: "block" }}>Role</label>
                            <input type="radio" value={role} onChange={(e) => { setRole(e.target.value) }} checked/>
                            <span style={{ marginRight: "10px" }}></span>
                            <label>Worker</label>
                            <span style={{ marginRight: "15px" }}></span>
                            <input type="radio" value={role} onChange={(e) => { setRole(e.target.value) }} />
                            <span style={{ marginRight: "10px" }}></span>
                            <label>Admin</label>
                        </div>
                    </div>
                    <br />
                    <div className="row">
                        <div className="col-4"></div>
                        <div className="col-4">
                            <button onClick={handleSave} className="save">Save</button>
                        </div>
                        <div className="col-4"></div>
                    </div>
                </div>
                <div className="col-4"></div>
            </div>
        </div>
    );
}

export default New;