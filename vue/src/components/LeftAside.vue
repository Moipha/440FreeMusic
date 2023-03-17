<template>
  <el-aside width="250px" style="height: 100%;overflow-x: hidden">
    <el-menu :default-openeds="['1', '3']"
             style="min-height: 100%; overflow-x: hidden;background-color: var(--leftbg);border: none"
             :collapse-transition="false"
             router
             ref="menu"
             :default-active="$route.path !== '/home/recent' ? $route.path : '/home'"
    >
      <!--头像和登录键-->
      <div style="height: 40px; line-height: 40px;margin: 32px 12px 20px 15px" @click="login">
        <span><el-avatar :size="40" :src="defaultUrl">{{ initials }}</el-avatar></span>
        <span style="float: right;cursor: pointer;font-weight: 700;margin-left: 12px" class="clickToLogin">
          {{ username }}
          <span class="el-icon-arrow-right" style="margin-left: 80px;font-weight: 800"/>
        </span>
      </div>
      <el-menu-item index="/home" class="leftSection">
        <i class="el-icon-s-home leftSectionInner"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="/setting" class="leftSection">
        <i class="el-icon-s-tools leftSectionInner"></i>
        <span slot="title">设置</span>
      </el-menu-item>
      <el-menu-item index="/likes" class="leftSection">
        <i class="el-icon-star-on leftSectionInner"></i>
        <span slot="title">收藏</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: "LeftAside",
  data() {
    return {
      username: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).username.toUpperCase() : '点击登录'
    }
  },
  methods: {
    login() {
      //如果已登录的话跳转至个人中心
      if (localStorage.getItem('user') !== null) {
        //如果当前页面不是个人中心则跳转
        if (this.$router.currentRoute.fullPath !== '/mine') {
          this.$router.push('/mine')
        }
      } else {
        //如果当前页面不是login的话才进行跳转
        if (this.$router.currentRoute.fullPath !== '/login') {
          this.$router.push('/login')
        }
      }
    }
  },
  computed: {
    initials() {
      if (!this.username) {
        return ''
      }
      const firstChar = this.username.charAt(0)
      if (firstChar.match(/[a-zA-Z]/)) {
        return firstChar.toUpperCase()
      }
      return ''
    },
    defaultUrl() {
      if (this.username === '点击登录') {
        return require('@/assets/avatar.png')
      } else {
        return ''
      }
    }
  },
  mounted() {
    this.$bus.$on('logout', () => {
      this.username = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).username.toUpperCase() : '点击登录'
    })
  },
  beforeDestroy() {
    this.$bus.$off('logout')
  }
}
</script>

<style scoped>
* {
  color: var(--leftText)
}

/*点击登录悬浮*/
.clickToLogin:hover .el-icon-arrow-right, .clickToLogin:hover {
  color: var(--leftBtnActive);
}

.clickToLogin *, .clickToLogin {
  transition: 0.2s;
}

/*选项*/
.leftSection {
  height: 40px;
  border-radius: 3px;
  margin: 4px 8px;
  line-height: 15px;
  padding: 10px !important;
  font-weight: 600;
}

.leftSection:hover {
  background-color: var(--leftBtnHover);
}

/*响应元素*/
.leftSection.is-active {
  background-color: var(--leftBtnActive) !important;
  color: var(--leftText);
}

.el-avatar {
  background-color: var(--whiteText);
  color: var(--blackText);
  font-size: 20px;
  line-height: 40px;
}

.leftSectionInner {
  font-size: 15px;
  color: var(--whiteText);
  margin-right: 10px
}

</style>