<script setup>
import { ref } from 'vue';
const selectedKeys = ref(['1']);
</script>

<template>
  <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
    <a-menu-item key="create" @click="$router.push('/article/new')">
      <file-add-outlined />
      <span>新建文章</span>
    </a-menu-item>
    
    <a-sub-menu key="category">
      <template #title>
        <folder-outlined />
        <span>分类管理</span>
      </template>
      <a-menu-item key="tech">技术分类</a-menu-item>
      <a-menu-item key="life">生活分类</a-menu-item>
    </a-sub-menu>

    <a-menu-item key="drafts">
      <container-outlined />
      <span>草稿箱（3）</span>
    </a-menu-item>
    
  </a-menu>
</template>

<style scoped>

</style>