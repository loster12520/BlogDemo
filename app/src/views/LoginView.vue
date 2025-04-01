<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">用户登录</h2>
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {login} from "@/api/login.js";

const form = ref({
  userName: '',
  password: ''
});

const router = useRouter();

const handleLogin = async () => {
  const token = (await login(form.value)).data["token"]
  if (token) {
    localStorage.setItem('token', token);
    await router.push('/');
  } else {
    alert('登录失败，请检查用户名和密码');
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  padding-top: 100px;
}

.login-card {
  width: 500px;
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
}
</style>