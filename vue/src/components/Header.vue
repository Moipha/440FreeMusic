<template>
  <el-header style="display: flex; font-size: 20px;width: 100%;background-color: var(--deepbgRgba)">
    <!--前进后退按钮-->
    <span class="navigation" @click="goBack" :class="{disabled: $store.state.index===0}">
      <span class="el-icon-arrow-left" style="font-weight: 1000;"/>
    </span>
    <span class="navigation" @click="goForward" v-if="$store.state.index !== $store.state.visitedRoutes.length-1">
      <span class="el-icon-arrow-right" style="font-weight: 1000;"/>
    </span>
    <!--标签页-->
    <el-tabs @tab-click="handleClick" v-model="activeTab" style="line-height: 50px;margin-left: 20px">
      <el-tab-pane v-if="activeTab === '/home'|| activeTab === '/home/recent'" label="推荐" name="/home"/>
      <el-tab-pane v-if="activeTab === '/home'|| activeTab === '/home/recent'" label="最近上传" @click="" name="/home/recent"/>
    </el-tabs>
    <!--假搜索框-->
    <span style="width: 240px;height: 40px;background-color: var(--bg);
                margin: auto 10px auto auto;border-radius: 20px;
                font-weight: bold;font-size: 15px;cursor: pointer;"
          @click="showDialog = true">
      <span class="el-icon-search" style="margin: 0 10px 0 20px;line-height: 40px"/>搜索
    </span>
    <el-tooltip class="item" effect="dark" content="主题切换" placement="bottom">
      <span class="el-dropdown-link" id="icon" @click="changeTheme">
        <span :class="currentIcon"/>
      </span>
    </el-tooltip>
    <!--对话框-->
    <el-dialog :visible.sync="showDialog"
               :class="dialogClass"
               :show-close="false"
               @open="openDialog"
               @close="closeDialog"
               ref="dialog">
      <div style="font-weight: 700;font-size: 17px;color: var(--text)">
        <span class="el-icon-search" style="line-height: 10px;margin: 20px 5px 20px 20px;color: var(--deeptext)"/>
        <el-input
            style="width: 80%;color: var(--text);user-select: auto;font-size: 16px;"
            placeholder=" 搜索"
            ref="input"/>
      </div>
      <hr style="border: var(--hr) 1px solid;width: 100%;padding: 0;margin: 0">
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
      //弹窗的动态样式
      dialogClass: 'animate__rotateInDownRight',
      //当前图标
      currentIcon: 'el-icon-moon',
      //当前响应的标签页
      activeTab: this.$router.currentRoute.fullPath
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
      this.dialogClass = 'animate__rotateInDownRight';
      this.$nextTick(() => {
        this.$refs.input.focus();
      })
    },
    //关闭对话框
    closeDialog() {
      this.dialogClass = 'animate__rotateOutUpRight'
    },
    //修改主题
    changeTheme() {
      const theme = this.$store.state.theme
      let themeName
      if (theme === 'dark') {
        themeName = 'light'
        this.currentIcon = 'el-icon-sunny'
      } else if (theme === 'light') {
        themeName = 'orange'
        this.currentIcon = 'el-icon-orange'
      } else {
        themeName = 'dark'
        this.currentIcon = 'el-icon-moon'
      }
      this.$store.commit('setTheme', themeName)
    },
    //标签页点击事件
    handleClick(option) {
      if (router.currentRoute.fullPath !== option.name) {
        router.push(option.name)
      }
    }
  },
  mounted() {
    this.$bus.$on('changeActiveTab', (path) => {
      this.activeTab = path
    })
  }
}
</script>


<style scoped>
.navigation {
  border-radius: 50%;
  background-color: var(--button);
  width: 26px;
  height: 38px;
  margin: 10px 8px 10px 0;
  line-height: 40px;
  font-size: 15px;
  padding-left: 12px;
  cursor: pointer;
}

.navigation:hover {
  background-color: var(--hover);
}

.disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

/*弹窗动画持续时间*/
.animate__rotateInDownRight {
  animation-duration: 0.4s;
}

.animate__rotateOutUpRight {
  animation-duration: 0.8s;
}

/deep/ .el-dialog {
  background-color: var(--bg);
  border-radius: 20px;
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
  border: none;
}

#icon {
  font-size: 20px;
  border-radius: 50%;
  background-color: var(--button);
  color: var(--deeptext);
  padding: 0 9px 20px 11px;
  line-height: 40px;
  margin-left: 5px;
  margin-top: 10px;
  height: 20px;
  width: 20px;
  cursor: pointer;
  transition: 0.2s;
}

#icon:hover {
  background-color: var(--hover);
}

/*修改el-tabs*/
::v-deep .el-tabs__item {
  font-weight: bold;
  color: var(--deeptext);
  padding: 0 15px;
}
::v-deep .el-tabs__item.is-active {
  color: var(--active);
}
::v-deep .el-tabs__nav-wrap::after {
  height: 0;
}
::v-deep .el-tabs__active-bar {
  background-color: var(--active);
}
</style>