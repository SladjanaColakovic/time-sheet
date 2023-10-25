import { useLocation, Navigate, Outlet } from "react-router-dom";
import AccessDenied from "../AccessDenied";
import { useSelector } from "react-redux";
import { selectUser } from "./userSlice";


const ProtectedRoute = ({ roles }) => {
    let location = useLocation();
    const user = useSelector(selectUser);

    return user ?  roles.find((role) => user.role.includes(role)) ?
        (<Outlet />) :
        (<AccessDenied />) :
    (<Navigate to="/" state={{ from: location }} />)
};

export default ProtectedRoute;


// const LoggedIn = (Component) => {
//     const isLoggedIn = localStorage.getItem('token');

//     if(!isLoggedIn) return <Unauthenticated />

//     return <Component />
// };


// return LoggedIn;
