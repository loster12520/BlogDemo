<script setup>
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";

const route = useRoute(); // 获取路由对象
const router = useRouter(); // 获取路由对象
const article = ref({}); // 文章内容
const articleInformation = ref({}); // 文章数据
const loading = ref(true); // 加载状态
const error = ref(null); // 错误信息

// 根据ID获取文章详情
const fetchArticle = async (id) => {
  try {
    const response = await axios.get(`http://localhost:8080/article/${id}`); // 假设后端接口为 /api/articles/:id
    article.value = response.data.data; // 更新文章数据
    articleInformation.value = article.value.articleInformation
  } catch (err) {
    error.value = 'Failed to load article details.'; // 处理错误
  } finally {
    loading.value = false; // 结束加载状态
  }
};

function goBack() {
  router.push("/")
}

function edit() {
  router.push({name: 'edit', params: {id: article.value.id}})
}

// 组件挂载时获取文章详情
onMounted(() => {
  const articleId = route.params.id; // 从路由参数中获取文章ID
  if (articleId) {
    fetchArticle(articleId);
  } else {
    error.value = 'Article ID is missing.'; // 处理ID缺失的情况
  }
});
</script>

<template>
  <div id="ArticleDetail">
    <a-page-header
        :title="articleInformation.title"
        :sub-title="articleInformation.description"
        @back="goBack"
        class="Header"
    >
      <template #extra>
        <a-button key="1" @click="edit">Edit</a-button>
      </template>
      <a-descriptions size="small" :column="3">
        <a-descriptions-item label="Author">{{ articleInformation.author }}</a-descriptions-item>
        <a-descriptions-item label="Description">{{ articleInformation.description }}</a-descriptions-item>
      </a-descriptions>
    </a-page-header>
    <div v-html="article.html" class="Content"/>
  </div>
</template>

<style scoped>
#ArticleDetail {
  background-color: #ffffff;
}

#ArticleDetail .Header {
  padding-bottom: 0;
}


#ArticleDetail .Content {
  margin: auto;
  width: 80%;
  border: 3px solid #73AD21;
  padding: 1%;
}
</style>