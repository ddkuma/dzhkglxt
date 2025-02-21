import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login'},
    {
      path: '/manager',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/manager/login',
      children: [
        { path: 'password', meta: { name: '修改密码' }, component: () => import('@/views/manager/Password.vue')},
        { path: 'home', meta: { name: '系统首页' }, component: () => import('@/views/manager/Home.vue')},
		{ path: 'admin', meta: { name: '管理员' }, component: () => import("@/views/manager/Admin.vue") },
		{ path: 'hkmanteacher', meta: { name: '管理老师' }, component: () => import("@/views/manager/Hkmanteacher.vue") },
		{ path: 'hkStudent', meta: { name: '学生管理' }, component: () => import("@/views/manager/HkStudent.vue") },
		{ path: 'hkteache', meta: { name: '辅导员' }, component: () => import("@/views/manager/Hkteache.vue") },
		{ path: 'hkClass', meta: { name: '班级管理' }, component: () => import("@/views/manager/HkClass.vue") },
		{ path: 'zhTodaybirthday', meta: { name: '贺卡发送管理' }, component: () => import("@/views/manager/ZhTodaybirthday.vue") },
		{ path: 'hkHksc', meta: { name: '贺卡生成' }, component: () => import("@/views/manager/HkHksc.vue") },
		{ path: 'hkHk', meta: { name: '贺卡管理' }, component: () => import("@/views/manager/HkHk.vue") },
		{ path: 'hkDxtj', meta: { name: '短信统计' }, component: () => import("@/views/manager/HkDxtj.vue") },
		{ path: 'pAdmin', meta: { name: '个人信息' }, component: () => import("@/views/manager/pAdmin.vue")  },
		{ path: 'pHkmanteacher', meta: { name: '个人信息' }, component: () => import("@/views/manager/pHkmanteacher.vue")  },
		{ path: 'pHkteache', meta: { name: '个人信息' }, component: () => import("@/views/manager/pHkteache.vue")  },

      ]
    },
    { path: '/login', component: () => import('@/views/Login.vue')},
	{ path: '/register', component: () => import("@/views/Register.vue") },
    { path: '/404', component: () => import('@/views/404.vue')},
    { path: '/:pathMatch(.*)', redirect: '/404', hidden: true }
  ]
})

export default router
