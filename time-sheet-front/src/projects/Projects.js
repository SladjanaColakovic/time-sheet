import { Tab, Tabs } from "react-bootstrap";
import Review from "./Review";
import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import New from "./New";


const Projects = () => {

    const [clients, setClients] = useState(null);
    const [teamMembers, setTeamMembers] = useState(null);
    const CLIENT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL
    const TEAM_MEMBER_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_TEAM_MEMBER_URL

    useEffect(() => {
        getRequest(CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients)
                console.log(res.data)
            }).catch((error) => {
                console.log(error.message)
            })
        getRequest(TEAM_MEMBER_URL)
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
                    <Review clients={clients} teamMembers={teamMembers}></Review>
                </Tab>
                <Tab eventKey="new" title="Create new project">
                    <New clients={clients} teamMembers={teamMembers}></New>
                </Tab>
            </Tabs>
        </div>
    );
}

export default Projects;