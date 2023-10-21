import InputRadioComponent from "./InputRadioComponent";

const RadioComponent = ({ radioName, radioValues, setValue, radioLabelValues, value }) => {
    return (
        <div>
            <label style={{ display: "block" }}>{radioName}</label>
            <InputRadioComponent radioName={radioName} value={value} radioValue={radioValues[0]} setValue={setValue} labelName={radioLabelValues[0]}></InputRadioComponent>
            {/* <input name={radioName} type="radio" value="ACTIVE" onChange={(e) => { setValue(e.target.value) }} checked={"ACTIVE" === value} />
            <span className="radio-span"></span>
            <label>{radioValues[0]}</label> */}
            <span className="radios-span"></span>
            <InputRadioComponent radioName={radioName} value={value} radioValue={radioValues[1]} setValue={setValue} labelName={radioLabelValues[1]}></InputRadioComponent>
            {/* <input name={radioName} type="radio" value="INACTIVE" onChange={(e) => { setValue(e.target.value); console.log(value) }} checked={"INACTIVE" === value}/>
            <span className="radio-span"></span>
            <label>{radioValues[1]}</label> */}
        </div>
    );
}

export default RadioComponent;