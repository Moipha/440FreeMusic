"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[856],{4856:function(e,r,t){t.r(r),t.d(r,{default:function(){return u}});var s=function(){var e=this,r=e._self._c;return r("div",[r("div",{staticStyle:{width:"400px",margin:"10% auto",color:"var(--loginText)"}},[r("el-form",{ref:"userForm",attrs:{model:e.user,rules:e.rules}},[r("h1",{staticStyle:{"font-size":"30px",margin:"10px 0"}},[e._v("注册一个账户")]),r("span",{staticStyle:{"font-weight":"600","font-size":"13px"}},[e._v(" 已有账户？ "),r("span",{staticClass:"register",staticStyle:{cursor:"pointer",color:"var(--loginA)",padding:"2px 5px 5px 5px","border-radius":"5px"},on:{click:function(r){return e.$router.push("/login")}}},[e._v(" 直接登录 ")])]),r("el-form-item",{attrs:{prop:"email"}},[r("el-input",{staticStyle:{"margin-top":"30px","background-color":"var(--loginInputBg)",width:"350px","font-size":"14px"},attrs:{placeholder:" 邮箱","prefix-icon":"el-icon-message",clearable:""},nativeOn:{keydown:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.register.apply(null,arguments)}},model:{value:e.user.email,callback:function(r){e.$set(e.user,"email",r)},expression:"user.email"}})],1),r("el-form-item",{attrs:{prop:"username"}},[r("el-input",{staticStyle:{"margin-top":"20px","background-color":"var(--loginInputBg)",width:"350px","font-size":"14px"},attrs:{placeholder:" 用户名","prefix-icon":"el-icon-user",clearable:"",maxlength:"20","show-word-limit":""},nativeOn:{keydown:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.register.apply(null,arguments)}},model:{value:e.user.username,callback:function(r){e.$set(e.user,"username",r)},expression:"user.username"}})],1),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{staticStyle:{"margin-top":"20px","background-color":"var(--loginInputBg)",width:"350px","font-size":"14px"},attrs:{placeholder:" 密码","prefix-icon":"el-icon-lock","show-password":"",clearable:""},nativeOn:{keydown:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.register.apply(null,arguments)}},model:{value:e.user.password,callback:function(r){e.$set(e.user,"password",r)},expression:"user.password"}})],1),r("el-button",{staticStyle:{"margin-top":"20px",width:"350px",height:"40px","background-color":"var(--loginA)",border:"none",color:"var(--btnText)","font-weight":"bold","font-size":"14px",transition:"0.4s"},on:{click:e.register}},[e._v(" 立即注册 ")])],1)],1)])},n=[],i={name:"Register",data(){return{user:{},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:1,max:20,message:"长度在 1 到 20 个字符",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:6,max:20,message:"长度在 6 到 20 个字符",trigger:"blur"}],email:[{required:!0,message:"请输入邮箱",trigger:"blur"},{min:1,max:20,message:"长度在 1 到 20 个字符",trigger:"blur"}]}}},methods:{register(){this.$refs["userForm"].validate((e=>{e&&this.request.post("/user/register",this.user).then((e=>{"200"!==e.code?this.$notify({message:e.msg,type:"error",title:"注册失败"}):(this.$router.push("/login"),this.$notify({title:"成功",message:"注册成功",type:"success"}))}))}))}}},o=i,l=t(1001),a=(0,l.Z)(o,s,n,!1,null,"7dbf0570",null),u=a.exports}}]);
//# sourceMappingURL=856.edcb3ba8.js.map