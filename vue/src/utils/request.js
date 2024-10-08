import axios from 'axios'
import {serverIp, protocol} from "../../public/config";
import router from "@/router";

const request = axios.create({
    baseURL: protocol + '://' + serverIp,  // 注意！！ 这里是全局统一加上了 后端接口前缀 前缀，后端必须进行跨域配置！
    timeout: 10000
})
// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : null
    //如果user存在就在请求头中设置token
    if (user) {
        config.headers['token'] = user.token;  // 设置请求头
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        //当权限验证不通过时直接弹出
        if (res.code === '401') {
            localStorage.removeItem('user')
            localStorage.removeItem('lists')
            router.push('/login')
        }

        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

