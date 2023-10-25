import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";
import Button from "../components/buttons/Button";
import InputEdit from "../components/input/InputEdit";
import SelectEdit from "../components/select/SelectEdit";
import { NotificationContainer, NotificationManager } from "react-notifications";
import * as Constants from "../constants/ProjectConstants";

const Edit = ({ clients, teamMembers, project, setData }) => {

    const [editProject, setEditProject] = useState(project);

    const handleSave = () => {

        const data = {
            id: editProject.id,
            name: editProject.name,
            description: editProject.description,
            status: editProject.status,
            client: {
                id: editProject.client.id
            },
            lead: {
                id: editProject.lead.id
            }
        }
        putRequest(Constants.PROJECT_URL, data)
            .then(() => {
                getRequest(Constants.PROJECT_URL)
                    .then((res) => {
                        setData(res.data.projects);
                    })
                    .catch((error) => {
                        NotificationManager.error(error.message, '', 5000);

                    })

            })
            .catch(error => {
                NotificationManager.error(error.message, '', 5000);
            })

    }

    const handleDelete = () => {
        const params = { id: editProject.id }
        deleteRequest(Constants.PROJECT_URL, params)
            .then(() => {
                getRequest(Constants.PROJECT_URL)
                    .then((res) => {
                        setData(res.data.projects);
                    })
                    .catch((error) => {
                        NotificationManager.error(error.message, '', 5000);

                    })
            })
            .catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    const changeProject = (e, property) => {
        setEditProject({ ...editProject, [property]: e.target.value })
    }

    const changeProjectLaed = (e) => {
        setEditProject({ ...editProject, lead: { ...editProject.lead, id: e.target.value } })
    }

    const changeProjectClient = (e) => {
        setEditProject({ ...editProject, client: { ...editProject.client, id: e.target.value } })
    }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEdit labelName={"Name"} changeValue={changeProject} property={"name"} value={editProject.name} />
                </div>
                <div className="col-4">
                    <InputEdit labelName={"Description"} changeValue={changeProject} property={"description"} value={editProject.description} />
                </div>
                <div className="col-4">
                    <SelectEdit selectedValue={editProject.client.id} items={clients} labelName={"Client"} setValue={changeProjectClient} />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <SelectEdit selectedValue={editProject.lead.id} items={teamMembers} labelName={"Lead"} setValue={changeProjectLaed} />
                </div>
                <div className="col-4">
                    <label style={{ display: "block" }}>Status</label>
                    <input type="radio" value={"ACTIVE"} checked={editProject.status === "ACTIVE"} onChange={(e) => { setEditProject({ ...editProject, status: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Active</label>
                    <span style={{ marginRight: "15px" }}></span>
                    <input type="radio" value={"INACTIVE"} checked={editProject.status === "INACTIVE"} onChange={(e) => { setEditProject({ ...editProject, status: e.target.value }) }} />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Inactive</label>
                </div>
                <div className="col-4">
                    <input name="archive" type="radio" />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Archive</label>
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
            <NotificationContainer />
        </div>
    );
}

export default Edit;