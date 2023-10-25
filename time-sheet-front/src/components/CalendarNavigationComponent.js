import SvgButton from "./SvgButton";
const CalendarNavigationComponent = ({ back, next, content }) => {
    return (
        <div style={{ textAlign: "center" }}>
            <SvgButton handleClick={back} className="back" icon={"back"}/>
            {content && <label className="date">{content}</label>}
            <SvgButton handleClick={next} className="next" icon={"next"}/>
        </div>
    );
}

export default CalendarNavigationComponent;