import InputRadioComponent from "./InputRadioComponent";

const RadioComponent = ({ radioLabelName, radioValues, setValue, radioLabelValues, value, radioName }) => {
    return (
        <div>
            <label style={{ display: "block" }}>{radioLabelName}</label>
            <InputRadioComponent radioName={radioName} value={radioValues[0]} radioValue={radioValues[0]} setValue={setValue} labelName={radioLabelValues[0]} checked = {radioValues[0] === value}></InputRadioComponent>
            {/* <input name={radioName} type="radio" value="ACTIVE" onChange={(e) => { setValue(e.target.value) }} checked={"ACTIVE" === value} />
            <span className="radio-span"></span>
            <label>{radioValues[0]}</label> */}
            <span className="radios-span"></span>
            <InputRadioComponent radioName={radioName} value={radioValues[1]} radioValue={radioValues[1]} setValue={setValue} labelName={radioLabelValues[1]} checked = {radioValues[1] === value}></InputRadioComponent>
            {/* <input name={radioName} type="radio" value="INACTIVE" onChange={(e) => { setValue(e.target.value); console.log(value) }} checked={"INACTIVE" === value}/>
            <span className="radio-span"></span>
            <label>{radioValues[1]}</label> */}
        </div>
    );
}

export default RadioComponent;