import {createApp} from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import {createRouter, createWebHistory} from "vue-router";
import ArtcileList from "@/views/ArtcileList.vue";

const routes = [
    {
        path: '/',
        name: 'list',
        component: ArtcileList
    },
    {
        path: '/article/:id',
        name: 'article',
        component: () => import('./views/ArticleDetail.vue')
    },
    {
        path: '/article/edit/:id',
        name: 'edit',
        component: () => import('./views/ArticleEdit.vue')
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})
createApp(App)
    .use(router)
    .use(Antd)
    .mount('#app')
