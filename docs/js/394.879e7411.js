"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[394],{1394:function(t,e,i){i.r(e),i.d(e,{default:function(){return o}});var s=function(){var t=this,e=t._self._c;return e("div",[t.$route.path.endsWith("recent")?t._e():e("div",{staticClass:"container"},[e("div",[t._m(0),e("div",{staticStyle:{display:"flex","flex-direction":"row"}},t._l(t.lists,(function(s){return e("div",{staticStyle:{display:"flex","flex-direction":"column",margin:"auto"}},[e("div",{staticClass:"avatarContainer",on:{click:function(e){return t.jumpToList(s)}}},[e("el-avatar",{staticStyle:{height:"150px",width:"150px",transition:"0.4s","margin-bottom":"-75px"},attrs:{src:s.avatar?s.avatar:i(2043),shape:"square"}}),e("i",{staticClass:"el-icon-video-play avatarIcon"})],1),e("span",{staticStyle:{"font-weight":"bold","font-size":"20px","margin-top":"10px"}},[t._v(t._s(s.title))]),e("span",[t._v("创建于 "+t._s(s.createTime))])])})),0)]),e("el-divider"),e("div",{staticStyle:{display:"flex","flex-direction":"row","justify-content":"space-between"}},[e("div",{staticStyle:{width:"49%"}},[t._m(1),e("table",{staticStyle:{width:"100%"}},[t._m(2),e("tr",{staticStyle:{height:"20px"}}),t._l(t.tableData,(function(i){return e("tr",{staticClass:"items",attrs:{id:i.id===t.currentPlayId?"light":""},on:{contextmenu:function(e){return e.preventDefault(),t.openMenu(e,i)},dblclick:function(e){return t.dbClick(i,t.tableData)}}},[e("td",{staticStyle:{"padding-top":"5px"}},[e("el-avatar",{attrs:{src:i.avatar,shape:"square"}})],1),e("td",[t._v(t._s(i.name))]),e("td",[t._v(t._s(i.author))]),e("td",[t._v(t._s(i.album))]),e("td",[t._v(t._s(i.time))]),e("td",[e("span",{staticClass:"el-icon-more",staticStyle:{cursor:"pointer","font-size":"20px"},on:{click:function(e){return e.stopPropagation(),t.openMenu(e,i)}}})])])}))],2)]),e("div",{staticStyle:{width:"49%"}},[e("h1",{staticStyle:{"font-size":"25px"}},[e("span",{staticStyle:{"margin-right":"10px",cursor:"pointer"},on:{click:function(e){return t.$router.push("/home/recent")}}},[t._v("最新上传")]),e("span",{staticClass:"el-icon-arrow-right",staticStyle:{"font-size":"18px","font-weight":"bold",cursor:"pointer"},on:{click:function(e){return t.$router.push("/home/recent")}}})]),e("table",{staticStyle:{width:"100%"}},[t._m(3),e("tr",{staticStyle:{height:"20px"}}),t._l(t.recentTable,(function(i){return e("tr",{staticClass:"items",attrs:{id:i.id===t.currentPlayId?"light":""},on:{contextmenu:function(e){return e.preventDefault(),t.openMenu(e,i)},dblclick:function(e){return t.dbClick(i,t.recentTable)}}},[e("td",{staticStyle:{"padding-top":"5px"}},[e("el-avatar",{attrs:{src:i.avatar,shape:"square"}})],1),e("td",[t._v(t._s(i.name))]),e("td",[t._v(t._s(i.author))]),e("td",[t._v(t._s(i.album))]),e("td",[t._v(t._s(i.time))]),e("td",[e("span",{staticClass:"el-icon-more",staticStyle:{cursor:"pointer","font-size":"20px"},on:{click:function(e){return e.stopPropagation(),t.openMenu(e,i)}}})])])}))],2)])]),e("el-divider"),t._m(4)],1),e("router-view")],1)},a=[function(){var t=this,e=t._self._c;return e("h1",{staticStyle:{"font-size":"25px"}},[t._v("歌单推荐 "),e("span",{staticClass:"el-icon-present"})])},function(){var t=this,e=t._self._c;return e("h1",{staticStyle:{"font-size":"25px"}},[t._v("单曲推荐 "),e("span",{staticClass:"el-icon-headset"})])},function(){var t=this,e=t._self._c;return e("tr",{staticStyle:{color:"var(--listTh)","font-size":"14px"}},[e("th",{staticStyle:{width:"10%"},attrs:{align:"center"}}),e("th",{staticStyle:{width:"30%"},attrs:{align:"left"}},[t._v("音乐标题")]),e("th",{staticStyle:{width:"20%"},attrs:{align:"left"}},[t._v("歌手")]),e("th",{staticStyle:{width:"20%"},attrs:{align:"left"}},[t._v("专辑")]),e("th",{staticStyle:{width:"10%"},attrs:{align:"left"}},[t._v("时长")]),e("th",{staticStyle:{width:"10%"},attrs:{align:"left"}})])},function(){var t=this,e=t._self._c;return e("tr",{staticStyle:{color:"var(--listTh)","font-size":"14px"}},[e("th",{staticStyle:{width:"10%"},attrs:{align:"center"}}),e("th",{staticStyle:{width:"30%"},attrs:{align:"left"}},[t._v("音乐标题")]),e("th",{staticStyle:{width:"20%"},attrs:{align:"left"}},[t._v("歌手")]),e("th",{staticStyle:{width:"20%"},attrs:{align:"left"}},[t._v("专辑")]),e("th",{staticStyle:{width:"10%"},attrs:{align:"left"}},[t._v("时长")]),e("th",{staticStyle:{width:"10%"},attrs:{align:"left"}})])},function(){var t=this,e=t._self._c;return e("div",{staticStyle:{"margin-bottom":"100px"}},[e("h3",[t._v("更新日志")]),e("h5",[t._v("2023/6/11")]),e("h6",[t._v("基本实现目前所有功能，即将正式上线")]),e("h5",[t._v("2023/6/12")]),e("h6",[t._v("修复了首页推荐歌单少于7个时悬浮图标显示错位的问题；修复了歌单信息修改出错的问题")]),e("h6",[t._v("实现了收藏和歌单页面添加歌曲至播放列表的功能；修正了播放和添加按钮稍微错位的样式错误")]),e("h5",[t._v("2023/11/29")]),e("h6",[t._v("实现了Header根据滚动条位置动态显示下边框和背景模糊效果")]),e("h6",[t._v("修复了部分页面显示不全的错误")]),e("h5",[t._v("2023/12/12")]),e("h6",[t._v("底栏添加隐藏和恢复按钮")]),e("h6",[t._v("修复了未登录状态点击收藏歌曲的500报错")]),e("h5",[t._v("2024/6/14")]),e("h6",[t._v("重置了亮色主题")]),e("h5",[t._v("2024/6/16")]),e("h6",[t._v("删除了未完成的部分")]),e("h6",[t._v("修复了二级弹窗在边缘显示出错的问题")])])}],n={name:"Home",data(){return{lists:[],tableData:[],recentTable:[],existMenu:!1,currentPlayId:-1,loading:!1}},mounted(){this.getTableData(),this.getCurrentMusic(),this.$bus.$on("getCurrentPlayMusic",(()=>{this.getCurrentMusic()}))},methods:{openMenu(t,e){t.x>1200?t.y>500?this.$bus.$emit("showMenu",t.x-230,t.y-200,!0,e,{},this.tableData,"search"):this.$bus.$emit("showMenu",t.x-230,t.y,!0,e,{},this.tableData,"search"):t.y>500?this.$bus.$emit("showMenu",t.x,t.y-230,!0,e,{},this.tableData,"search"):this.$bus.$emit("showMenu",t.x,t.y,!0,e,{},this.tableData,"search"),this.existMenu=!0},getCurrentMusic(){this.currentPlayId=JSON.parse(localStorage.getItem("currentMusic"))?JSON.parse(localStorage.getItem("currentMusic")).id:""},dbClick(t,e){this.$bus.$emit("play",t,e),this.getCurrentMusic()},getTableData(){this.loading=!0,this.request.get("/music/getAll").then((t=>{"200"!==t.code?(this.$notify({title:"查询数据失败",message:t.msg,type:"error"}),this.loading=!1):(t.data.length<=7?this.recentTable=t.data.reverse():this.recentTable=t.data.slice(-7).reverse(),this.loading=!1)})).catch((t=>{this.$notify({title:"查询数据失败",message:t,type:"error"}),this.loading=!1})),this.request.get("/music/getRandom").then((t=>{"200"!==t.code?(this.$notify({title:"查询数据失败",message:t.msg,type:"error"}),this.loading=!1):(this.tableData=t.data,this.loading=!1)})).catch((t=>{this.$notify({title:"查询数据失败",message:t,type:"error"}),this.loading=!1})),this.request.get("/list/getAll").then((t=>{if("200"!==t.code)this.$notify({title:"查询数据失败",message:t.msg,type:"error"}),this.loading=!1;else{const e=[...t.data],i=[];while(i.length<7&&e.length>0){const t=Math.floor(Math.random()*e.length),s=e.splice(t,1)[0];i.push(s)}this.lists=i,this.loading=!1}})).catch((t=>{this.$notify({title:"查询数据失败",message:t,type:"error"}),this.loading=!1}))},jumpToList(t){this.$router.push({path:"/list",query:{listTitle:t.title,listAuthor:t.authorId}}),this.$bus.$emit("changeList")}}},r=n,l=i(1001),c=(0,l.Z)(r,s,a,!1,null,"b75d0bc6",null),o=c.exports},2043:function(t,e,i){t.exports=i.p+"img/DefaultAvatar.e88d641e.png"}}]);
//# sourceMappingURL=394.879e7411.js.map