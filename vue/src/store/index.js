import Vuex from 'vuex'
import Vue from 'vue';
//使用插件
Vue.use(Vuex)
//actions，用于响应组件中的动作
const actions = {}
//准备mutations，用于操作state中的数据
const mutations = {
    //设置主题色
    setTheme(state,theme){
        state.theme = theme
    }
}
//准备state，用于存放数据
const state = {
    //目前主题色
    theme: 'black'
}
//创建并导出store
export default new Vuex.Store({
    actions,
    mutations,
    state,
})