import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8088/api';
axios.defaults.timeout = 10000;

export function doGet(url, parameter) {
    return axios({
        url: url,
        method: 'get',
        params: parameter
    })
}