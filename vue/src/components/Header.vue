<template>
  <el-header ref="header" class="header" style="width: calc(100% - 250px)">
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
    <el-tooltip class="item" effect="dark" content="项目地址" placement="bottom">
      <a class="el-dropdown-link" id="icon" href="https://gitee.com/YoungTrendFar/Project" target="_blank">
        <span class="el-icon-tickets"/>
      </a>
    </el-tooltip>
    <el-tooltip class="item" effect="dark" content="主题切换" placement="bottom">
      <span class="el-dropdown-link" id="icon" @click="changeTheme">
        <span :class="icon"/>
      </span>
    </el-tooltip>

    <!--对话框-->
    <el-dialog :visible.sync="showDialog"
               :show-close="false"
               @open="openDialog"
               ref="dialog"
               :modal-append-to-body='false'
               :append-to-body="false">
      <div style="font-size: 17px">
        <span class="el-icon-search"
              style="line-height: 10px;margin: 20px 5px 20px 20px;color: var(--headerInputText)!important"/>
        <el-input
            style="width: 55%;user-select: auto;font-size: 16px"
            placeholder=" 搜索"
            ref="input"
            v-model="searchString"
            @keydown.enter.native="jumpToSearch"
            clearable/>
        <span class="word">打开搜索</span>
        <span class="keys">Alt S</span>
        <span class="word" style="margin-left: 10px">退出</span>
        <span class="keys">Esc</span>
      </div>
      <hr style="border: var(--hr) 1px solid;width: 100%;padding: 0;margin: 0">
      <div style="margin-left: 30px;margin-top: 20px;color: var(--searchText)">
        <span style="font-size: 17px;margin-right: 15px;">历史搜索</span>
        <el-popconfirm
            title="确定删除搜索记录吗？"
            confirm-button-type="danger"
            cancel-button-type="primary"
            icon-color="red"
            @confirm="deleteHistory"
        >
          <span slot="reference" class="el-icon-delete deleteIcon"></span>
        </el-popconfirm>
        <div v-if="keywordList.length!==0" v-for="word in keywordList" class="searchItem" @click="searchString=word">
          <span>{{ word }}</span>
        </div>
        <div v-if="keywordList.length===0" style="margin: 100px 160px;width: 200px">
          <span style="font-size: 14px">暂无搜索记录</span>
        </div>
      </div>
    </el-dialog>

  </el-header>
</template>

<script>
import 'animate.css'
import router from "@/router";

export default {
  name: "Header",
  data() {
    return {
      //弹窗显示
      showDialog: false,
      //当前图标
      currentIcon: this.$store.state.theme,
      //当前响应的标签页
      activeTab: this.$router.currentRoute.fullPath,
      //搜索的内容
      searchString: '',
      //搜索记录
      keywordList: localStorage.getItem('searchHistory') ? JSON.parse(localStorage.getItem('searchHistory')) : []
    }
  },
  computed: {
    icon() {
      return this.currentIcon === 'Dark' ? 'el-icon-moon' : this.currentIcon === 'Light' ? 'el-icon-sunny' : 'el-icon-orange'
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
    //打开对话框
    openDialog() {
      this.$nextTick(() => {
        this.$refs.input.focus();
      })
    },
    //修改主题
    changeTheme() {
      const theme = this.$store.state.theme
      let themeName
      if (theme === 'Dark') {
        themeName = 'Light'
        this.$bus.$emit('changeIcon', themeName)
      } else if (theme === 'Light') {
        themeName = 'Orange'
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
    //搜索
    jumpToSearch() {
      //添加搜索记录
      this.keywordList.unshift(this.searchString)
      localStorage.setItem('searchHistory', JSON.stringify(this.keywordList))
      //跳转并传参
      this.showDialog = false
      this.$router.push({path: '/search', query: {searchStr: this.searchString}})
      this.$bus.$emit('changeSearchStr', this.searchString)
      this.searchString = ''
    },
    // //滚动条
    // handleScroll(e) {
    //   if (e.target.__vue__._uid === '22') {
    //     const header = document.getElementsByClassName('header')[0]
    //     if (header) {
    //       header.style.borderBottomColor = `rgba(64,64,64,${e.target.scrollTop})`
    //       if (e.target.scrollTop !== 0) {
    //         header.style.backgroundColor = 'var(--rightBg)'
    //       } else {
    //         header.style.backgroundColor = 'var(--headerBg)'
    //
    //       }
    //     }
    //   }
    // },
    openD() {
      this.showDialog = true
    },
    //清除搜索记录
    deleteHistory() {
      localStorage.removeItem('searchHistory')
      this.keywordList = []
    }
  },
  mounted() {
    this.$bus.$on('changeActiveTab', (path) => {
      this.activeTab = path
    })
    this.$bus.$on('changeIcon', (icon) => {
      this.currentIcon = icon
    })
    //监听滚动条事件
    // window.addEventListener('scroll', this.handleScroll, true);
    //使用快捷键打开或关闭搜索框
    this.$bus.$on('openSearch', () => {
      this.showDialog = !this.showDialog
    })
  },
  beforeDestroy() {
    this.$bus.$off('changeActiveTab')
    this.$bus.$off('changeIcon')
    // window.removeEventListener('scroll', this.handleScroll)
    this.$bus.$off('openSearch')
  }
}
</script>


<style scoped>

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