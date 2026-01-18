import {createRouter, createWebHashHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AddCategory from "@/views/Category/AddCategory.vue";
import ListCategories from "@/views/Category/ListCategories.vue";
import EditCategory from "@/views/Category/EditCategory.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },

    {
        path: '/admin/category/add',
        name: 'AddCategory',
        component: AddCategory
    },

    {
        path: '/admin/category/',
        name: 'ListCategories',
        component: ListCategories
    },

    {
        path: '/admin/category/:id',
        name: 'EditCategory',
        component: EditCategory
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
