<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import axios from "axios";

const route = useRoute() // 获取路由对象
const router = useRouter() // 获取路由对象

const article = ref({
  id: 0,
  md: "",
  html: "",
  articleInformation: {
    id: 0,
    title: "",
    author: "",
    description: ""
  }
}) // 文章内容

const loading = ref(true) // 加载状态
const error = ref(null) // 错误信息

let isChange = false
let isNew = false

const flush = async () => {
  isChange = true
  if (article.value.md.length === 0) {
    article.value.html = ""; // 如果 Markdown 内容为空，直接设置 HTML 为空
    return;
  }
  try {
    const response = await axios.post("https://api.github.com/markdown", {
      text: article.value.md
    });
    article.value.html = response.data; // 设置转换后的 HTML 内容
  } catch (error) {
    console.error("请求 GitHub Markdown API 失败：", error);
    article.value.html = "<p>无法加载内容，请稍后重试。</p>"; // 提示用户请求失败
  }
}

// 根据ID获取文章详情
const fetchArticle = async (id) => {
  try {
    const response = await axios.get(`http://localhost:8080/admin/article/${id}`, { headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') } }); // 假设后端接口为 /api/articles/:id
    article.value = {
      ...response.data.data
    }; // 更新文章数据
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

const save = async () => {
  try {
    if (isNew) {
      // 创建新文章信息
      const infoResponse = await axios.post("http://localhost:8080/article/information/add", {
        ...article.value.articleInformation
      });

      // 创建文章内容
      const contentResponse = await axios.post("http://localhost:8080/article/add", {
        md: article.value.md,
        html: article.value.html,
        articleInformationId: infoResponse.data.id, // 使用实际返回的ID
      });

      // 更新本地数据
      article.value = {
        ...contentResponse.data.data,
        articleInformation: {
          ...infoResponse.data.data
        }
      };
    } else {
      // 更新现有文章
      const contentResponse = await axios.post(`http://localhost:8080/article/add`, {
        id: article.value.id,
        md: article.value.md,
        html: article.value.html,
        articleInformationId: article.value.articleInformation.id,
      });

      article.value = {
        ...contentResponse.data.data
      };
    }
    await router.push(`/article/${article.value.articleInformation.id}`);
  } catch (error) {
    console.error("保存失败:", error);
    // 这里可以添加用户提示
  }
}

const open = ref(false);

const handleOk = () => {
  router.push(`/article/${article.value.articleInformation.id}`)
};
const handleSave = () => {
  save()
}
const handleCancel = () => {
  open.value = false;
};

const informationOpen = ref(false);

const informationHandleSave = async () => {
  const result = await axios.post("http://localhost:8080/article/information/add", {
    ...article.value.articleInformation
  })
  article.value.articleInformation = {
    ...result.data.data
  }
  isNew = false;
  informationOpen.value = false;
}
const informationHandleCancel = () => {
  informationOpen.value = false;
};

// 组件挂载时获取文章详情
onMounted(() => {
  const articleId = route.params.id; // 从路由参数中获取文章ID
  if (articleId == 0) {
    isNew = true
  } else if (articleId) {
    fetchArticle(articleId);
  } else {
    error.value = 'Article ID is missing.'; // 处理ID缺失的情况
  }
});
</script>

<template>
  <div id="ArticleEdit">
    <a-page-header
        :title="article.articleInformation.title"
        :sub-title="article.articleInformation.description"
        @back="goBack"
        class="Header"
    >
      <template #extra>
        <a-button key="3" @click="flush">Flush</a-button>
        <a-button key="1" @click="() => informationOpen=true">EditInformation</a-button>
        <a-button key="2" @click="save">Save</a-button>
      </template>
      <template #tags>
        <a-tag color="blue">{{ article.articleInformation.author }}</a-tag>
      </template>
    </a-page-header>


    <div class="Content">
      <div class="Code">
        <a-textarea v-model:value="article.md" placeholder="Write your markdown here..." :rows="4"/>
      </div>
      <a-divider type="vertical" class="Divider"/>
      <article class="markdown-body Render">
        <div v-html="article.html" class="RenderBody"/>
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
    <a-modal v-model:open="informationOpen" title="信息修改" @ok="informationHandleSave">
      <template #footer>
        <a-button key="back" @click="informationHandleCancel">Cancel</a-button>
        <a-button key="submit" type="primary" @click="informationHandleSave">Save</a-button>
      </template>
      <a-space direction="vertical">
        <a-input prefix="Title" v-model:value="article.articleInformation.title" placeholder="your title"/>
        <a-input prefix="Author" v-model:value="article.articleInformation.author" placeholder="your author"/>
        <a-input prefix="Description" v-model:value="article.articleInformation.description"
                 placeholder="your description"/>
      </a-space>
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
  padding-bottom: 5vh;
}

#ArticleEdit .Content {
  display: flex; /* 使用 Flexbox 布局 */
  margin: auto;
  border: 3px solid rgba(143, 143, 143, 0.48);
  padding: 1%;
  width: 90%;
  height: 80vh; /* 设置 Content 的高度 */
}

#ArticleEdit .Divider {
  height: 100%;
  color: #4b7eac;
}

#ArticleEdit .Code {
  flex: 1; /* 占据一半宽度 */
  padding: 1%;
  box-sizing: border-box;
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
}
</style>