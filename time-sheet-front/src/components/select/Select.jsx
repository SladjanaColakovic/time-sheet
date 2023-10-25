const Select = ({ labelName, setValue, items, value }) => {
    return (
        <div>
            <label>{labelName}</label>
            <select value={value} onChange={(e) => { setValue(e.target.value) }}>
                <option value={''}>
                Choose an option
                </option>
                {items && items.map((item) => (
                    <option key={item.id} value={item.id}>{item.name}</option>
                ))}
            </select>
        </div>
    );
}

export default Select;