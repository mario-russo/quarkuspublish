import axios from 'axios';
import type { AxiosInstance, AxiosRequestConfig } from 'axios';

const config: AxiosRequestConfig = {
  baseURL: import.meta.env.VITE_API_URL as string,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  },
};

const api: AxiosInstance = axios.create(config);

// REQUEST INTERCEPTOR
api.interceptors.request.use((config) => {

  const token = localStorage.getItem('token');

  const publicRoutes = [
    '/auth/login',
    '/auth/register'
  ];

  const isPublicRoute =
    publicRoutes.some(route =>
      config.url?.includes(route)
    );

  if (token && !isPublicRoute) {

    config.headers = config.headers ?? {};

    config.headers.Authorization =
      `Bearer ${token}`;
  }

  return config;
});

// RESPONSE INTERCEPTOR
api.interceptors.response.use(
   (response) => response,

  async (error) => {


    if (error.response?.status === 401 || error.response?.status === 403) {

      localStorage.removeItem('token');

      window.location.href = '/login';

    }

    return Promise.reject(
      error instanceof Error
        ? error
        : new Error('Erro desconhecido')
    );
  }
);



export default api;
