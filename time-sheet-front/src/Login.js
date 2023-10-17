import { useState } from 'react'
import axiosInstance from './axiosIntance'

const Login = () => {

    const url = "http://localhost:8080/auth/login";

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    const handleClick = (e) => {
        e.preventDefault();
        let data = {
            username: username,
            password: password
        }
        
        axiosInstance.post(url, 
            JSON.stringify(data))
        .then((res) => {
            localStorage.clear();
            localStorage.setItem('token', res.data.accessToken)
            var obj = JSON.parse(window.atob(res.data.accessToken.split('.')[1]))
            localStorage.setItem('role', obj.role)
        }).catch((error) => {
            console.log(error);
            
        })
    }

    return (
        <div className="login">
            <h2>Prijava</h2>
            <div className="box">
                <form>
                    <div className="row">
                        <div className="col-6">
                            <label>Korisnicko ime:</label>
                        </div>
                        <div className="col-6">
                            <input type="text" value={username} onChange={(e) => {setUsername(e.target.value)}}/>

                        </div>
                    </div>
                    <div className="row">
                        <div className="col-6">
                            <label>Lozinka:</label>
                        </div>
                        <div className="col-6">
                            <input type="password" value={password} onChange={(e) => {setPassword(e.target.value)}}/>
                        </div>
                    </div>
                    <div className="row">
                        <button onClick={handleClick}>Uloguj se</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;