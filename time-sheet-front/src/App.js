import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './Login';
import PrivateRoute from './auth/ProtectedRoute';
import Home from './Home';
import Navbar from './Navbar';
import Catgeories from './categories/Categories';

function App() {

  const token = localStorage.getItem('token')

  return (
    <Router>
      <div>
        <div>
          {token && <Navbar></Navbar>}
        </div>
        <div className="content">
          <Routes>
            <Route path='/' element={<Login />}></Route>
            <Route element={<PrivateRoute roles={["ADMIN"]}/>}>
              <Route path='/home' element={<Home/>}/>
            </Route>
            <Route path='/categories' element={<Catgeories/>}/>
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
