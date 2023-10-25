const InputDate = ({ labelName, value, setValue }) => {
    return (
        <div>
            <label>{labelName}</label>
            <input type="date" value={value} onChange={(e) => { setValue(e.target.value) }} />
        </div>
    );
}

export default InputDate;