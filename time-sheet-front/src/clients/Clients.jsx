import { useEffect, useState } from 'react';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { getRequest } from "../requests/httpClient";
import Review from './Review';
import New from './New';
import * as Constants from '../constants/ClientConstants'

const Clients = () => {

    const [countries, setCountries] = useState(null);

    useEffect(() => {
        getRequest(Constants.COUNTRY_URL)
            .then((res) => {
                setCountries(res.data.countries)
            })
            .catch((error) => {
                console.log(error.message)
            })

    }, [])

    return (
        <div className="main">
            <h1>Clients</h1>
            <Tabs
                defaultActiveKey="review"
                id="fill-tab-example"
                className="mb-3">
                <Tab eventKey="review" title="Review">
                    <Review countries={countries} />
                </Tab>
                <Tab eventKey="new" title="Create new client">
                    <New countries={countries} />
                </Tab>
            </Tabs>
        </div>
    );
}

export default Clients;