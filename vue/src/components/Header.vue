<template>
  <el-header ref="header" class="header" :class="{ 'not-top': !isTop }" style="width: calc(100% - 250px)">
    <!--前进后退按钮-->
    <span class="navigation" @click="goBack" :class="{disabled: $store.state.index===0}">
      <span class="el-icon-arrow-left" style="font-weight: 1000;color: var(--headerText)"/>
    </span>
    <span class="navigation" @click="goForward" v-if="$store.state.index !== $store.state.visitedRoutes.length-1">
      <span class="el-icon-arrow-right" style="font-weight: 1000;color: var(--headerText)"/>
    </span>
    <!--标签页-->
    <el-tabs @tab-click="handleClick" v-model="activeTab" style="line-height: 50px;margin-left: 20px">
      <el-tab-pane v-if="activeTab === '/home'|| activeTab === '/home/recent'" label="推荐" name="/home"/>
      <el-tab-pane v-if="activeTab === '/home'|| activeTab === '/home/recent'" label="最新上传" name="/home/recent"/>
    </el-tabs>
    <!--假搜索框-->
    <span style="width: 240px;height: 40px;background-color: var(--headerSearchBg);
                margin: auto 0 auto auto;border-radius: 20px;
                ;font-size: 15px;cursor: pointer;color: var(--headerText)"
          @click="openD">
      <span class="el-icon-search" style="margin: 0 10px 0 20px;line-height: 40px"/>搜索
    </span>
    <!--    <el-tooltip class="item" effect="dark" content="项目地址" placement="bottom">-->
    <!--      <a class="el-dropdown-link" id="icon" href="https://gitee.com/YoungTrendFar/Project" target="_blank">-->
    <!--        <span class="el-icon-tickets"/>-->
    <!--      </a>-->
    <!--    </el-tooltip>-->
    <span class="el-dropdown-link" id="icon" @click="changeTheme">
        <span :class="icon"/>
      </span>

  </el-header>
</template>

<script>
import 'animate.css'
import router from "@/router";

export default {
  name: "Header",
  data() {
    return {
      //当前图标
      currentIcon: this.$store.state.theme,
      //当前响应的标签页
      activeTab: this.$router.currentRoute.fullPath,
      isTop: true,
    }
  },
  computed: {
    icon() {
      return this.currentIcon === 'Dark' ? 'el-icon-moon' : 'el-icon-sunny'
    }
  },
  methods: {
    //前进后退按钮方法
    goBack() {
      this.$store.commit("goBack")
    },
    goForward() {
      this.$store.commit("goForward")
    },

    //修改主题
    changeTheme() {
      const theme = this.$store.state.theme
      let themeName
      if (theme === 'Dark') {
        themeName = 'Light'
        this.$bus.$emit('changeIcon', themeName)
      } else {
        themeName = 'Dark'
        this.$bus.$emit('changeIcon', themeName)
      }
      this.$store.commit('setTheme', themeName)
      localStorage.setItem('theme', themeName)
      this.$bus.$emit('changeTheme', themeName)
    },
    //标签页点击事件
    handleClick(option) {
      if (router.currentRoute.fullPath !== option.name) {
        router.push(option.name)
      }
    },

    //监听滚动条事件
    handleScroll(e) {
      // console.log(e.srcElement._prevClass)
      if(e.srcElement._prevClass !== undefined && e.srcElement._prevClass !== 'secondMenu' && e.srcElement._prevClass !== 'content'){
        this.isTop = e.target.scrollTop <= 60;
      }
    },
    openD() {
      this.$bus.$emit('openSearch', true)
    },

  },
  mounted() {
    this.$bus.$on('changeActiveTab', (path) => {
      this.activeTab = path
    })
    this.$bus.$on('changeIcon', (icon) => {
      this.currentIcon = icon
    })
    //监听滚动条事件
    window.addEventListener('scroll', this.handleScroll, true);

  }
  ,
  beforeDestroy() {
    this.$bus.$off('changeActiveTab')
    this.$bus.$off('changeIcon')
    window.removeEventListener('scroll', this.handleScroll)
    this.$bus.$off('openSearch')
  }
}
</script>


<style scoped>

