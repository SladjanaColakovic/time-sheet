import { useState } from 'react'
import { postRequest } from './requests/httpClient';
import Button from './components/buttons/Button';
import InputPassword from './components/input/InputPassword';
import InputText from './components/input/InputText';
import { NotificationContainer, NotificationManager } from "react-notifications";
import { login } from './auth/userSlice';
import { useDispatch } from 'react-redux';


const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    
    const handleClick = () => {
        let data = {
            username: username,
            password: password
        }

        postRequest(process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_AUTH_URL + '/login', data)
            .then((res) => {
                // localStorage.clear();
                // localStorage.setItem('token', res.data.accessToken)
                var obj = JSON.parse(window.atob(res.data.accessToken.split('.')[1]))
                dispatch(
                    login({
                     token: res.data.accessToken,
                     exipredIn: obj.exp,
                     role: obj.role,
                     id: obj.id,
                     loggedIn: true,
                    })
                )
                // localStorage.setItem('role', obj.role)
                // window.location.reload();
            }).catch((error) => {
                NotificationManager.error(error.message, '', 5000);
            })
    }

    return (
        <div className="login">
            <h1>Login</h1>
            <div className="box">
                <div className='row'>
                    <InputText labelName={"Username"} value={username} setValue={setUsername} />
                </div>
                <div className='row'>
                    <InputPassword value={password} labelName={"Password"} setValue={setPassword} />
                </div>
                <div className="row">
                    <Button handleClick={handleClick} buttonName={"Login"} />
                </div>
            </div>
            <NotificationContainer/>
        </div>
    );
}

export default Login;