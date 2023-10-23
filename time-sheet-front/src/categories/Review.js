import Accordion from 'react-bootstrap/Accordion';
import Edit from './Edit';
import { useEffect, useState } from 'react';
import { getRequest } from '../requests/httpClient'

const Review = () => {

    const [data, setData] = useState(null);
    const URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL

    useEffect(() => {
        getRequest(URL)
            .then((res) => {
                setData(res.data.categories)
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
                        {data && data.map((category) => (
                            <Accordion.Item key={category.id} eventKey={category.id}>
                                <Accordion.Header>{category.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit category={category} setData={setData}/>
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