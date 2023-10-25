import { useNavigate } from 'react-router-dom';
import SvgButton from './components/buttons/SvgButton';
import { Role } from './auth/Role';
import { useDispatch, useSelector } from 'react-redux';
import { logout, selectUser } from './auth/userSlice';

const Navbar = () => {

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const user = useSelector(selectUser);

    const showCategories = () => {
        navigate('/categories', { replace: true });
    }

    const showTeamMembers = () => {
        navigate('/teamMembers', { replace: true });
    }

    const showProjects = () => {
        navigate('/projects', { replace: true });
    }

    const showClients = () => {
        navigate('/clients', { replace: true });
    }

    const showReport = () => {
        navigate('/reports', { replace: true });
    }

    const showTimeSheet = () => {
        navigate('/timeSheet', { replace: true });
    }

    const doLogout = () => {
        dispatch(logout());
    }

    const getRole = () => {
        if (user) {
            const role = user.role;
            return role;
        }
    }

    return (
        <div className="sidenav">
            {getRole() === Role.WORKER && <SvgButton handleClick={showTimeSheet} icon={"timeSheet"} name={"TimeSheet"} />}
            {getRole() === Role.ADMIN &&
                <span>
                    <SvgButton handleClick={showReport} icon={"report"} name={"Reports"} />
                    <SvgButton handleClick={showClients} icon={"clients"} name={"Clients"} />
                    <SvgButton handleClick={showProjects} icon={"projects"} name={"Projects"} />
                    <SvgButton handleClick={showTeamMembers} icon={"teamMembers"} name={"Team members"} />
                    <SvgButton handleClick={showCategories} icon={"categories"} name={"Categories"} />
                </span>
            }
            <SvgButton className="bottomButton" handleClick={doLogout} icon={"logout"} name={"Logout"} />
        </div>

    );
}

export default Navbar;