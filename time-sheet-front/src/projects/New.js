import { useState } from "react";
import { postRequest } from "../requests/httpClient";

const New = ({clients, teamMembers}) => {


    const [client, setClient] = useState(1);
    const [lead, setLead] = useState(1);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const handleSave = () => {
        let data = {
            name: name,
            description: description,
            client: {
                id: client
            },
            lead: {
                id: lead
            }
        }

        postRequest("http://localhost:8080/api/project", data)
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
                    <input type="text" placeholder="Enter name..." value={name} onChange={(e) => {setName(e.target.value)}}/>
                    <label>Description</label>
                    <input type="text" placeholder="Enter description..." value={description} onChange={(e) => {setDescription(e.target.value)}}/>
                    <label>Client</label>
                    <select onChange={(e) => { setClient(e.target.value) }}>
                        {clients && clients.map((client) => (
                            <option key={client.id} value={client.id}>{client.name}</option>
                        ))}
                    </select>
                    <label>Team member</label>
                    <select onChange={(e) => { setLead(e.target.value)}}>
                        {teamMembers && teamMembers.map((teamMember) => (
                            <option key={teamMember.id} value={teamMember.id}>{teamMember.name}</option>
                        ))}
                    </select>
                    <br />
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