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

function App() {

  const token = localStorage.getItem('token')

  useEffect(() => {
    console.log("Ok");
    if (token) {
      var obj = JSON.parse(window.atob(token.split('.')[1]))
      console.log(obj)
      if(obj.exp < Date.now() / 1000){
        localStorage.clear();
        window.location.reload();
      }
    }
  })

  return (
    <Router>
      <div>
        <div>
          {token && <Navbar></Navbar>}
        </div>
        <div className="content">
          <Routes>
            {!token && <Route path='/' element={<Login />}></Route>}
            {token && <Route path='/' element={<Categories />}></Route>}
            <Route element={<PrivateRoute roles={["ADMIN"]} />}>
              <Route path='/home' element={<Home />} />
            </Route>
            <Route path='/categories' element={<Categories />} />
            <Route path='/teamMembers' element={<TeamMembers />} />
            <Route path='/projects' element={<Projects />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
