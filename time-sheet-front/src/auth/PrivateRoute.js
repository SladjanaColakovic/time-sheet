import { useLocation, Navigate, Outlet } from "react-router-dom";


const PrivateRoute = ({ children, roles}) => {
    let location = useLocation();

    const currentRole = localStorage.getItem('role');

    return roles.find((role) => currentRole.includes(role)) ? (
        <Outlet />
    ) : currentRole ? (
        <div>Access Denied...</div>
    ) : (
        <Navigate to="/login" state={{ from: location }} />
    )

};

export default PrivateRoute;