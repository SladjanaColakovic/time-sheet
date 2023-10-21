const InputEditComponent = ({ value, changeValue, property, labelName }) => {
    return (
        <div>
            <label>{labelName}</label>
            <input type="text" value={value} onChange={(e) => { changeValue(e, property) }} />
        </div>
    );
}

export default InputEditComponent;