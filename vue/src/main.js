import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {router,getThis} from "@/router"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css'
import request from "@/utils/request"
import store from './store'

Vue.config.productionTip = false
Vue.use(VueRouter)
//使用element-ui，设置大小和默认主题色
Vue.use(ElementUI,{size:"small",theme:'normal'})
//设置配置后的axios
Vue.prototype.request = request

const v = new Vue({
  render: h => h(App),
  router,
  store,
  //安装事件总线
  beforeCreate() {
    Vue.prototype.$bus = this
  }
}).$mount('#app')

getThis(v)