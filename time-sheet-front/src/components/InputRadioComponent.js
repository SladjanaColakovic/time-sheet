const InputRadioComponent = ({ radioName, labelName, radioValue, setValue, checked }) => {
    return (
        <span>
            <input name={radioName} type="radio" value={radioValue} onChange={(e) => { setValue(e.target.value) }} checked={checked}/>
            <span className="radio-span"></span>
            <label>{labelName}</label>
        </span>
    );
}

export default InputRadioComponent;