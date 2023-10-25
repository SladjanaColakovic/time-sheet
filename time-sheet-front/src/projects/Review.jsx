import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import { Accordion } from "react-bootstrap";
import Edit from "./Edit";
import * as Constants from "../constants/ProjectConstants";
import { notification } from "../shared/notification";

const Review = ({ clients, teamMembers }) => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest(Constants.PROJECT_URL)
            .then((res) => {
                setData(res.data.projects)
            }).catch((error) => {
                notification(error.message);
            })

    }, [])

    return (
        <div className="span-top">
            <div className="row">
                <div className="col-2"></div>
                <div className="col-8">
                    <Accordion>
                        {data && data.map((project) => (
                            <Accordion.Item key={project.id} eventKey={project.id}>
                                <Accordion.Header>{project.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit project={project} clients={clients} teamMembers={teamMembers} setData={setData} />
                                </Accordion.Body>
                            </Accordion.Item>
                        ))}
                    </Accordion>
                </div>
                <div className="col-2"></div>
            </div>
        </div>
    );
}

export default Review;