import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";
import ButtonComponent from "../components/ButtonComponent";
import InputEditComponent from "../components/InputEditComponent";
import SelectEditComponent from "../components/SelectEditComponent";

const Edit = ({ clients, teamMembers, project, setData }) => {

    const [editProject, setEditProject] = useState(project);
    const url = "http://localhost:8080/api/project";


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
        putRequest(url, data)
            .then(res => {
                getRequest(url).then((res) => {
                    setData(res.data.projects);
                })

            })
            .catch(error => {
                console.log(error);
            })

    }

    const handleDelete = () => {
        const params = { id: editProject.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.projects);
                })
            }
            )
            .catch((error) => {
                console.log(error);
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

    // const changeProjectStatus = (value) => {
    //     setEditProject({ ...editProject, status: value });
    // }

    return (
        <div className="edit">
            <div className="row">
                <div className="col-4">
                    <InputEditComponent labelName={"Name"} changeValue={changeProject} property={"name"} value={editProject.name}></InputEditComponent>
                </div>
                <div className="col-4">
                    <InputEditComponent labelName={"Description"} changeValue={changeProject} property={"description"} value={editProject.description}></InputEditComponent>
                </div>
                <div className="col-4">
                    <SelectEditComponent selectedValue={editProject.client.id} items={clients} labelName={"Client"} setValue={changeProjectClient}></SelectEditComponent>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <SelectEditComponent selectedValue={editProject.lead.id} items={teamMembers} labelName={"Lead"} setValue={changeProjectLaed}></SelectEditComponent>
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
                    {/* <RadioComponent radioName={"edit-project-status"} value={editProject.status} radioLabelName={"Status"} radioValues={["ACTIVE", "INACTIVE"]} setValue={changeProjectStatus} radioLabelValues={["Active", "Inactive"]}></RadioComponent> */}
                </div>
                <div className="col-4">
                    <input name="archive" type="radio" />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Archive</label>
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