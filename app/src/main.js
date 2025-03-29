import {createApp} from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import {createRouter, createWebHistory} from "vue-router";
import ArticleList from "@/views/ArticleList.vue";

const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('./views/Login.vue')
    },
    {
        path: '/',
        name: 'list',
        component: ArticleList,
        meta: {requiresAuth: true}
    },
    {
        path: '/user/profile',
        name: 'profile',
        component: () => import('./views/UserProfile.vue'),
        meta: {requiresAuth: true}
    },
    {
        path: '/article/:id',
        name: 'article',
        component: () => import('./views/ArticleDetail.vue'),
        meta: {requiresAuth: true}
    },
    {
        path: '/article/edit/:id',
        name: 'edit',
        component: () => import('./views/ArticleEdit.vue'),
        meta: {requiresAuth: true}
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})
router.beforeEach((to, from, next) => {
    const isAuthenticated = localStorage.getItem('token');
    if (to.meta.requiresAuth && !isAuthenticated) {
        next('/login');
    } else {
        next();
    }
});

createApp(App)
    .use(router)
    .use(Antd)
    .mount('#app')
