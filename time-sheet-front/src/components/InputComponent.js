const InputComponent = ({ labelName, placeholder, value, setValue }) => {
    return (
        <div>
            <label>{labelName}</label>
            <input type="text" placeholder={placeholder} value={value} onChange={(e) => { setValue(e.target.value) }} />
        </div>
    );
}

export default InputComponent;