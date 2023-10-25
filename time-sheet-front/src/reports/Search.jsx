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
                setClients(res.data.clients);
            })
            .catch((error) => {
                console.log(error.message)
            })

        getRequest(TEAM_MEMBER_URL)
            .then((res) => {
                setTeamMembers(res.data.teamMembers);
            })
            .catch((error) => {
                console.log(error.message)
            })

        getRequest(CATEGORY_URL)
            .then((res) => {
                setCategories(res.data.categories)
            })
            .catch((error) => {
                console.log(error.message)
            })
    }, [])


    const handleSearch = () => {

        const params =
        {
            clientId: (client !== "All") ? client : null,
            projectId: (project !== "All") ? project : null,
            categoryId: (category !== "All") ? category : null,
            teamMemberId: (teamMember !== "All") ? teamMember : null,
            startDate: startDate,
            endDate: endDate
        }

        getRequestWithParams(REPORT_URL, params)
            .then((res) => {
                setData(res.data);
                console.log(res.data)
            })
            .catch((error) => {
                console.log(error.message)
            })
    }

    const pdfReport = () => {
        const params =
        {
            clientId: (client !== "All") ? client : null,
            projectId: (project !== "All") ? project : null,
            categoryId: (category !== "All") ? category : null,
            teamMemberId: (teamMember !== "All") ? teamMember : null,
            startDate: startDate,
            endDate: endDate
        }

        getRequestWithParams(REPORT_URL + "/pdf", params)
            .then((res) => {
                console.log(res.data);
                // const file = new Blob([res.data], {
                //     type: 'application/pdf',
                //   });

                //   const fileURL = URL.createObjectURL(file);

                //   window.open(fileURL);
            })

    }

    const selectClient = (value) => {
        setClient(value);
        const params = {
            clientId: value
        }
        getRequestWithParams(PROJECT_URL + "/client", params)
            .then((res) => {
                setProjects(res.data.projects)
            })
    }

    return (
        <div className="search">
            <div className="row">
                <div className="col-1"></div>
                <div className="col-10">
                    <div className="box">
                        <div className="row">
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Team member"} setValue={setTeamMember} items={teamMembers} />
                            </div>
                            <div className="col-4">
                                <SelectSearchComponent labelName={"Client"} setValue={selectClient} items={clients} />
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
                            <div className="col-8"></div>
                            <div className="col-2">
                                <ButtonComponent handleClick={handleSearch} buttonName={"Search"} className="search-btn" />
                            </div>
                            <div className="col-2">
                                <ButtonComponent buttonName={"Create PDF"} className="pdf-btn" handleClick={pdfReport} />
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