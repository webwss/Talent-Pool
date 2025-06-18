import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'

// 登录
import LoginPage from "@/views/login/LoginPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
        path: '/login',
        name: 'login',
        component: LoginPage,
      },
  ],
})

export default router
