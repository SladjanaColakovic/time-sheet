import { useState } from 'react'
import { postRequest } from './requests/httpClient';
import ButtonComponent from './components/ButtonComponent';
import InputPasswordComponent from './components/InputPasswordComponent';
import InputComponent from './components/InputComponent';
import { NotificationContainer, NotificationManager } from "react-notifications";


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

        postRequest(url, data)
            .then((res) => {
                localStorage.clear();
                localStorage.setItem('token', res.data.accessToken)
                var obj = JSON.parse(window.atob(res.data.accessToken.split('.')[1]))
                console.log(obj)
                localStorage.setItem('role', obj.role)
                window.location.reload();
            }).catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    return (
        <div className="login">
            <h1>Login</h1>
            <div className="box">

                <div className='row'>
                    <InputComponent labelName={"Username"} value={username} setValue={setUsername} />
                </div>
                <div className='row'>
                    <InputPasswordComponent value={password} labelName={"Password"} setValue={setPassword} />
                </div>
                <div className="row">
                    <ButtonComponent handleClick={handleClick} buttonName={"Login"} />
                </div>

            </div>
            <NotificationContainer/>
        </div>
    );
}

export default Login;