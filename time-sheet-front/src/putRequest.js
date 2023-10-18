import axiosInstance from "./axiosIntance";

const putRequest = (url, data) => {
    return axiosInstance.put(url, data);
}

export default putRequest;