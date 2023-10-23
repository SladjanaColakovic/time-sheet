import { useEffect, useState } from "react";
import { getRequest, getRequestWithParams } from "../requests/httpClient";
import SelectSearchComponent from "../components/SelectSearchComponent";
import ButtonComponent from "../components/ButtonComponent";
import InputDateComponent from "../components/InputDateComponent";

const Search = ({ setData }) => {

    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();
    const [teamMembers, setTeamMembers] = useState();
    const [client, setClient] = useState(null)
    const [project, setProject] = useState(null)
    const [teamMember, setTeamMember] = useState(null)
    const [category, setCategory] = useState(null)
    const [startDate, setStartDate] = useState("")
    const [endDate, setEndDate] = useState("");

    const CLIENT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CLIENT_URL
    const PROJECT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_PROJECT_URL
    const TEAM_MEMBER_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_TEAM_MEMBER_URL
    const CATEGORY_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_CATEGORY_URL
    const REPORT_URL = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_REPORT_URL

    useEffect(() => {
        getRequest(CLIENT_URL)
            .then((res) => {
                console.log(res.data.clients)
                setClients(res.data.clients);
                getRequest(PROJECT_URL)
                    .then((res) => {
                        setProjects(res.data.projects);
                        getRequest(TEAM_MEMBER_URL)
                            .then((res) => {
                                setTeamMembers(res.data.teamMembers);
                                getRequest(CATEGORY_URL)
                                    .then((res) => {
                                        setCategories(res.data.categories)
                                    })
                            })
                    })
            })
    }, [])


    const handleSearch = () => {

        console.log(project)
        const params =
        {
            clientId: (client !== "All") ? client : null,
            projectId: (project !== "All") ? project : null,
            categoryId: (category !== "All") ? category : null,
            teamMemberId: (teamMember !== "All") ? teamMember : null,
            startDate: startDate,
            endDate: endDate
        }
        console.log(params)

        getRequestWithParams(REPORT_URL, params)
            .then((res) => {
                setData(res.data);
                console.log(res.data)
            })
            .catch((error) => {
                console.log(error.message)
            })
    }

    return (
        <div>
            <div className="row">
                <div className="col-1"></div>
                <div className="col-10">
                    <div className="box">
                        <div className="row">
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Team member"} setValue={setTeamMember} items={teamMembers} />
                            </div>
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Client"} setValue={setClient} items={clients} />
                            </div>
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Project"} setValue={setProject} items={projects} />
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Category"} setValue={setCategory} items={categories} />
                            </div>
                            <div className="col-4">
                                <InputDateComponent labelName={"Start date"} value={startDate} setValue={setStartDate} />
                            </div>
                            <div className="col-4">
                                <InputDateComponent labelName={"End date"} value={endDate} setValue={setEndDate} />
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-10"></div>
                            <div className="col-2">
                                <ButtonComponent handleClick={handleSearch} buttonName={"Search"} className="search-btn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-1"></div>
            </div>
            <br />
        </div>
    );
}

export default Search;