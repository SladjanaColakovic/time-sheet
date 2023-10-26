import { useEffect, useState } from "react";
import { Accordion } from "react-bootstrap";
import { getRequest } from "../requests/httpClient";
import Edit from "./Edit";
import * as Constants from '../constants/TeamMemberConstants'
import { errorNotification } from "../shared/notification";

const Review = () => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest(Constants.URL)
            .then((res) => {
                setData(res.data.teamMembers)
            }).catch((error) => {
                errorNotification(error.message);
            })
    }, [])

    return (
        <div className="span-top">
            <div className="row">
                <div className="col-2"></div>
                <div className="col-8">
                    <Accordion>
                        {data && data.map((teamMember) => (
                            <Accordion.Item key={teamMember.id} eventKey={teamMember.id}>
                                <Accordion.Header>{teamMember.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit teamMember={teamMember} setData={setData} />
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