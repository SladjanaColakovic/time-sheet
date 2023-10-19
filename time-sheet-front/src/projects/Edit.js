import { useState } from "react";
import { deleteRequest, getRequest, putRequest } from "../requests/httpClient";

const Edit = ({ clients, teamMembers, project, setData }) => {

    const [editProject, setEditProject] = useState(project);
    const [client, setClient] = useState(project.client.id);
    const [lead, setLead] = useState(project.lead.id);
    const url = "http://localhost:8080/api/project";


    const handleSave = () => {
        let data = {
            id: editProject.id,
            name: editProject.name,
            description: editProject.description,
            status: editProject.status,
            client: {
                id: client
            },
            lead: {
                id: lead
            }
        }
        putRequest(url, data)
            .then(res => {
                console.log(res.data);
                getRequest(url).then((res) => {
                    setData(res.data.projects)
                })

            })
            .catch(error => {
                console.log(error)
            })

    }

    const handleDelete = () => {
        const params = { id: editProject.id }
        deleteRequest(url, params)
            .then((res) => {
                getRequest(url).then((res) => {
                    setData(res.data.projects)
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
                    <input type="text" value={editProject.name} onChange={(e) => { setEditProject({ ...editProject, name: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Description</label>
                    <input type="text" value={editProject.description} onChange={(e) => { setEditProject({ ...editProject, description: e.target.value }) }} />
                </div>
                <div className="col-4">
                    <label>Client</label>
                    <select onChange={(e) => { setClient(e.target.value) }}>
                        {clients && clients.map((client) => (
                            <option key={client.id} value={client.id}>{client.name}</option>
                        ))}
                    </select>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-4">
                    <label>Team member</label>
                    <select onChange={(e) => { setLead(e.target.value)}}>
                        {teamMembers && teamMembers.map((teamMember) => (
                            <option key={teamMember.id} value={teamMember.id}>{teamMember.name}</option>
                        ))}
                    </select>
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
                    <input type="radio" />
                    <span style={{ marginRight: "10px" }}></span>
                    <label>Archive</label>
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