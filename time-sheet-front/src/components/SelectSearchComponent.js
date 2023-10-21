const SelectSearchComponent = ({labelName, setValue, items}) => {
    return ( 
        <div>
            <label>{labelName}</label>
            <select onChange={(e) => { setValue(e.target.value) }}>
            <option value={"All"}>All</option>
                {items && items.map((item) => (
                    <option key={item.id} value={item.id}>{item.name}</option>
                ))}
            </select>
        </div>
     );
}
 
export default SelectSearchComponent;