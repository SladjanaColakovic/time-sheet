const SelectComponent = ({ labelName, setValue, items }) => {
    return (
        <div>
            <label>{labelName}</label>
            <select onChange={(e) => { setValue(e.target.value) }}>
                {items && items.map((item) => (
                    <option key={item.id} value={item.id}>{item.name}</option>
                ))}
            </select>
        </div>
    );
}

export default SelectComponent;