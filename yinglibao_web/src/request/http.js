import axios from "axios";
import layx from "vue-layx";

axios.defaults.baseURL = 'http://localhost:8088/api';
axios.defaults.timeout = 10000;

export function doGet(url, parameter) {
    return axios({
        url: url,
        method: 'get',
        params: parameter
    })
}

//指定post方法
export function  doPost(url,parameter){
    return axios({
        url:url,
        method:'post',
        data:parameter
    })
}

//请求拦截器， 在所有的请求中，修改请求的参数或header，增加token，公共参数。
axios.interceptors.request.use(config => {
    //在任意请求之前，先执行这里的代码，然后再发送请求给服务器
    let stringInfo = window.sessionStorage.getItem("ylb-user");
    if (stringInfo) {
        let userInfo = JSON.parse(stringInfo);
        //在header中添加： Authorization: Bearer <token>
        //在header中添加： uid:userId
        config.headers["Authorization"] = "Bearer " + userInfo.token;
        config.headers["uid"] = userInfo.uid;
    }
    return config;
}, error => {
    console.log("请求拦截器：" + error)
    //window.location.href="/";
})

//应答拦截器，统一接收服务器返回的结果， 对于大于1000的状态码，做错误提示
axios.interceptors.response.use(resp => {
    if (resp) {
        if (resp.data.code === 2000) {
            window.location.href = "/user/login";
        } else if (resp.data.code > 1000) {
            layx.msg(resp.data.msg, {dialogIcon: 'warn', position: 'ct'});
        }
    } else {
        console.log("应答拦截器，服务器没有返回结果")
        //window.location.href="/";
    }
    return resp;
}, err => {
    console.log("应答拦截器发生了错误：" + err);
})