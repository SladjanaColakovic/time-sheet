const InputListItemComponent = ({value, changeItem, itemId, property}) => {
    return ( 
        <input type="text" value={value} onChange={(e) => { changeItem(e, property, itemId) }} />
     );
}
 
export default InputListItemComponent;