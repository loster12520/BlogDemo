<template>
  <div class="article-list">
    <el-card>
      <template #header>
        <div class="list-header">
          <h2>文章列表</h2>
          <div>
            <el-button type="primary" @click="fetchArticles">刷新</el-button>
            <el-button type="success" @click="$router.push('/add-article')">新建文章</el-button>
          </div>
        </div>
      </template>

      <el-skeleton :loading="loading" animated>
        <el-table :data="articles" style="width: 100%">
          <el-table-column prop="title" label="标题" width="180"/>
          <el-table-column prop="author" label="作者" width="120"/>
          <el-table-column prop="createTime" label="发布时间"/>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                  type="text"
                  @click="$router.push(`/article/${scope.row.id}`)"
              >查看详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-skeleton>
    </el-card>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {getArticleInfoList} from '@/api/article'

const loading = ref(true)
const articles = ref([])

const fetchArticles = async () => {
  try {
    loading.value = true
    const data = await getArticleInfoList().data
    articles.value = data?.map(item => ({
      ...item,
      createTime: new Date(item.createTime).toLocaleString()
    })) ?? []
  } finally {
    loading.value = false
  }
}

fetchArticles()
</script>

<style scoped>
.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>