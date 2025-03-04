<template>
  <div>
    <form @submit.prevent="handleLogin">
      <input v-model="username" type="text" placeholder="Username"/>
      <input v-model="password" type="password" placeholder="Password"/>
      <button type="submit">Login</button>
    </form>
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script setup>
import axios from 'axios';
import {ref} from "vue";
import {useRouter} from "vue-router";

const username = ref("")
const password = ref("")
const errorMessage = ref("")
const router = useRouter();

const handleLogin = async () => {
  try {
    const response = await axios.post(
        'http://localhost:8080/api/login',
        {
          username: username.value,
          password: password.value
        },
        {withCredentials: true} // 携带 Cookie（Session 方式）
    );

    if (response.data.success) {
      await router.push('/'); // 跳转到主页
    }
  } catch (error) {
    errorMessage.value = '登录失败，请检查用户名和密码';
  }
}
</script>

<style scoped>

</style>