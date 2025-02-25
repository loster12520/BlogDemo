<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import axios from "axios";

const route = useRoute() // 获取路由对象
const router = useRouter() // 获取路由对象

const article = ref({
  id: 0,
  title: "",
  author: "",
  description: "",
}) // 文章内容
const articleInformation = ref({
  id: 0,
  title: "",
  author: "",
  description: ""
}) // 文章数据

const loading = ref(true) // 加载状态
const error = ref(null) // 错误信息

const md = ref("")
const html = ref("")

let isChange = false
let isNew = false

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

const save = async () => {
  try {
    if (isNew) {
      // 创建新文章信息
      const infoResponse = await axios.post("http://localhost:8080/article/information/add", {
        title: articleInformation.value.title,
        author: articleInformation.value.author,
        description: articleInformation.value.description,
      });

      // 创建文章内容
      const contentResponse = await axios.post("http://localhost:8080/article/add", {
        md: md.value,
        html: html.value,
        articleInformationId: infoResponse.data.id, // 使用实际返回的ID
      });

      // 更新本地数据
      article.value = {
        ...contentResponse.data,
        articleInformation: infoResponse.data
      };
    } else {
      console.log("new!!")
      // 更新现有文章
      await axios.post(`http://localhost:8080/article/add`, {
        id: article.id,
        md: md.value,
        html: html.value,
        articleInformationId: articleInformation.value.id,
      });
    }

    isChange = false;
    router.push(`/article/${article.value.id}`);
  } catch (error) {
    console.error("保存失败:", error);
    // 这里可以添加用户提示
  }
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
  if (articleId === 0) {
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
        :title="articleInformation.title"
        :sub-title="articleInformation.description"
        @back="goBack"
        class="Header"
    >
      <template #extra>
        <a-button key="1" @click="save">Save</a-button>
      </template>
      <a-descriptions size="small" :column="3">
        <a-descriptions-item label="Author">
          <a-input v-model:value="articleInformation.author" placeholder="Author"/>
        </a-descriptions-item>
        <a-descriptions-item label="Description">
          <a-input v-model:value="articleInformation.description" placeholder="Description" auto-size/>
        </a-descriptions-item>
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
    <a-form
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="on"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
    >
      <a-form-item
          label="Title"
          name="title"
          :rules="[{ required: true, message: 'Your title!' }]"
      >
        <a-input v-model:value="article.title"/>
      </a-form-item>

      <a-form-item
          label="Author"
          name="author"
          :rules="[{ required: true, message: 'Your author!' }]"
      >
        <a-input v-model:value="article.author"/>
      </a-form-item>

      <a-form-item
          label="Description"
          name="description"
          :rules="[{ required: true, message: 'Your description!' }]"
      >
        <a-input v-model:value="article.description"/>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="submit">Submit</a-button>
      </a-form-item>
    </a-form>
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