<template>
  <div class="user-center">
    <el-card>
      <template #header>
        <h2>用户中心</h2>
      </template>

      <el-skeleton :loading="loading" animated>
        <div class="profile">
          <el-avatar :size="80" :src="userInfo.avatar" />
          <div class="info">
            <h3>{{ userInfo.nickname }}</h3>
            <p>注册时间：{{ userInfo.registerDate }}</p>
          </div>
        </div>

        <el-tabs v-model="activeTab">
          <el-tab-pane label="我的文章" name="articles">
            <el-empty description="暂无文章" />
          </el-tab-pane>
          <el-tab-pane label="账户设置" name="settings">
            <el-form label-width="80px">
              <el-form-item label="邮箱">
                <el-input v-model="userInfo.email" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary">保存修改</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-skeleton>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const loading = ref(false)
const activeTab = ref('articles')
const userInfo = ref({
  avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  nickname: '用户昵称',
  email: 'user@example.com',
  registerDate: '2024-03-01'
})
</script>

<style scoped>
.profile {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}
.info {
  margin-left: 20px;
}
.info h3 {
  margin: 0 0 8px 0;
}
</style>