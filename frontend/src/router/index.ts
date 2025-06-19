import { createRouter, createWebHistory } from 'vue-router'

// 登录
import LoginPage from '@/views/login/LoginPage.vue';

//  数据看板
import DataPage from '@/views/hr/DataPage.vue';

//  人才库
import TalentPage from '@/views/hr/TalentPage.vue';

// 新增人才
import TalentAddPage from '@/views/hr/TalentAddPage.vue';

// 人才详情
import TalentDetailPage from '@/views/hr/TalentDetailPage.vue';

// 数据看板管理员
import AdminDataPage from '@/views/admin/AdminDataPage.vue';
// 岗位管理
import AdminPostPage from '@/views/admin/AdminPostPage.vue';
// 员工管理
import AdminStaffPage from '@/views/admin/AdminStaffPage.vue';
// 删除审核
import AdminDelPage from '@/views/admin/AdminDelPage.vue';



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '数据看板',
      component: DataPage,
    },
    {
      path: '/login',
      name: '登录',
      component: LoginPage,
    },
    {
      path: '/talent',
      name: '人才库',
      component: TalentPage,
    },
    {
      path: '/talent/add',
      name: '新增人才',
      component: TalentAddPage,
    },
    {
      path: '/talent/:id',
      name: '人才详情',
      component: TalentDetailPage,
    },
    {
      path: '/admin/data',
      name: '数据看板管理员',
      component: AdminDataPage,
    },
    {
      path: '/admin/post',
      name: '岗位管理',
      component: AdminPostPage,
    },
    {
      path: '/admin/staff',
      name: '员工管理',
      component: AdminStaffPage,
    },
    {
      path: '/admin/del',
      name: '删除审核',
      component: AdminDelPage,
    }
  ],
})

router.beforeEach((to, from, next) => {
  document.title = to.name
  next()
})

export default router
