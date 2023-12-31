import SvgButton from "../buttons/SvgButton";

const CalendarNavigation= ({ back, next, content }) => {
    return (
        <div className="calendar-nav">
            <SvgButton handleClick={back} className="back" icon={"back"}/>
            {content && <label className="date">{content}</label>}
            <SvgButton handleClick={next} className="next" icon={"next"}/>
        </div>
    );
}

export default CalendarNavigation;