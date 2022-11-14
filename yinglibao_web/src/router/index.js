import {createRouter, createWebHistory} from 'vue-router'
import IndexView from "@/views/IndexView";

const routes = [
    {
        path: '/',
        name: 'IndexView',
        component: IndexView
    },
    {
        path: '/product/detail',
        name: 'ProductDetail',
        component: () => import('../views/ProductDetailView')
    },
    {
        path: '/product/list',
        name: 'ProductList',
        component: () => import('../views/ProductListView')
    },
    {
        path: '/user/register',
        name: 'RegisterView',
        component: () => import('../views/RegisterView')
    },
    {
        path: '/user/login',
        name: 'LoginView',
        component: () => import('../views/LoginView')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
