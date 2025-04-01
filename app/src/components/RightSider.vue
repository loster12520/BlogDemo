<script setup>
// 右侧边栏组件
</script>

<template>
  <div class="right-sider">
    <el-menu
      default-active="1"
      class="el-menu-vertical"
    >
      <el-menu-item index="1">
        <span>热门文章</span>
      </el-menu-item>
      <el-menu-item index="2">
        <span>标签云</span>
      </el-menu-item>
      <el-menu-item index="3">
        <span>广告位</span>
      </el-menu-item> 
      <el-button 
      type="primary"
      @click="$router.push('/login')"
      class="login-btn"
      index="4"
    >
      登录
    </el-button>
    </el-menu>
  </div>
</template>

<style scoped>
.right-sider {
  height: 100%;
  background: #fff;
  box-shadow: -2px 0 6px rgba(0,0,0,0.1);
}
</style>