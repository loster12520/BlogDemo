<script setup>
import LeftSider from "@/components/LeftSider.vue";
import RightSider from "@/components/RightSider.vue";
</script>

<template>
  <a-layout id="BasicLayout">
    <el-header class="Header">
      <div class="nav-container">
        <el-menu mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">文章管理</el-menu-item>
          <el-submenu index="3" class="user-menu">
            <template #title>用户中心</template>
            <el-menu-item index="3-1">个人资料</el-menu-item>
            <el-menu-item index="3-2">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </el-header>
    <a-layout class="Context">
      <el-aside width="25%" class="Side">
        <LeftSider class="LeftSider"/>
      </el-aside>
      <a-layout-content>
        <router-view class="Content"/>
      </a-layout-content>
      <el-aside width="25%" class="Side">
        <RightSider class="RightSider"/>
      </el-aside>
    </a-layout>
    <el-footer class="Footer">© 2024 博客系统 All Rights Reserved
      <br>by lingting
    </el-footer>
  </a-layout>
</template>

<style scoped>
#BasicLayout {
  margin: 0;
  padding: 0;
  width: 100%;
  max-width: 100vw;
}

#BasicLayout .Context {
  display: flex;
  flex-direction: row;
  justify-content: center;
  min-height: 100vh;
  max-width: 100%;
}

#BasicLayout .Side {
  width: 10%;
}

#BasicLayout .Content {
  flex: 1 1 auto;
  min-width: 100vh;
  padding: 20px;
  background: #f5f7fa;
}

#BasicLayout .Header {
  text-align: center;
  color: white;
}

#BasicLayout .Footer {
  text-align: center;
}
</style>