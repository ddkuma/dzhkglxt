<template>
  <div style="height: 100vh; overflow: hidden">
    <div style="display: flex; width: 80%; height: 100%; margin: 0 auto; align-items: center">

      <div style="flex: 1"><img src="@/assets/imgs/login.svg" alt="" style="width: 100%"></div>
      <div style="flex: 1; display: flex; justify-content: center">
        <div style="width: 50%">
          <h2 style="font-size: 30px">欢迎登录</h2>
          <div style="margin: 10px 0; color: #999">Welcome Login</div>
          <el-form :model="data.form" ref="formRef" :rules="data.rules">
            <el-form-item prop="username">
              <el-input size="large" :prefix-icon="User" placeholder="请输入账号" v-model="data.form.username"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input size="large" :prefix-icon="Lock" show-password placeholder="请输入密码" v-model="data.form.password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-select size="large" style="width: 100%" placeholder="请选择角色" v-model="data.form.role">
								<el-option value="admin" label="管理员"></el-option>
								<el-option value="hkmanteacher" label="管理老师"></el-option>
								<el-option value="hkteache" label="辅导员"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button size="large" type="primary" style="width: 100%; background-color: #5891aa; border-color: #5891aa" @click="login()">登 录</el-button>
            </el-form-item>
            <el-form-item class="regBox">
              还没有账号？去 <a href="/register">注册</a>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

  import { reactive, ref } from "vue";
  import { User, Lock } from "@element-plus/icons-vue";
  import request from "@/utils/request";
  import {ElMessage} from "element-plus";
  import router from "@/router";

  const data = reactive({
    form: {},
    rules: {
      username: [
        { required: true, message: '请输入账号', trigger: 'blur' },
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
      ],
    }
  })

  const formRef = ref()

  // 点击登录按钮的时候会触发这个方法
  const login = () => {
    formRef.value.validate((valid => {
      if (valid) {
        // 调用后台的接口
        request.post('/login', data.form).then(res => {
          if (res.code === '200') {
            // 先设置用户菜单缓存
            localStorage.setItem('xm-user', JSON.stringify(res.data))
            ElMessage.success("登录成功")
            router.push('/manager/home')
          } else {
            ElMessage.error(res.msg)
          }
        })
      }
    })).catch(error => {
      console.error(error)
    })
  }
</script>

<style scoped>
body {
  height: 100vh;
  overflow: hidden;
}
h2 {
  margin: 0;
  padding: 0;
}
a {
  text-decoration: none;
  color: #5891aa;
}
.el-form-item {
  margin-bottom: 15px !important;
}
.regBox {
  text-align: right;
}
</style>