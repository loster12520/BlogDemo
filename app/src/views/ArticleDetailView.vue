<template>
  <div class="article-detail">
    <el-card>
      <template #header>
        <h2>{{ article.title || '加载中...' }}</h2>
      </template>
      
      <el-skeleton :loading="loading" animated>
        <div class="meta-info">
          <span>作者：{{ article.author }}</span>
          <span>发布日期：{{ article.createTime }}</span>
        </div>
        <div class="markdown-body" v-html="renderedContent" />
      </el-skeleton>
      
      <div class="actions">
        <el-button @click="$router.go(-1)">返回列表</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const loading = ref(true)
const article = ref({})
const renderedContent = ref('')

// 正式数据加载
onMounted(async () => {
  try {
    const { data } = await getArticleDetail(route.params.id)
    article.value = data
    
    // 获取Markdown原始内容
    const response = await fetch(`/article-details/${route.params.id}`)
    const markdownContent = await response.json()
    
    // 通过GitHub API渲染
    const renderResponse = await fetch('https://api.github.com/markdown', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        text: markdownContent,
        mode: 'markdown'
      })
    })
    renderedContent.value = await renderResponse.text()
  } catch (error) {
    ElMessage.error('内容加载失败: ' + error.message)
  } finally {
    loading.value = false
  }
})

// 加载GitHub markdown样式
onMounted(() => {
  const link = document.createElement('link')
  link.rel = 'stylesheet'
  link.href = 'https://cdn.jsdelivr.net/npm/github-markdown-css@5.1.0/github-markdown-light.css'
  document.head.appendChild(link)
})
</script>

<style scoped>
.meta-info {
  margin-bottom: 20px;
  color: #666;
}
.meta-info span {
  margin-right: 15px;
}
.actions {
  margin-top: 20px;
}
</style>