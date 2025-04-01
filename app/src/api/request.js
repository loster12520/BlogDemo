import axios from 'axios'

const service = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 此处可添加token等全局请求头
    config.headers.Authorization = 'Bearer ' + localStorage.getItem('token')
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    // 统一错误处理
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

export default service