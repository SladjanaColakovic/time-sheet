import RadioButton from "./RadioButton";

const RadioButtons = ({ radioLabelName, radioValues, setValue, radioLabelValues, value, radioName }) => {
    return (
        <div>
            <label className="new-label">{radioLabelName}</label>
            <RadioButton radioName={radioName} value={radioValues[0]} radioValue={radioValues[0]} setValue={setValue} labelName={radioLabelValues[0]} checked={radioValues[0] === value} />
            <span className="span-radios"></span>
            <RadioButton radioName={radioName} value={radioValues[1]} radioValue={radioValues[1]} setValue={setValue} labelName={radioLabelValues[1]} checked={radioValues[1] === value} />
        </div>
    );
}

export default RadioButtons;