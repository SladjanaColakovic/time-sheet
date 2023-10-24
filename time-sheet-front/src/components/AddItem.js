import InputComponent from "./InputComponent";
import SelectComponent from "./SelectComponent";

const AddItem = ({clients, setClient, client, projects, setProject, project, categories, setCategory, category, description, setDescription, time, setTime, overtime, setOvertime, addItem}) => {
    return (
        <tr className="tr-margin">
            <td>
                <SelectComponent items={clients} setValue={setClient} value={client} />
            </td>
            <td>
                <SelectComponent items={projects} setValue={setProject} value={project} />
            </td>
            <td>
                <SelectComponent items={categories} setValue={setCategory} value={category} />
            </td>
            <td>
                <InputComponent value={description} setValue={setDescription} />
            </td>
            <td>
                <InputComponent value={time} setValue={setTime} />
            </td>
            <td>
                <InputComponent value={overtime} setValue={setOvertime} />
            </td>
            <td>
                <button onClick={addItem} className="add-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" className="bi bi-plus-square-fill" viewBox="0 0 16 16">
                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z" />
                    </svg>
                </button>
            </td>
        </tr>
    );
}

export default AddItem;