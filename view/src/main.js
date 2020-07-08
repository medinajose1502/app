import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Footer from '@/components/Footer.vue';
import NavBar from '@/components/NavBar.vue';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'nprogress/nprogress.css'



Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.component('NavBar', NavBar)
Vue.component('Footer', Footer)

Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
