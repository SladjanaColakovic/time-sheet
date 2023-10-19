import { useState } from "react";
import { getRequest, putRequest, deleteRequest } from "../requests/httpClient";

const Edit = ({ teamMember, setData }) => {

    const [editTeamMember, setEditTeamMember] = useState(teamMember);
    const url = "http://localhost:8080/api/teamMember";


    const handleSave = () => {
        let data = {
            id: editTeamMember.id,
            name: editTeamMember.name,
            username: editTeamMember.username,
            email: editTeamMember.email,
            hoursPerWeek: editTeamMember.hoursPerWeek,
            status: editTeamMember.status,
            role: editTeamMember.role
        }
        putRequest(url, data)
            .then(res => {
                console.log(res.data);
                getRequest(url).then((res) => {
                    setData(res.data.teamMembers)
                })

            })
            .catch(error => {
                console.log(error)
            })


    }

    const handleDelete = () => {
        const params = { id: editTeamMember.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.teamMembers)
                })
            }
            )
            .catch((error) => {
                console.log(error)
            })
    }


    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <label>Name</label>
                    <input type="text" value={editTeamMember.name} onChange={(e) => { setEditTeamMember({ ...editTeamMember, name: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Username</label>
                    <input type="text" value={editTeamMember.username} onChange={(e) => { setEditTeamMember({ ...editTeamMember, username: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Email</label>
                    <input type="text" value={editTeamMember.email} onChange={(e) => { setEditTeamMember({ ...editTeamMember, email: e.target.value }) }} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <label>Hours per week</label>
                    <input type="text" value={editTeamMember.hoursPerWeek} onChange={(e) => { setEditTeamMember({ ...editTeamMember, hoursPerWeek: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label style={{ display: "block" }}>Status</label>
                    <input type="radio" value={"ACTIVE"} checked={editTeamMember.status === "ACTIVE"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, status: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Active</label>
                    <span style={{ marginRight: "15px" }}></span>
                    <input type="radio" value={"INACTIVE"} checked={editTeamMember.status === "INACTIVE"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, status: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Inactive</label>
                </div>
                <div className="col-4">
                    <label style={{ display: "block" }}>Role</label>
                    <input type="radio" value={"WORKER"} checked={editTeamMember.role === "WORKER"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, role: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Worker</label>
                    <span style={{ marginRight: "15px" }}></span>
                    <input type="radio" value={"ADMIN"} checked={editTeamMember.role === "ADMIN"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, role: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Admin</label>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-2">
                    <button onClick={handleSave} className="save">Save</button>
                </div>
                <div className="col-2">
                    <button onClick={handleDelete} className="delete">Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Edit;