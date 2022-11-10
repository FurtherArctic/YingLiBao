import {createRouter, createWebHistory} from 'vue-router'

import IndexView from "@/views/IndexView";

const routes = [
    {
        path: '/',
        name: 'IndexView',
        component: IndexView
    },
    // {
    //     path: '/about',
    //     name: 'about',
    //     // route level code-splitting
    //     // this generates a separate chunk (about.[hash].js) for this route
    //     // which is lazy-loaded when the route is visited.
    //     component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    // },
    {
        path: '/product/detail',
        name: 'ProductDetail',
        component: () => import('../views/ProductDetailView')
    },
    {
        path: '/product/list',
        name: 'ProductList',
        component: () => import('../views/ProductListView')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
