<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import axios from "axios";

const route = useRoute(); // 获取路由对象
const router = useRouter(); // 获取路由对象

const article = ref({}); // 文章内容
const articleInformation = ref({}); // 文章数据

const loading = ref(true); // 加载状态
const error = ref(null); // 错误信息

const md = ref("")
const html = ref("");

let isChange = false;

watch(md, async (newVal) => {
  isChange = true
  if (newVal.length === 0) {
    html.value = ""; // 如果 Markdown 内容为空，直接设置 HTML 为空
    return;
  }
  try {
    const response = await axios.post("https://api.github.com/markdown", {
      text: newVal
    });
    html.value = response.data; // 设置转换后的 HTML 内容
  } catch (error) {
    console.error("请求 GitHub Markdown API 失败：", error);
    html.value = "<p>无法加载内容，请稍后重试。</p>"; // 提示用户请求失败
  }
});

// 根据ID获取文章详情
const fetchArticle = async (id) => {
  try {
    const response = await axios.get(`http://localhost:8080/article/${id}`); // 假设后端接口为 /api/articles/:id
    article.value = response.data.data; // 更新文章数据
    articleInformation.value = article.value.articleInformation
    md.value = article.value.md
  } catch (err) {
    error.value = 'Failed to load article details.'; // 处理错误
  } finally {
    loading.value = false; // 结束加载状态
  }
};

function goBack() {
  if (isChange)
    open.value = true;
  else
    router.push(`/article/${article.value.id}`)
}

function save() {
  router.push(`/article/${article.value.id}`)
}

const open = ref(false);

const handleOk = () => {
  router.push(`/article/${article.value.id}`)
};
const handleSave = () => {
  save()
}
const handleCancel = () => {
  open.value = false;
};

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
  <div id="ArticleEdit">
    <a-page-header
        :title="articleInformation.title"
        :sub-title="articleInformation.description"
        @back="goBack"
        class="Header"
    >
      <template #extra>
        <a-button key="1" @click="save">Save</a-button>
      </template>
      <a-descriptions size="small" :column="3">
        <a-descriptions-item label="Author">{{ articleInformation.author }}</a-descriptions-item>
        <a-descriptions-item label="Description">{{ articleInformation.description }}</a-descriptions-item>
      </a-descriptions>
    </a-page-header>
    <div class="Content">
      <div class="Code">
        <textarea v-model="md" placeholder="Write your markdown here..."></textarea>
      </div>
      <article class="markdown-body Render">
        <div v-html="html" class="RenderBody"/>
      </article>
    </div>
    <a-modal v-model:open="open" title="未保存！" @ok="handleOk">
      <template #footer>
        <a-button key="back" @click="handleCancel">Cancel</a-button>
        <a-button key="save" @click="handleSave">Save</a-button>
        <a-button key="submit" type="primary" @click="handleOk">Back Only</a-button>
      </template>
      <p class="Text">您的文章没保存！！</p>
      <p class="Text">您就这么愿意您的努力成果付之东流吗！！</p>
      <p class="Text">呜呜呜呜聆听求你保存保存嘛~</p>
      <p class="Text">快给我保存！！</p>
      <p class="Text">哼！</p>
    </a-modal>
  </div>
</template>

<style scoped>
#ArticleEdit {
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  align-items: stretch; /* 确保子元素宽度一致 */
}

#ArticleEdit .Header {
  padding-bottom: 0;
}

#ArticleEdit .Content {
  display: flex; /* 使用 Flexbox 布局 */
  margin: auto;
  border: 3px solid #73AD21;
  padding: 1%;
  height: 80vh; /* 设置 Content 的高度 */
}

#ArticleEdit .Code {
  flex: 1; /* 占据一半宽度 */
  padding: 1%;
  box-sizing: border-box;
  border: 2px double #73AD21;
}

#ArticleEdit .Code textarea {
  width: 100%;
  height: 100%;
  font-family: monospace;
  resize: none;
}

#ArticleEdit .Render {
  flex: 1; /* 占据一半宽度 */
  padding: 1%;
  box-sizing: border-box;
  overflow-y: auto; /* 添加滚动条 */
  border: 2px double #73AD21;
}
</style>