<template>
  <div style="width: 50%;">
    <div class="card">
      <el-form ref="formRef" :model="data.user" label-width="80px" style="padding: 20px 30px" status-icon>
					<el-form-item label="账号" prop="username">
						<el-input v-model="data.user.username" placeholder="请输入账号"></el-input>
					</el-form-item>
					<el-form-item label="名称" prop="name">
						<el-input v-model="data.user.name" placeholder="请输入名称"></el-input>
					</el-form-item>
					<el-form-item label="头像" prop="avatar">
						<el-upload class="avatar-uploader" :action="'http://localhost:9090/files/upload'" :on-success="avatarSuccessUpload" list-type="picture">
							<el-button type="primary">点击上传</el-button>
						</el-upload>
					</el-form-item>
					<el-form-item label="手机" prop="phone">
						<el-input v-model="data.user.phone" placeholder="请输入手机"></el-input>
					</el-form-item>
					<el-form-item label="邮箱" prop="email">
						<el-input v-model="data.user.email" placeholder="请输入邮箱"></el-input>
					</el-form-item>
        <el-form-item>
          <el-button type="primary" @click="update">保存</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

const data = reactive({
  user:  JSON.parse(localStorage.getItem('xm-user') || '{}'), 
})

const emit = defineEmits(["updateUser"])

const update = () => {
  request.put('/hkmanteacher/update', data.user).then(res => {
    if (res.code === '200') {
      ElMessage.success('更新成功')
      localStorage.setItem('xm-user', JSON.stringify(data.user))
      emit('updateUser')
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const avatarSuccessUpload = (res) => {
	data.user.avatar = res.data;
}

</script>

<style scoped>
.avatar-uploader .avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  text-align: center;
}
</style>
