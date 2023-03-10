<template>
  <el-aside width="250px" style="height: 100%;overflow-x: hidden">
    <el-menu :default-openeds="['1', '3']"
             style="min-height: 100%; overflow-x: hidden"
             active-text-color="#FFD04B"
             active-background-color="#FFFFFF"
             :collapse-transition="false"
             router
    >
      <!--头像和登录键-->
      <div style="height: 60px; line-height: 60px; text-align: center;margin-top: 20px;background-color: coral">
        <span><el-avatar :size="40" style="margin-top: 10px"/></span>
        <span style="float: right">点击登录<span class="el-icon-arrow-right" style="margin-left: 90px;margin-right: 10px"/></span>
      </div>

      <el-menu-item index="/home">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item index="/user">
          <template slot="title"><i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/file">
          <template slot="title"><i class="el-icon-document"></i>
            <span slot="title">文件管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/role">
          <template slot="title"><i class="el-icon-postcard"></i>
            <span slot="title">角色管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/menus">
          <template slot="title"><i class="el-icon-set-up"></i>
            <span slot="title">菜单管理</span>
          </template>
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="/mine">
        <i class="el-icon-s-custom"></i>
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
  computed: {}
}
</script>

<style scoped>

</style>