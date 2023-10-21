import { useState } from "react";
import { getRequest, putRequest, deleteRequest } from "../requests/httpClient";
import InputEditComponent from "../components/InputEditComponent";
import ButtonComponent from "../components/ButtonComponent";

const Edit = ({ teamMember, setData }) => {

    const [editTeamMember, setEditTeamMember] = useState(teamMember);
    const url = "http://localhost:8080/api/teamMember";


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
        putRequest(url, data)
            .then(res => {
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

    const changeTeamMember = (e, property) => {
        setEditTeamMember({ ...editTeamMember, [property]: e.target.value })
    }


    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Name"} changeValue={changeTeamMember} property={"name"} value={editTeamMember.name}></InputEditComponent>
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Username"} changeValue={changeTeamMember} property={"username"} value={editTeamMember.username}></InputEditComponent>
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Email"} changeValue={changeTeamMember} property={"email"} value={editTeamMember.email}></InputEditComponent>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Hours per week"} changeValue={changeTeamMember} property={"hoursPerWeek"} value={editTeamMember.hoursPerWeek}></InputEditComponent>
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
                    <ButtonComponent handleClick={handleSave} className="edit-save" buttonName={"Save"}></ButtonComponent>
                </div>
                <div className="col-2">
                    <ButtonComponent handleClick={handleDelete} className="edit-delete" buttonName={"Delete"}></ButtonComponent>
                </div>
            </div>
        </div>
    );
}

export default Edit;