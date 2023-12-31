import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Review from './Review';
import New from './New';

const Categories = () => {
    return (
        <div className="main">
            <h1>Categories</h1>
            <Tabs
                defaultActiveKey="review"
                id="fill-tab-example"
                className="mb-3">
                <Tab eventKey="review" title="Review">
                    <Review/>
                </Tab>
                <Tab eventKey="new" title="Create new category">
                    <New/>
                </Tab>
            </Tabs>
        </div>
    );
}

export default Categories;