const InputRadioComponent = ({radioName, labelName, radioValue, setValue, value}) => {
    return ( 
        <span>
            <input name={radioName} type="radio" value={radioValue} onChange={(e) => { setValue(e.target.value) }} checked={radioValue === value}/>
            <span className="radio-span"></span>
            <label>{labelName}</label>
        </span>
     );
}
 
export default InputRadioComponent;