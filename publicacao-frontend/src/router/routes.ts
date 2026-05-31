import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/FeedPage.vue') }],
  },
  {
    path: '/login',
    component: () => import('layouts/EmptyLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/auth/LoginPage.vue'),
      },
      {
        path: '/register',
        component: () => import('pages/auth/RegisterPage.vue'),
      },
    ],
  },
  {
    path: '/profile',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: 'publicacao/:id',
        name: 'usuario-publicacao',
        component: () => import('pages/profile/ProfileUser.vue'),
      },
      {
        path: '',
        name: 'ver-perfil',
        component: () => import('pages/profile/ProfilePage.vue'),
      },
      {
        path: 'user/:id',
        name: 'perfil-visitante',
        component: () => import('pages/profile/ProfilePublic.vue'),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
