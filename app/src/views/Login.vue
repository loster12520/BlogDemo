<template>
  <a-card class="login-card">
    <a-form
        :model="formState"
        @finish="handleSubmit"
    >
      <a-form-item
          label="用户名"
          name="username"
          :rules="[{ required: true, message: '请输入用户名' }]"
      >
        <a-input v-model:value="formState.username"/>
      </a-form-item>

      <a-form-item
          label="密码"
          name="password"
          :rules="[{ required: true, message: '请输入密码' }]"
      >
        <a-input-password v-model:value="formState.password"/>
      </a-form-item>

      <a-form-item>
        <a-button type="primary" html-type="submit">登录</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script setup>
import {reactive} from 'vue';
import {message} from 'ant-design-vue';
import axios from 'axios';
import {useRouter} from 'vue-router';

const router = useRouter();
const formState = reactive({
  username: '',
  password: '',
});

const handleSubmit = async () => {
  try {
    const response = await axios.post('http://localhost:8080/public/user/login', formState, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    if (response.data.code === 200) {
      message.success('登录成功');
      localStorage.setItem('token', response.data.data.token);
      await router.push('/');
    } else {
      message.error('登录失败：' + response.data.message);
    }
  } catch (error) {
    message.error('登录失败：' + error.response?.data?.message || '服务器错误');
  }
};
</script>

<style scoped>
.login-card {
  width: 400px;
  margin: 100px auto;
}
</style>