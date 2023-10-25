const ListItemSelect = ({value, changeValue, itemId, items}) => {
    return (
        <select value={value} onChange={(e) => { changeValue(e, itemId) }}>
            {items && items.map((item) => (
                <option key={item.id} value={item.id}>{item.name}</option>
            ))}
        </select>
    );
}

export default ListItemSelect;