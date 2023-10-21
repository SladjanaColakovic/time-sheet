const ButtonComponent = ({ handleClick, className, buttonName }) => {
    return (
        <button onClick={handleClick} className={className}>{buttonName}</button>
    );
}

export default ButtonComponent;