import { useState } from "react";
import { getRequest, putRequest, deleteRequest } from "../requests/httpClient";
import InputEditComponent from "../components/InputEditComponent";
import ButtonComponent from "../components/ButtonComponent";
import { NotificationContainer, NotificationManager } from "react-notifications";


const Edit = ({ teamMember, setData }) => {

    const [editTeamMember, setEditTeamMember] = useState(teamMember);
    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_TEAM_MEMBER_URL


    const handleSave = () => {

        const data = {
            id: editTeamMember.id,
            name: editTeamMember.name,
            username: editTeamMember.username,
            email: editTeamMember.email,
            hoursPerWeek: editTeamMember.hoursPerWeek,
            status: editTeamMember.status,
            role: editTeamMember.role
        }
        putRequest(URL, data)
            .then(res => {
                getRequest(URL).then((res) => {
                    setData(res.data.teamMembers)
                })

            })
            .catch(error => {
                NotificationManager.error(error.message, '', 5000);
            })


    }

    const handleDelete = () => {
        const params = { id: editTeamMember.id }
        deleteRequest(URL, params)
            .then((res) => {
                getRequest(URL).then((res) => {
                    setData(res.data.teamMembers)
                })
            }
            )
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    const changeTeamMember = (e, property) => {
        setEditTeamMember({ ...editTeamMember, [property]: e.target.value })
    }


    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Name"} changeValue={changeTeamMember} property={"name"} value={editTeamMember.name} />
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Username"} changeValue={changeTeamMember} property={"username"} value={editTeamMember.username} />
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Email"} changeValue={changeTeamMember} property={"email"} value={editTeamMember.email} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Hours per week"} changeValue={changeTeamMember} property={"hoursPerWeek"} value={editTeamMember.hoursPerWeek} />
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
            <div className="row">
                <div className="col-2">
                    <ButtonComponent handleClick={handleSave} className="edit-save" buttonName={"Save"} />
                </div>
                <div className="col-2">
                    <ButtonComponent handleClick={handleDelete} className="edit-delete" buttonName={"Delete"} />
                </div>
            </div>
            <NotificationContainer/>
        </div>
    );
}

export default Edit;