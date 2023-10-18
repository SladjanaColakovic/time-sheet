import { useEffect, useState } from "react"
import axiosInstance from "./axiosIntance";

const useFetch = (url) => {

    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        axiosInstance.get(url)
        .then((res) => {
            console.log(res);
            setData(res.data.categories)
        })
        .catch((error) => {
            setError(error.message)
        })

    }, [url])

    return [data, error]
}

export default useFetch;
