import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Accordion from 'react-bootstrap/Accordion';

const Catgeories = () => {
    return (
        <div className="main">
            <h1>Categories</h1>
            <Tabs
                defaultActiveKey="review"
                id="fill-tab-example"
                className="mb-3"

            >
                <Tab eventKey="review" title="Review">
                    <div className='span-top'>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-10'>
                                <Accordion>
                                    <Accordion.Item eventKey="0">
                                        <Accordion.Header>Accordion Item #1</Accordion.Header>
                                        <Accordion.Body>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                                            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                                            minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                                            aliquip ex ea commodo consequat. Duis aute irure dolor in
                                            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                                            culpa qui officia deserunt mollit anim id est laborum.
                                        </Accordion.Body>
                                    </Accordion.Item>
                                    <Accordion.Item eventKey="1">
                                        <Accordion.Header>Accordion Item #2</Accordion.Header>
                                        <Accordion.Body>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                                            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                                            minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                                            aliquip ex ea commodo consequat. Duis aute irure dolor in
                                            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                                            culpa qui officia deserunt mollit anim id est laborum.
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </div>
                            <div className='col-1'></div>
                        </div>
                    </div>
                </Tab>
                <Tab eventKey="new" title="New">
                    Tab content for Home
                </Tab>
            </Tabs>
        </div>
    );
}

export default Catgeories;