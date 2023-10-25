const ButtonComponent = ({ handleClick, className, buttonName }) => (
        <button onClick={handleClick} className={className}>{buttonName}</button>
    );
    
export default ButtonComponent;