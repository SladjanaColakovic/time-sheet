import DailyCalendarTableHeader from "./DailyCalenadarTableHeader";
import InputListItemComponent from "./InputListItemComponent";
import SelectListItemComponent from "./SelectListItemComponent";
import AddItem from "./AddItem";
import Edititem from "./EditItem";

const ItemsTable = ({ items, changeCategory, changeClient, clients, changeProject, projects, categories, changeItem, edit, setClient, client, setProject, project, setCategory, category, description, time, overtime, setDescription, setTime, setOvertime, addItem }) => {
    return (
        <div className="table-box">
            {items && <table>
                <thead>
                    <DailyCalendarTableHeader />
                </thead>
                <tbody>
                    {items.map((item) => (
                        <Edititem item={item} key={item.id} categories={categories} clients={clients} projects={projects} changeClient={changeClient} changeCategory={changeCategory} changeProject={changeProject} changeItem={changeItem} edit={edit}/>
                    ))}
                    <AddItem clients={clients} setClient={setClient} client={client} projects={projects} setProject={setProject} project={project} categories={categories} setCategory={setCategory} category={category} description={description} setDescription={setDescription} time={time} setTime={setTime} overtime={overtime} setOvertime={setOvertime} addItem={addItem}/>
                </tbody>
            </table>}
        </div>
    );
}

export default ItemsTable;