import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
        path: '/',
        redirect: '/article-list'
      },
    {
      path: '/article-list',
      name: 'ArticleList',
      component: () => import('@/views/ArticleListView.vue')
    },
    {
      path: '/add-article',
      name: 'ArticleAdd',
      component: () => import('@/views/ArticleAddView.vue')
    },
    {
      path: '/article/:id',
      name: 'ArticleDetail',
      component: () => import('@/views/ArticleDetailView.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/LoginView.vue')
    }
  ]
})