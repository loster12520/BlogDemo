<script setup>
import LeftSider from "@/components/LeftSider.vue";
import RightSider from "@/components/RightSider.vue";
</script>

<template>
  <a-layout id="BasicLayout">
    <a-layout-header class="Header">Header</a-layout-header>
    <a-layout class="Context">
      <a-layout-sider>
        <LeftSider class="LeftSider"/>
      </a-layout-sider>
      <a-layout-content>
        <router-view class="Content"/>
      </a-layout-content>
      <a-layout-sider>
        <RightSider class="RightSider"/>
      </a-layout-sider>
    </a-layout>
    <a-layout-footer class="Footer">Footer</a-layout-footer>
  </a-layout>
</template>

<style scoped>
#BasicLayout {
  margin: 0;
  padding: 0;
  min-width:100vh;
}

#BasicLayout .Context {
  min-height: 100vh;
}

#BasicLayout .Header {
  text-align: center;
  color: white;
}

#BasicLayout .Footer {
  text-align: center;
}
</style>