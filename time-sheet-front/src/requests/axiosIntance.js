import axios from 'axios'

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/"
});

axiosInstance.interceptors.request.use(
    config => {
        config.headers['Content-Type'] = 'application/json';
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config
    },
    error => {
        Promise.reject(error)
    }
);

export default axiosInstance;