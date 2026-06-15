import axios from 'axios';
import type { AxiosInstance, AxiosRequestConfig } from 'axios';

declare global {
  interface Window {
    VITE_API_URL?: string;
  }
}

// 2. Tenta ler da janela global (K8s/Compose). Se não existir ou for o placeholder, usa a do Vite
const getBaseURL = (): string => {
  if (window.VITE_API_URL && !window.VITE_API_URL.startsWith('__VITE')) {
    return window.VITE_API_URL;
  }
  return (import.meta.env.VITE_API_URL as string) || 'http://localhost:8080';
};

const config: AxiosRequestConfig = {
  baseURL: getBaseURL(),
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
