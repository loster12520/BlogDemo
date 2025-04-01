<template>
  <div class="article-add">
    <el-card>
      <template #header>
        <h2>新建文章</h2>
      </template>

      <el-form ref="formRef" :model="form" label-width="80px" :rules="rules">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题"/>
        </el-form-item>

        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者名称"/>
        </el-form-item>

        <el-form-item label="描述" prop="description">
          <el-input
              v-model="form.description"
              type="textarea"
              :rows="3"
              placeholder="请输入文章摘要描述"
          />
        </el-form-item>

        <el-form-item label="内容" prop="content">
          <div class="editor-container">
            <el-tabs type="border-card">
              <el-tab-pane label="编辑">
                <el-input
                    v-model="form.content"
                    type="textarea"
                    :rows="18"
                    placeholder="输入Markdown内容"
                    @input="handleContentChange"
                />
              </el-tab-pane>
              <el-tab-pane label="预览">
                <article class="markdown-body" v-html="previewHtml"/>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">立即创建</el-button>
          <el-button @click="router.go(-1)">取消返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {addArticleDetail, addArticleInfo} from '@/api/article'
import {ElMessage} from 'element-plus'
import {useRouter} from "vue-router";

const router = useRouter()
const GITHUB_MARKDOWN_API = 'https://api.github.com/markdown'
const MARKDOWN_CSS_URL = 'https://cdn.jsdelivr.net/npm/github-markdown-css@5.1.0/github-markdown-light.css'

const formRef = ref(null)
const form = ref({
  title: '',
  author: '管理员',
  description: '',
  content: ''
})

const previewHtml = ref('')
const loadingPreview = ref(false)

const rules = {
  title: [{required: true, message: '请输入文章标题', trigger: 'blur'}],
  author: [{required: true, message: '请输入作者名称', trigger: 'blur'}],
  description: [{required: true, message: '请输入文章描述', trigger: 'blur'}],
  content: [{required: true, message: '请输入文章内容', trigger: 'blur'}]
}

const handleContentChange = async () => {
  try {
    loadingPreview.value = true
    const response = await fetch(GITHUB_MARKDOWN_API, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        text: form.value.content,
        mode: 'markdown'
      })
    })
    previewHtml.value = await response.text()
  } catch (error) {
    ElMessage.error('预览渲染失败: ' + error.message)
  } finally {
    loadingPreview.value = false
  }
}

const submitForm = async () => {
  try {
    await formRef.value.validate()

    // 提交基础信息
    const {data: article} = await addArticleInfo(
        {
          title: form.value.title,
          author: form.value.author,
          description: form.value.description,
        }
    )

    // 提交Markdown内容
    await addArticleDetail(
        {
          articleInformationId: article.id,
          md: form.value.content,
          html: previewHtml.value
        }
    )

    ElMessage.success('创建成功')
    formRef.value.resetFields()
    setTimeout(() => {
      router.push('/article-list')
    }, 1500)
  } catch (error) {
    console.error('提交失败:', error)
    ElMessage.error('提交失败: ' + error.message)
  }
}

// 动态加载GitHub Markdown CSS
onMounted(() => {
  const link = document.createElement('link')
  link.rel = 'stylesheet'
  link.href = MARKDOWN_CSS_URL

  link.onload = () => console.log('Markdown样式加载成功')
  link.onerror = (err) => {
    console.error('样式加载失败:', err)
    ElMessage.error('Markdown样式加载失败，请检查网络连接')
  }

  document.head.appendChild(link)
})
</script>

<style scoped>
.editor-container {
  margin: 20px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
}

.markdown-body {
  padding: 24px;
  max-width: 800px;
  margin: 0 auto;
}

.article-add {
  max-width: 800px;
  margin: 20px auto;
}
</style>