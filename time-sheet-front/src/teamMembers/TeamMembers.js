import { Tab } from "bootstrap";
import { Tabs } from "react-bootstrap";
import Review from "./Review";
import New from "./New"

const TeamMembers = () => {
    return (
        <div>
            <div className="main">
                <h1>Team Members</h1>
                <Tabs
                    defaultActiveKey="review"
                    id="fill-tab-example"
                    className="mb-3">
                    <Tab eventKey="review" title="Review">
                        <Review />
                    </Tab>
                    <Tab eventKey="new" title="Create new member">
                        <New />
                    </Tab>
                </Tabs>
            </div>
        </div>
    );
}

export default TeamMembers;