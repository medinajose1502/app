import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue';
import SecretarioHome from '@/views/SecretarioHome.vue';
import SecretarioVerActa from '@/views/SecretarioVerActa.vue';
import SecretarioCrearActa from '@/views/SecretarioCrearActa.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/secretario',
    name: 'SecretarioHome',
    component: SecretarioHome
  },
  {
    path: '/secretario/acta/:id',
    name: 'SecretarioVerActa',
    component: SecretarioVerActa,
    props: true
  },
  {
    path: '/secretario/crear',
    name: 'SecretarioCrearActa',
    component: SecretarioCrearActa,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
