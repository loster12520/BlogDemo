<script setup>
import { ref } from 'vue';
const selectedKeys = ref(['1']);
</script>

<template>
  <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
    <a-menu-item key="1">
      <span>Option 1</span>
    </a-menu-item>
    <a-menu-item key="2">
      <span>Option 2</span>
    </a-menu-item>
    <a-sub-menu key="sub1">
      <template #title>
            <span>
              <span>User</span>
            </span>
      </template>
      <a-menu-item key="3">Tom</a-menu-item>
      <a-menu-item key="4">Bill</a-menu-item>
      <a-menu-item key="5">Alex</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub2">
      <template #title>
            <span>
              <span>Team</span>
            </span>
      </template>
      <a-menu-item key="6">Team 1</a-menu-item>
      <a-menu-item key="8">Team 2</a-menu-item>
    </a-sub-menu>
    <a-menu-item key="9">
      <span>File</span>
    </a-menu-item>
  </a-menu>
</template>

<style scoped>

</style>