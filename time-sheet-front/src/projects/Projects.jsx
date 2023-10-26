import { Tab, Tabs } from "react-bootstrap";
import Review from "./Review";
import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import New from "./New";
import * as Constants from "../constants/ProjectConstants";
import { errorNotification } from "../shared/notification";

const Projects = () => {

    const [clients, setClients] = useState(null);
    const [teamMembers, setTeamMembers] = useState(null);

    useEffect(() => {
        getRequest(Constants.CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients)
            }).catch((error) => {
                errorNotification(error.message);
            })
        getRequest(Constants.TEAM_MEMBER_URL)
            .then((res) => {
                setTeamMembers(res.data.teamMembers)
            }).catch((error) => {
                errorNotification(error.message);
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
                    <Review clients={clients} teamMembers={teamMembers} />
                </Tab>
                <Tab eventKey="new" title="Create new project">
                    <New clients={clients} teamMembers={teamMembers} />
                </Tab>
            </Tabs>
        </div>
    );
}

export default Projects;