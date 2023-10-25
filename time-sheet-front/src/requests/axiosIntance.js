import axios from 'axios'
import { store } from '../auth/store';

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/"
});

axiosInstance.interceptors.request.use(
    config => {
        config.headers['Content-Type'] = 'application/json';

        if (store.getState().user.user) {
            config.headers['Authorization'] = 'Bearer ' + store.getState().user.user.token;
        }
        return config
    },
    error => {
        Promise.reject(error)
    }
);

export default axiosInstance;