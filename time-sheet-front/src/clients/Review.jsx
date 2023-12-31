import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import { Accordion } from "react-bootstrap";
import Edit from "./Edit";
import * as Constants from "../constants/ClientConstants";
import { errorNotification } from "../shared/notification";


const Review = ({ countries }) => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest(Constants.CLIENT_URL)
            .then((res) => {
                setData(res.data.clients)
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
                        {data && data.map((client) => (
                            <Accordion.Item key={client.id} eventKey={client.id}>
                                <Accordion.Header>{client.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit countries={countries} client={client} setData={setData} />
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