/*header不在顶部的样式*/
.not-top {
  backdrop-filter: blur(25px);
  background-color: var(--headerBgOp) !important;
  border-bottom: 1px var(--footerHover) solid !important;
}

.header {
  position: fixed;
  z-index: 1;
  height: 60px;
  display: flex;
  font-size: 20px;
  background-color: var(--headerBg);
  border-bottom: solid 1px rgba(64, 64, 64, 0);
  width: 100%;
  padding-right: 18px;
}

.navigation {
  border-radius: 50%;
  background-color: var(--headerBtn);
  width: 26px;
  height: 38px;
  margin: 10px 8px 10px 0;
  line-height: 40px;
  font-size: 15px;
  padding-left: 12px;
  cursor: pointer;
}

.navigation:hover {
  background-color: var(--headerBtnHover);
}

.disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

/deep/ .el-dialog {
  background-color: var(--dialogBg);
  border-radius: 10px;
  height: 450px;
  width: 500px;
}

/deep/ .el-dialog__body {
  padding: 0;
}

/deep/ .el-dialog__header {
  height: 0;
  padding: 0;
}

/deep/ .el-input__inner {
  background-color: rgba(0, 0, 0, 0);
  color: var(--headerInputText) !important;
  border: none;
}

#icon {
  font-size: 20px;
  border-radius: 50%;
  background-color: var(--headerBtn);
  color: var(--headerText);
  padding: 0 10px 20px 10px;
  line-height: 40px;
  margin-left: 5px;
  margin-top: 10px;
  height: 20px;
  width: 20px;
  cursor: pointer;
  transition: 0.2s;
}

#icon:hover {
  background-color: var(--headerBtnHover);
}

/*修改el-tabs*/
::v-deep .el-tabs__item {
  font-weight: bold;
  color: var(--headerTabText);
  padding: 0 15px;
}

::v-deep .el-tabs__item.is-active {
  color: var(--headerTabHover);
}

::v-deep .el-tabs__nav-wrap::after {
  height: 0;
}

::v-deep .el-tabs__active-bar {
  background-color: var(--headerTabHover);
}

/deep/ .el-input__inner::placeholder {
  color: var(--headerInputText);
}

/deep/ .el-tabs__nav {
  position: static;
}

/deep/ .el-dialog__wrapper {
  transition-duration: 0.3s;
}

/deep/ .dialog-fade-enter-active {
  animation: none !important;
}

/deep/ .dialog-fade-leave-active {
  transition-duration: 0.15s !important;
  animation: none !important;
}

/deep/ .dialog-fade-enter-active .el-dialog,
.dialog-fade-leave-active .el-dialog {
  animation-fill-mode: forwards;
}

/deep/ .dialog-fade-enter-active .el-dialog {
  animation-duration: 0.3s;
  animation-name: anim-open;
  animation-timing-function: cubic-bezier(0.6, 0, 0.4, 1);
}

/deep/ .dialog-fade-leave-active .el-dialog {
  animation-duration: 0.3s;
  animation-name: anim-close;
}


@keyframes anim-open {
  0% {
    opacity: 0;
    transform: scale3d(0, 0, 1);
  }
  100% {
    opacity: 1;
    transform: scale3d(1, 1, 1);
  }
}


@keyframes anim-close {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: scale3d(0.5, 0.5, 1);
  }
}

.keys {
  letter-spacing: normal;
  color: var(--settingLightText);
  border: #555555 solid;
  border-width: 1px 1px 2px 1px;
  padding: 2px 4px;
  border-radius: 5px;
  font-size: 12px;
  font-weight: bold;
  margin-right: 5px;
  background-color: #111111;
  margin-left: 5px;
}

.word {
  font-size: 10px;
  letter-spacing: normal;
  color: var(--headerInputText);
  font-weight: bold;
}

.searchItem {
  margin: 10px 30px 10px 0;
  cursor: pointer;
  padding: 5px 10px;
  border-radius: 3px;
}

.searchItem:hover {
  background-color: var(--searchHover);
}

.deleteIcon {
  font-size: 17px;
  cursor: pointer;
  padding: 8px;
  border-radius: 3px;
}

.deleteIcon:hover {
  background-color: var(--searchHover);
}
</style>