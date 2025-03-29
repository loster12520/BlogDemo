<template>
  <a-card title="个人中心" class="profile-card">
    <a-form :model="formState" @finish="handleSave">
      <a-form-item label="用户名">
        <a-input v-model:value="formState.username" disabled />
      </a-form-item>
      
      <a-form-item 
        label="电子邮箱"
        name="email"
        :rules="[{ type: 'email', message: '请输入有效的邮箱格式' }]"
      >
        <a-input v-model:value="formState.email" />
      </a-form-item>

      <a-form-item>
        <a-button type="primary" html-type="submit">保存修改</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script setup>
import { reactive, onMounted } from 'vue';
import { message } from 'ant-design-vue';
import service from "@/utils/request";

const formState = reactive({
  username: localStorage.getItem('username') || '',
  email: ''
});

onMounted(async () => {
  try {
    const response = await service.get('/user/info', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    });
    formState.email = response.data.data.email;
  } catch (error) {
    message.error('获取用户信息失败');
  }
});

const handleSave = async () => {
  try {
    await axios.post('http://localhost:8080/user/profile', {
      email: formState.email
    }, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    });
    message.success('信息更新成功');
  } catch (error) {
    message.error('更新失败：' + error.response?.data?.message);
  }
};
</script>

<style scoped>
.profile-card {
  width: 600px;
  margin: 20px auto;
  padding: 20px;
}
</style>