import Vuex from 'vuex'
import Vue from 'vue';
import router from "@/router";
//使用插件
Vue.use(Vuex)
//actions，用于响应组件中的动作
const actions = {}
//准备state，用于存放数据
const state = {
    //目前主题色
    theme: localStorage.getItem('theme') ? localStorage.getItem('theme').toString() : 'dark',
    //访问过的路由数组
    visitedRoutes: [],
    //当前索引
    index: -1,
    //是否需要通过路由守卫
    need: true,
}
//准备mutations，用于操作state中的数据
const mutations = {
    //设置主题色
    setTheme(state, theme) {
        state.theme = theme
    },
    //在数组中新增元素
    addVisited(state,path){
        //只要新增元素，删除索引所在位置之后的所有元素，然后添加新元素
        state.visitedRoutes.splice(state.index + 1)
        state.visitedRoutes.push(path)
        //最后索引移至最后一位
        state.index++
    },
    //前进
    goForward(state){
        //只要索引不是最右端即可前进一位
        if(state.index!==state.visitedRoutes.length-1){
            //前进和后退不通过路由守卫
            state.need = false
            state.index++
            router.forward()
        }
    },
    //后退
    goBack(state){
        //只要索引不是最左端即可后退一位
        if(state.index!==0){
            //前进和后退不通过路由守卫
            state.need = false
            state.index--
            router.back()
        }
    }
}
//创建并导出store
export default new Vuex.Store({
    actions,
    mutations,
    state,
})