<template>
  <el-aside width="250px" style="height: 100%;overflow-x: hidden">
    <el-menu :default-openeds="['1', '3']"
             style="min-height: 100%; overflow-x: hidden;background-color: var(--color-primary);border: none"
             :collapse-transition="false"
             router
             ref="menu"
             :default-active="$route.path"
    >
      <!--头像和登录键-->
      <div style="height: 40px; line-height: 40px; text-align: center;margin: 32px 12px 20px" @click="login">
        <span><el-avatar :size="40"/></span>
        <span style="float: right;cursor: pointer;font-weight: 700;margin-left: 12px" class="clickToLogin">
          点击登录
          <span class="el-icon-arrow-right" style="margin-left: 85px;font-weight: 800"/>
        </span>
      </div>
      <el-menu-item index="/home" class="leftSection">
        <i class="el-icon-s-home" style="font-size: 15px;color: var(--text);margin-right: 10px"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="/setting" class="leftSection">
        <i class="el-icon-s-tools" style="font-size: 15px;color: var(--text);margin-right: 10px"></i>
        <span slot="title">设置</span>
      </el-menu-item>
      <el-menu-item index="/mine" class="leftSection">
        <i class="el-icon-s-custom" style="font-size: 15px;color: var(--text);margin-right: 10px"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: "LeftAside",
  data() {
    return {}
  },
  methods:{
    login(){
      this.$router.push('/login')
    }
  },
}
</script>

<style scoped>
*{
  color: var(--color-text)
}
/*点击登录悬浮*/
.clickToLogin:hover .el-icon-arrow-right,.clickToLogin:hover{
  color: RGB(60, 126, 255);
}
.clickToLogin *,.clickToLogin{
  transition: 0.2s;
}
/*选项*/
.leftSection{
  height: 40px;
  border-radius: 3px;
  margin: 4px 8px;
  line-height: 15px;
  padding: 10px!important;
  font-weight: 600;
}
.leftSection:hover{
  background-color: #666666;
}
/*响应元素*/
.leftSection.is-active {
  background-color: var(--active)!important;
}
</style>