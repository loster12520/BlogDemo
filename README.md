### 简介

这是一个blog项目，目前仍在施工中喵

---

### 实现功能

- 后端
    - [x] 文章列表
    - [x] 文档预览
    - [x] 文档编辑
    - [x] 登录
    - [ ] 个人文章管理
    - [ ] 数据分析
    - [ ] 文章推送
    - [ ] 点赞、收藏、评论、转发
    - [ ] 文章分类、标签
- 前端
    - [x] 文章列表
    - [x] 文档预览
    - [x] 文档编辑
    - [x] 登录
    - [ ] 个人文章管理
    - [ ] 数据分析
    - [ ] 文章推送
    - [ ] 点赞、收藏、评论、转发
    - [ ] 文章分类、标签

---

### 技术栈

- 前端
    1. vue
    2. vue-router
    3. axios
    4. ant design vue
- 后端
    1. kotlin
    2. springboot
    3. gradle-kotlin
    4. mysql
    5. jpa
    6. redis
    7. shiro
    8. springdoc openapi with swagger

---

### 如何使用

1. 使用git clone项目，导入idea中，等待gradle下载好包（已经配置好国内镜像源，所以理论上不会很慢），创建
   `api/src/main/resources/application.properties`这个文件，根据你的需要配置信息，大概需要以下内容：

```properties
spring.application.name=api
spring.datasource.username=<your username>
spring.datasource.password=<your password>
spring.datasource.url=<your mysql url>/<your database name>
```

运行`api\src\main\kotlin\com\lignting\api\ApiApplication.kt`这个文件，启动springboot项目

2. 打开终端，运行以下指令

```shell
cd app
npm install
npm run dev
```

根据终端的提示，进入对应网页进行操作

---

### 感谢

- [shimh-develop](https://github.com/shimh-develop/)
  提供的[blog-vue-springboot](https://github.com/shimh-develop/blog-vue-springboot)项目作为参照进行开发
- [Github](https://github.com/)
  提供的[markdown渲染服务](https://docs.github.com/en/free-pro-team@latest/rest/reference/markdown)
- [slydor](https://github.com/slydor)提供的[github-markdown-css](https://github.com/sindresorhus/github-markdown-css)
  进行类似Github的markdown渲染样式
- [Deepseek](https://chat.deepseek.com/)以及[Kimi](https://kimi.moonshot.cn/)提供的ai问答服务
