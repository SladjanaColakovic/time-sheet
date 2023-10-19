import { useEffect, useState } from "react";
import { getRequest } from "../requests/httpClient";
import { Accordion } from "react-bootstrap";
import Edit from "./Edit";

const Review = ({countries}) => {

    const [data, setData] = useState(null);

    useEffect(() => {
        getRequest("http://localhost:8080/api/client")
            .then((res) => {
                setData(res.data.clients)
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
                        {data && data.map((client) => (
                            <Accordion.Item key={client.id} eventKey={client.id}>
                                <Accordion.Header>{client.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit countries={countries} client={client} setData={setData}></Edit>
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