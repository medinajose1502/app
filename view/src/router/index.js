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
import NProgress from 'nprogress'
import store from '@/store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    alias: '/',
    component: Login
  },
  {
    path: '/logout',
    name: 'Logout',
  },
  {
    path: '/secretario',
    name: 'SecretarioHome',
    component: SecretarioHome,
    meta: { requiereSecretario: true }
  },
  {
    path: '/secretario/acta/:id',
    name: 'SecretarioVerActa',
    component: SecretarioVerActa,
    props: true,
    meta: { requiereSecretario: true }
  },
  {
    path: '/secretario/crear',
    name: 'SecretarioCrearActa',
    component: SecretarioCrearActa,
    meta: { requiereSecretario: true }
  },
  {
    path: '/secretario/acta/editar/:id',
    name: 'SecretarioEditarActa',
    component: SecretarioEditarActa,
    props: true,
    meta: { requiereSecretario: true }
  },
  {
    path: '/admin',
    name: 'AdminHome',
    component: AdminHome,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/acta/ver/:id',
    name: 'AdminVerActa',
    component: AdminVerActa,
    props: true,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/acta/crear',
    name: 'AdminCrearActa',
    component: AdminCrearActa,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/acta/editar/:id',
    name: 'AdminEditarActa',
    component: AdminEditarActa,
    props: true,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/decanato/ver/:id',
    name: 'AdminVerDecanato',
    component: AdminVerDecanato,
    props: true,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/decanato/crear',
    name: 'AdminCrearDecanato',
    component: AdminCrearDecanato,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/decanato/editar/:id',
    name: 'AdminEditarDecanato',
    component: AdminEditarDecanato,
    props: true,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/usuario/ver/:id',
    name: 'AdminVerUsuario',
    component: AdminVerUsuario,
    props: true,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/usuario/crear',
    name: 'AdminCrearUsuario',
    component: AdminCrearUsuario,
    meta: { requiereAdmin: true }
  },
  {
    path: '/admin/usuario/editar/:id',
    name: 'AdminEditarUsuario',
    component: AdminEditarUsuario,
    props: true,
    meta: { requiereAdmin: true }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const usuarioSesionVacio = {
  id: "",
  cedula: "",
  nombres: "",
  apellidos: "",
  estado: "",
  roles: [],
  actas: []
}

export default router
