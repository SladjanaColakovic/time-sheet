const SelectEditComponent = ({labelName, setValue, items, selectedValue}) => {
    return (
        <div>
            <label>{labelName}</label>
            <select value={selectedValue} onChange={(e) => { setValue(e) }}>
                {items && items.map((item) => (
                    <option key={item.id} value={item.id}>{item.name}</option>
                ))}
            </select>
        </div>
    );
}

export default SelectEditComponent;