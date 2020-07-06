import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue';
import AdminHome from '@/views/admin/AdminHome.vue';
import AdminVerActa from '@/views/admin/AdminVerActa.vue';
import AdminCrearActa from '@/views/admin/AdminCrearActa.vue';
import AdminEditarActa from '@/views/admin/AdminEditarActa.vue';
import AdminVerDecanato from '@/views/admin/AdminVerDecanato.vue';
import AdminCrearDecanato from '@/views/admin/AdminCrearDecanato.vue';
import AdminEditarDecanato from '@/views/admin/AdminEditarDecanato.vue';
import AdminVerUsuario from '@/views/admin/AdminVerUsuario.vue';
import AdminCrearUsuario from '@/views/admin/AdminCrearUsuario.vue';
import AdminEditarUsuario from '@/views/admin/AdminEditarUsuario.vue';

import SecretarioHome from '@/views/secretario/SecretarioHome.vue';
import SecretarioVerActa from '@/views/secretario/SecretarioVerActa.vue';
import SecretarioCrearActa from '@/views/secretario/SecretarioCrearActa.vue';
import SecretarioEditarActa from '@/views/secretario/SecretarioEditarActa.vue';
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    alias: '/',
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
  {
    path: '/secretario/acta/editar/:id',
    name: 'SecretarioEditarActa',
    component: SecretarioEditarActa,
    props: true
  },
  {
    path: '/admin',
    name: 'AdminHome',
    component: AdminHome
  },
  {
    path: '/admin/acta/:id',
    name: 'AdminVerActa',
    component: AdminVerActa,
    props: true
  },
  {
    path: '/admin/acta/crear',
    name: 'AdminCrearActa',
    component: AdminCrearActa,
  },
  {
    path: '/admin/acta/editar/:id',
    name: 'AdminEditarActa',
    component: AdminEditarActa,
    props: true
  },
  {
    path: '/admin/decanato/:id',
    name: 'AdminVerDecanato',
    component: AdminVerDecanato,
    props: true
  },
  {
    path: '/admin/decanato/crear',
    name: 'AdminCrearDecanato',
    component: AdminCrearDecanato,
  },
  {
    path: '/admin/decanato/editar/:id',
    name: 'AdminEditarDecanato',
    component: AdminEditarDecanato,
    props: true
  },
  {
    path: '/admin/usuario/:id',
    name: 'AdminVerUsuario',
    component: AdminVerUsuario,
    props: true
  },
  {
    path: '/admin/usuario/crear',
    name: 'AdminCrearUsuario',
    component: AdminCrearUsuario,
  },
  {
    path: '/admin/usuario/editar/:id',
    name: 'AdminEditarUsuario',
    component: AdminEditarUsuario,
    props: true
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
