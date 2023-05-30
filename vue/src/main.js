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

//设置全局快捷键
document.addEventListener('keydown', (event) => {
    //1.打开搜索栏
    if (event.altKey && event.key === 's') {
        v.$bus.$emit('openSearch')
    }

    //2.调节音量
    if (event.ctrlKey && event.key === 'ArrowUp') {
        v.$bus.$emit('changeVolume', true)
    }
    if (event.ctrlKey && event.key === 'ArrowDown') {
        v.$bus.$emit('changeVolume', false)
    }
    if (event.ctrlKey && event.key === 'm') {
        v.$bus.$emit('changeVolume')
    }
    //3.播放或暂停
    if(event.key === ' '){
        v.$bus.$emit('changePlay')
    }
})


//点击其他区域关闭菜单
document.addEventListener('click', e => {
    e.stopPropagation();
    const menu = document.querySelector('.menu');
    if (!menu.contains(e.target)) {
        v.$bus.$emit('showMenu', 0, 0, false, {}, '')
    }
})


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