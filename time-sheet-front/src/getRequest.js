import axiosInstance from "./axiosIntance"

const getRequest = (url) => {
    return axiosInstance.get(url);
}

export default getRequest;