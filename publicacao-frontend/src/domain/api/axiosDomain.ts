import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig } from 'axios'

const config: AxiosRequestConfig = {
  baseURL: import.meta.env.VITE_API_URL as string,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
}

const api: AxiosInstance = axios.create(config)

export default api