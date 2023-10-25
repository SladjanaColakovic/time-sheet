import { Routes, Route, Outlet, Navigate } from 'react-router-dom'
import Login from './Login';
import PrivateRoute from './auth/ProtectedRoute';
import Navbar from './Navbar';
import Categories from './categories/Categories';
import { useEffect } from 'react';
import TeamMembers from './teamMembers/TeamMembers';
import Projects from './projects/Projects';
import Clients from './clients/Clients';
import Reports from './reports/Reports';
import TimeSheet from './timeSheet/TimeSheet';
import WeekView from './timeSheet/WeekView';
import { Role } from './auth/Role';
import { useSelector } from 'react-redux';
import { selectUser } from './auth/userSlice';
import { useDispatch } from 'react-redux';
import { logout } from './auth/userSlice';
import { NotificationContainer } from "react-notifications";


function App() {

  const user = useSelector(selectUser)
  const dispatch = useDispatch();

  useEffect(() => {
    if (!user) return;
    if (user.expiredIn < Date.now() / 1000) {
      dispatch(logout())
    }
  }, [user])

  return (

    <Routes>
      {!user && <Route path='/' element={<Login />}></Route>}
      <Route path='/' element={<LoggedInLayout />}>
        {user && user.role === Role.ADMIN && <Route path='/' element={<Navigate to='/categories' />}></Route>}
        {user && user.role === Role.WORKER && <Route path='/' element={<Navigate to='/timeSheet' />}></Route>}
        <Route element={<PrivateRoute roles={[Role.ADMIN]} />}>
          <Route path='/categories' element={<Categories />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.ADMIN]} />}>
          <Route path='/teamMembers' element={<TeamMembers />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.ADMIN]} />}>
          <Route path='/projects' element={<Projects />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.ADMIN]} />}>
          <Route path='/clients' element={<Clients />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.ADMIN]} />}>
          <Route path='/reports' element={<Reports />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.WORKER]} />}>
          <Route path='/timeSheet' element={<TimeSheet />} />
        </Route>
        <Route element={<PrivateRoute roles={[Role.WORKER]} />}>
          <Route path='/weekView/:startDateFormat/:endDateFormat/:date' element={<WeekView />} />
        </Route>
      </Route>
    </Routes>

  );
}


function LoggedInLayout() {
  return (
    <div>
      <Navbar />
      <Outlet />
      <NotificationContainer />
    </div>
  );
}


export default App;
