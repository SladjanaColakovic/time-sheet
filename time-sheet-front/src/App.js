import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './Login';
import PrivateRoute from './auth/ProtectedRoute';
import Home from './Home';
import Navbar from './Navbar';
import Categories from './categories/Categories';
import { useEffect } from 'react';
import TeamMembers from './teamMembers/TeamMembers';
import Projects from './projects/Projects';
import Clients from './clients/Clients';
import Reports from './reports/Reports';
import TimeSheet from './timeSheet/TimeSheet';
import WeekView from './timeSheet/WeekView';
import { useNavigate } from 'react-router-dom';

function App() {

  const token = localStorage.getItem('token')
  const role = localStorage.getItem('role')
  const navigate = useNavigate();

  useEffect(() => {
    if (token) {
      var obj = JSON.parse(window.atob(token.split('.')[1]))
      if (obj.exp < Date.now() / 1000) {
        localStorage.clear();
        window.location.reload();
      }
    }
  }, [token])

  return (
   
      <div>
        <div>
          {token && <Navbar></Navbar>}
        </div>
        <div className="content">
          <Routes>
            {!token &&  <Route path='/' element={<Login />}></Route>}
            {token && role === 'ADMIN' && <Route path='/' element={<Categories />}></Route>}
            {token && role === 'WORKER' && <Route path='/' element={<TimeSheet />}></Route>}
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/home' element={<Home />} />
            </Route>
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/categories' element={<Categories />} />
            </Route>
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/teamMembers' element={<TeamMembers />} />
            </Route>
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/projects' element={<Projects />} />
            </Route>
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/clients' element={<Clients />} />
            </Route>
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/reports' element={<Reports />} />
            </Route>
            <Route element={<PrivateRoute roles={["WORKER"]} />}>
              <Route path='/timeSheet' element={<TimeSheet />} />
            </Route>
            <Route element={<PrivateRoute roles={["WORKER"]} />}>
              <Route path='/weekView/:startDateFormat/:endDateFormat/:date' element={<WeekView />} />
            </Route>
          </Routes>
        </div>
      </div>

  );
}

export default App;
