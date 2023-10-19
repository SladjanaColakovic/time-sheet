import { useEffect, useState } from "react";
import { Accordion } from "react-bootstrap";
import getRequest from "../requests/getRequest";
import Edit from "./Edit";

const Review = () => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest("http://localhost:8080/api/teamMember")
            .then((res) => {
                setData(res.data.teamMembers)
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
                        {data && data.map((teamMember) => (
                            <Accordion.Item key={teamMember.id} eventKey={teamMember.id}>
                                <Accordion.Header>{teamMember.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit teamMember = {teamMember} setData = {setData}></Edit>
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