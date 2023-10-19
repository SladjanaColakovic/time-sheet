import axiosInstance from "./axiosIntance"

const postRequest = (url, data) => {
    return axiosInstance.post(url, data);
}

export default postRequest;