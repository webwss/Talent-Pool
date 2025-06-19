import axios from 'axios'
import {message} from "ant-design-vue";

// 创建 Axios 实例
const myAxios = axios.create({
    baseURL: '/api',
    timeout: 60000,
    withCredentials: true,
})

// 全局请求拦截器
myAxios.interceptors.request.use(
    function (config) {
        return config
    },
    function (error) {
        // Do something with request error
        return Promise.reject(error)
    },
)

// 全局响应拦截器
myAxios.interceptors.response.use(
    function (response) {
        if (response.data.code === 40100) {
            // 未登录
            message.error('请先登录')
        } else {
            return response
        }
    },
    function (error) {
        return Promise.reject(error)
    },
)

export default myAxios
