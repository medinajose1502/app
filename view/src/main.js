import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Footer from '@/components/Footer.vue';
import NavBarA from '@/components/NavBarA.vue';
import NavBarS from '@/components/NavBarS.vue';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'nprogress/nprogress.css'
import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSimpleAlert);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.component('NavBarA', NavBarA)
Vue.component('NavBarS', NavBarS)
Vue.component('Footer', Footer)

Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
