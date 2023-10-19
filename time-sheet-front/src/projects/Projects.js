import { Tab, Tabs } from "react-bootstrap";
import Review from "./Review";
import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import New from "./New";


const Projects = () => {

    const [clients, setClients] = useState(null);
    const [teamMembers, setTeamMembers] = useState(null);

    useEffect(() => {
        getRequest("http://localhost:8080/api/client")
            .then((res) => {
                setClients(res.data.clients)
                console.log(res.data)
            }).catch((error) => {
                console.log(error.message)
            })
        getRequest("http://localhost:8080/api/teamMember")
            .then((res) => {
                setTeamMembers(res.data.teamMembers)
            }).catch((error) => {
                console.log(error.message)
            })
    }, [])

    return (
        <div className="main">
            <h1>Projects</h1>
            <Tabs
                defaultActiveKey="review"
                id="fill-tab-example"
                className="mb-3">
                <Tab eventKey="review" title="Review">
                    <Review clients = {clients} teamMembers = {teamMembers}></Review>
                </Tab>
                <Tab eventKey="new" title="Create new project">
                    <New clients={clients} teamMembers={teamMembers}></New>
                </Tab>
            </Tabs>
        </div>
    );
}

export default Projects;