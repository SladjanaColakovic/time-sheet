import axiosInstance from "./axiosIntance"

const deleteRequest = (url, params) => {
    return axiosInstance.delete(url, {params});
}

export default deleteRequest;