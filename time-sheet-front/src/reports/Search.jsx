import { useEffect, useState } from "react";
import { getRequest, getRequestWithParams } from "../requests/httpClient";
import SelectSearch from "../components/select/SelectSearch";
import Button from "../components/buttons/Button";
import InputDate from "../components/input/InputDate";
import * as Constants from '../constants/ReportConstants'
import { errorNotification } from "../shared/notification";

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

    useEffect(() => {
        getRequest(Constants.CLIENT_URL)
            .then((res) => {
                setClients(res.data.clients);
            })
            .catch((error) => {
                errorNotification(error.message);
            })

        getRequest(Constants.TEAM_MEMBER_URL)
            .then((res) => {
                setTeamMembers(res.data.teamMembers);
            })
            .catch((error) => {
                errorNotification(error.message);
            })

        getRequest(Constants.CATEGORY_URL)
            .then((res) => {
                setCategories(res.data.categories)
            })
            .catch((error) => {
                errorNotification(error.message);
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

        getRequestWithParams(Constants.REPORT_URL, params)
            .then((res) => {
                setData(res.data);
            })
            .catch((error) => {
                errorNotification(error.message);
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

        getRequestWithParams(Constants.REPORT_URL + "/pdf", params)
            .then((res) => {
                console.log(res.data);
                const file = new Blob([res.data], {
                    type: 'application/pdf',
                });
                //console.log(file)
                const fileURL = URL.createObjectURL(file);
                //console.log(fileURL)
                //window.open(fileURL);
            })

    }

    const selectClient = (value) => {
        setClient(value);
        const params = {
            clientId: value
        }
        getRequestWithParams(Constants.PROJECT_URL + "/client", params)
            .then((res) => {
                setProjects(res.data.projects)
            })
            .catch((error) => {
                errorNotification(error.message);
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
                                <SelectSearch labelName={"Team member"} setValue={setTeamMember} items={teamMembers} />
                            </div>
                            <div className="col-4">
                                <SelectSearch labelName={"Client"} setValue={selectClient} items={clients} />
                            </div>
                            <div className="col-4">
                                <SelectSearch labelName={"Project"} setValue={setProject} items={projects} />
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-4">
                                <SelectSearch labelName={"Category"} setValue={setCategory} items={categories} />
                            </div>
                            <div className="col-4">
                                <InputDate labelName={"Start date"} value={startDate} setValue={setStartDate} />
                            </div>
                            <div className="col-4">
                                <InputDate labelName={"End date"} value={endDate} setValue={setEndDate} />
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-8"></div>
                            <div className="col-2">
                                <Button handleClick={handleSearch} buttonName={"Search"} className="search-btn" />
                            </div>
                            <div className="col-2">
                                <Button buttonName={"Create PDF"} className="pdf-btn" handleClick={pdfReport} />
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