<template>
  <div class="manager-container">
    <div class="manager-header">
      <div class="manager-header-left">
        <img style="border-radius: 50%" src="@/assets/imgs/logo.png" alt="">
        <div class="title">贺卡管理系统</div>
      </div>
      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item to="/manager/home">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{ router.currentRoute.value.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="manager-header-right">
        <el-dropdown style="cursor: pointer;">
          <div style="padding-right: 20px; display: flex; align-items: center;">
            <img v-if="data.user.avatar" :src="data.user?.avatar" alt="" style="width: 40px; height: 40px; display: block; border-radius: 50%">
            <img v-else src="@/assets/imgs/avatar.png" alt="" style="width: 40px; height: 40px; display: block; border-radius: 50%">
            <span style="margin-left: 5px">{{ data.user?.name }}</span>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click.native="goToPerson">个人资料</el-dropdown-item>
              <el-dropdown-item @click.native="router.push('/manager/password')">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>

    <div style="display: flex">
      <div class="manager-main-left">
        <el-menu
            :default-active="router.currentRoute.value.path"
            :default-openeds="['info', 'user', 'sys']"
            router
        >
          <el-menu-item index="/manager/home">
            <el-icon><home-filled /></el-icon><span>系统首页</span>
          </el-menu-item>
          <el-sub-menu index="info">
            <template #title><el-icon><Menu /></el-icon><span>信息管理</span></template>
							<el-menu-item index="/manager/hkStudent">学生管理</el-menu-item>
							<el-menu-item index="/manager/hkClass">班级管理</el-menu-item>
							<el-menu-item index="/manager/zhTodaybirthday">贺卡发送管理</el-menu-item>
							<el-menu-item v-if="data.user.role === 'admin' || data.user.role === 'hkmanteacher'" index="/manager/hkHksc">贺卡生成</el-menu-item>
							<el-menu-item index="/manager/hkHk">贺卡管理</el-menu-item>
							<el-menu-item v-if="data.user.role === 'admin' || data.user.role === 'hkmanteacher'" index="/manager/hkDxtj">短信统计</el-menu-item>

          </el-sub-menu>
          <el-sub-menu index="user" v-if="data.user.role==='admin'">
            <template #title><el-icon><UserFilled /></el-icon><span>用户信息</span></template>
							<el-menu-item v-if="data.user.role === 'admin'" index="/manager/admin">管理员</el-menu-item>
							<el-menu-item v-if="data.user.role === 'admin' || data.user.role === 'hkmanteacher'" index="/manager/hkmanteacher">管理老师</el-menu-item>
							<el-menu-item v-if="data.user.role === 'admin' || data.user.role === 'hkmanteacher'" index="/manager/hkteache">辅导员</el-menu-item>

          </el-sub-menu>
          <el-sub-menu index="sys">
            <template #title><el-icon><Setting /></el-icon><span>系统管理</span></template>
            <el-menu-item index="/manager/password">修改密码</el-menu-item>
            <el-menu-item @click.native="logout">退出登录</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>

      <div class="manager-main-right">
        <router-view @updateUser="updateUser" />
      </div>
    </div>

  </div>
</template>

<script setup>
import {HomeFilled, UserFilled} from "@element-plus/icons-vue";
import {reactive} from "vue";
import router from "@/router";

const data = reactive({
  user:  JSON.parse(localStorage.getItem('xm-user') || '{}')
})
const goToPerson = () => {
	if (data.user.role === 'admin') {
		router.push("/manager/pAdmin")
	}
	if (data.user.role === 'hkmanteacher') {
		router.push("/manager/pHkmanteacher")
	}
	if (data.user.role === 'hkteache') {
		router.push("/manager/pHkteache")
	}

}
const logout = () => {
  localStorage.removeItem('user')
  router.push('/login')
}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
}
</script>

<style scoped>
@import '@/assets/css/manager.css';
</style>
