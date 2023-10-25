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

function App() {

  const token = localStorage.getItem('token')
  const role = localStorage.getItem('role')

  useEffect(() => {
    if (!token) return;

    var obj = JSON.parse(window.atob(token.split('.')[1]))
    if (obj.exp < Date.now() / 1000) {
      localStorage.clear();
      window.location.reload();
    }
  }, [token])

  return (


    <Routes>
      {!token && <Route path='/' element={<Login />}></Route>}
      <Route path='/' element={<LoggedInLayout />}>
        {token && role === Role.ADMIN && <Route path='/' element={<Navigate to='/categories' />}></Route>}
        {token && role === Role.WORKER && <Route path='/' element={<Navigate to='/timeSheet' />}></Route>}
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
      <div>
        {<Navbar />}
      </div>
      <Outlet />
    </div>
  );
}


export default App;
