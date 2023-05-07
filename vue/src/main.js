import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {router, getThis} from "@/router"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css'
import request from "@/utils/request"
import store from './store'

//剪切板
import VueClipboards from 'vue-clipboard2'

Vue.use(VueClipboards);
//解决跳转爆红问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.config.productionTip = false
Vue.use(VueRouter)
//使用element-ui，设置大小和默认主题色
Vue.use(ElementUI, {size: "small", theme: 'normal'})
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