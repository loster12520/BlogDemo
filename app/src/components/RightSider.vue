<script setup>
import {ref} from 'vue';

const selectedKeys = ref(['1']);
</script>

<template>
  <a-card class="user-card" :bordered="false">
    <div class="avatar-wrapper">
      <a-avatar :size="64" src="https://randomuser.me/api/portraits/men/85.jpg"/>
      <h3>张伟</h3>
      <p>zhangwei@example.com</p>
    </div>
  </a-card>

  <a-card title="快捷操作" class="quick-actions">
    <a-button block type="link" @click="$router.push('/tags')">
      <template #icon>
        <tag-outlined/>
      </template>
      热门标签
    </a-button>
    <a-button block type="link" @click="$router.push('/settings')">
      <template #icon>
        <setting-outlined/>
      </template>
      系统设置
    </a-button>
  </a-card>

  <a-card title="推荐阅读" class="recommendations">
    <a-list item-layout="horizontal">
      <a-list-item>
        <a href="#" @click.prevent="$router.push('/article/1')">Vue3最佳实践</a>
      </a-list-item>
      <a-list-item>
        <a href="#" @click.prevent="$router.push('/article/2')">前端架构设计</a>
      </a-list-item>
    </a-list>
  </a-card>
</template>

<style scoped>
.user-card {
  margin: 16px;
  text-align: center;
}

.avatar-wrapper h3 {
  margin: 12px 0 4px;
  color: rgba(0, 0, 0, 0.85);
}

.quick-actions {
  margin: 16px;
}

.recommendations {
  margin: 16px;
}
</style>