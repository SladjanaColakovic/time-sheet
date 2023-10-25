import SvgIcons from "./SvgIcons";

const SvgButton = ({handleClick, className, name, icon}) => {
    return (
        <button onClick={handleClick} className={className}>
            <SvgIcons icon={icon}/>
            <label>{name}</label>
        </button>
    );
}

export default SvgButton;