import { useLocation, Navigate, Outlet } from "react-router-dom";
import AccessDenied from "../AccessDenied";


const ProtectedRoute = ({ children, roles }) => {
    let location = useLocation();

    const currentRole = localStorage.getItem('role');

    return currentRole ?
        roles.find((role) => currentRole.includes(role)) ?
            (<Outlet />) :
            (<AccessDenied />) :
        (<Navigate to="/" state={{ from: location }} />)
};

export default ProtectedRoute;