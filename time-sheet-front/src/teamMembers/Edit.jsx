import { useState } from "react";
import { getRequest, putRequest, deleteRequest } from "../requests/httpClient";
import InputEdit from "../components/input/InputEdit";
import Button from "../components/buttons/Button";
import * as Constants from '../constants/TeamMemberConstants'
import { errorNotification, successNotification } from "../shared/notification";

const Edit = ({ teamMember, setData }) => {

    const [editTeamMember, setEditTeamMember] = useState(teamMember);

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
        putRequest(Constants.URL, data)
            .then(() => {
                getRequest(Constants.URL)
                    .then((res) => {
                        setData(res.data.teamMembers)
                        successNotification("Successfully edited")
                    })
                    .catch((error) => {
                        errorNotification(error.message);
                    })

            })
            .catch(error => {
                errorNotification(error.message);
            })


    }

    const handleDelete = () => {
        const params = { id: editTeamMember.id }
        deleteRequest(Constants.URL, params)
            .then(() => {
                getRequest(Constants.URL)
                    .then((res) => {
                        setData(res.data.teamMembers);
                        successNotification("Successfully deleted");
                    })
                    .catch((error) => {
                        errorNotification(error.message);
                    })
            }
            )
            .catch((error) => {
                errorNotification(error.message);
            })
    }

    const changeTeamMember = (e, property) => {
        setEditTeamMember({ ...editTeamMember, [property]: e.target.value })
    }


    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEdit labelName={"Name"} changeValue={changeTeamMember} property={"name"} value={editTeamMember.name} />
                </div>
                <div className="col-4">
                    <InputEdit labelName={"Username"} changeValue={changeTeamMember} property={"username"} value={editTeamMember.username} />
                </div>
                <div className="col-4">
                    <InputEdit labelName={"Email"} changeValue={changeTeamMember} property={"email"} value={editTeamMember.email} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <InputEdit labelName={"Hours per week"} changeValue={changeTeamMember} property={"hoursPerWeek"} value={editTeamMember.hoursPerWeek} />
                </div>
                <div className="col-4">
                    <label className="edit-label">Status</label>
                    <input type="radio" value={"ACTIVE"} checked={editTeamMember.status === "ACTIVE"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, status: e.target.value }) }} />
                    <span className="span-radio"></span>
                    <label>Active</label>
                    <span className="span-radios"></span>
                    <input type="radio" value={"INACTIVE"} checked={editTeamMember.status === "INACTIVE"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, status: e.target.value }) }} />
                    <span className="span-radio"></span>
                    <label>Inactive</label>
                </div>
                <div className="col-4">
                    <label className="edit-label">Role</label>
                    <input type="radio" value={"WORKER"} checked={editTeamMember.role === "WORKER"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, role: e.target.value }) }} />
                    <span className="span-radio"></span>
                    <label>Worker</label>
                    <span className="span-radios"></span>
                    <input type="radio" value={"ADMIN"} checked={editTeamMember.role === "ADMIN"} onChange={(e) => { setEditTeamMember({ ...editTeamMember, role: e.target.value }) }} />
                    <span className="span-radio"></span>
                    <label>Admin</label>
                </div>
            </div>
            <div className="row">
                <div className="col-2">
                    <Button handleClick={handleSave} className="edit-save" buttonName={"Save"} />
                </div>
                <div className="col-2">
                    <Button handleClick={handleDelete} className="edit-delete" buttonName={"Delete"} />
                </div>
            </div>
        </div>
    );
}

export default Edit;