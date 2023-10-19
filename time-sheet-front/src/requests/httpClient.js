import axiosInstance from "./axiosIntance"

export const getRequest = (url) => {
    return axiosInstance.get(url);
}

export const postRequest = (url, data) => {
    return axiosInstance.post(url, data);
}

export const putRequest = (url, data) => {
    return axiosInstance.put(url, data);
}

export const deleteRequest = (url, params) => {
    return axiosInstance.delete(url, {params});
}
