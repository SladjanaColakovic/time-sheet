import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './Login';
import PrivateRoute from './auth/ProtectedRoute';
import Home from './Home';

function App() {
  return (
    <Router>
      <div>
        <div className="navbar">

        </div>
        <div className="content">
          <Routes>
            <Route path='/login' element={<Login />}></Route>
            <Route element={<PrivateRoute roles={["ADMIN"]}/>}>
              <Route path='/home' element={<Home/>}/>
            </Route>
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
