const InputPasswordComponent = ({labelName, value, setValue}) => {
    return (
        <div>
            <label>{labelName}</label>
            <input type="password" value={value} onChange={(e) => { setValue(e.target.value) }} />
        </div>
    );
}

export default InputPasswordComponent;