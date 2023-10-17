import { useLocation, Navigate, Outlet } from "react-router-dom";
import AccessDenied from "../AccessDenied";


const ProtectedRoute = ({ children, roles}) => {
    let location = useLocation();

    const currentRole = localStorage.getItem('role');

    return roles.find((role) => currentRole.includes(role)) ? (
        <Outlet />
    ) : currentRole ? (
        <AccessDenied/>
    ) : (
        <Navigate to="/login" state={{ from: location }} />
    )

};

export default ProtectedRoute;