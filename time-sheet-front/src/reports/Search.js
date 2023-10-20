import { useEffect, useState } from "react";
import { getRequest, getRequestWithParams } from "../requests/httpClient";

const Search = ({ setData }) => {

    const [clients, setClients] = useState();
    const [projects, setProjects] = useState();
    const [categories, setCategories] = useState();
    const [teamMembers, setTeamMembers] = useState();
    const [client, setClient] = useState()
    const [project, setProject] = useState()
    const [teamMember, setTeamMember] = useState()
    const [category, setCategory] = useState()
    const [startDate, setStartDate] = useState("")
    const [endDate, setEndDate] = useState("");

    useEffect(() => {
        getRequest("http://localhost:8080/api/client")
            .then((res) => {
                console.log(res.data.clients)
                setClients(res.data.clients);
                getRequest("http://localhost:8080/api/project")
                    .then((res) => {
                        setProjects(res.data.projects);
                        getRequest("http://localhost:8080/api/teamMember")
                            .then((res) => {
                                setTeamMembers(res.data.teamMembers);
                                getRequest("http://localhost:8080/api/category")
                                    .then((res) => {
                                        setCategories(res.data.categories)
                                    })
                            })
                    })
            })
    }, [])


    const handleSearch = () => {

        const params =
        {
            clientId: client,
            projectId: project,
            categoryId: category,
            teamMemberId: teamMember,
            startDate: startDate,
            endDate: endDate
        }
        getRequestWithParams("http://localhost:8080/api/report", params)
            .then((res) => {
                console.log(res);
                setData(res.data)
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
                                <label>Team member</label>
                                <select onChange={(e) => { setTeamMember(e.target.value) }}>
                                    <option>All</option>
                                    {teamMembers && teamMembers.map((teamMember) => (
                                        <option key={teamMember.id} value={teamMember.id}>{teamMember.name}</option>
                                    ))}

                                </select>
                            </div>
                            <div className="col-4">
                                <label>Client</label>
                                <select onChange={(e) => { setClient(e.target.value) }}>
                                    <option>All</option>
                                    {clients && clients.map((client) => (
                                        <option key={client.id} value={client.id}>{client.name}</option>
                                    ))}

                                </select>
                            </div>
                            <div className="col-4">
                                <label>Project</label>
                                <select onChange={(e) => { setProject(e.target.value) }}>
                                    <option>All</option>
                                    {projects && projects.map((project) => (
                                        <option key={project.id} value={project.id}>{project.name}</option>
                                    ))}

                                </select>
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-4">
                                <label>Category</label>
                                <select onChange={(e) => { setCategory(e.target.value) }}>
                                    <option value="">All</option>
                                    {categories && categories.map((category) => (
                                        <option key={category.id} value={category.id}>{category.name}</option>
                                    ))}

                                </select>
                            </div>
                            <div className="col-4">
                                <label>Start date</label>
                                <input type="date" value={startDate} onChange={(e) => { setStartDate(e.target.value) }} />
                            </div>
                            <div className="col-4">
                                <label>End date</label>
                                <input type="date" value={endDate} onChange={(e) => { setEndDate(e.target.value) }} />
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-10"></div>
                            <div className="col-2">
                                <button onClick={handleSearch} className="search-btn">Search</button>
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