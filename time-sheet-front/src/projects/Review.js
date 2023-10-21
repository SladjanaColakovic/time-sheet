import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import { Accordion } from "react-bootstrap";
import Edit from "./Edit";

const Review = ({ clients, teamMembers }) => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest("http://localhost:8080/api/project")
            .then((res) => {
                setData(res.data.projects)
                console.log(res.data.projects)
            }).catch((error) => {
                console.log(error);
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
                                    <Edit project={project} clients={clients} teamMembers={teamMembers} setData={setData}></Edit>
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