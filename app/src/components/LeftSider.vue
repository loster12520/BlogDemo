<script setup>
// 左侧边栏组件
</script>

<template>
  <div class="left-sider">
    <el-menu
      default-active="1"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
    >
      <el-menu-item index="1">
        <span>最新文章</span>
      </el-menu-item>
      <el-menu-item index="2">
        <span>热门标签</span>
      </el-menu-item>
      <el-menu-item index="3">
        <span>文章分类</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.left-sider {
  height: 100%;
  background: #fff;
}
.el-menu-vertical {
  height: 100%;
}
</style>