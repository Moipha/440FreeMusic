<template>
  <el-aside width="250px" style="height: 100%;overflow-x: hidden;">
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
        <svg t="1680360816994" class="icon leftSectionInner" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="2784" width="13" height="13">
          <path
              d="M322.56 913.92v-318.179556a51.2 51.2 0 0 1 51.2-51.2h275.911111a51.2 51.2 0 0 1 51.2 51.2v318.179556h121.571556V559.616a51.2 51.2 0 0 1 102.4 0v405.504a51.2 51.2 0 0 1-51.2 51.2H149.617778a51.2 51.2 0 0 1-51.2-51.2V562.005333a51.2 51.2 0 1 1 102.4 0v351.914667h121.742222z m102.4 0h173.511111v-266.979556H424.96v266.979556zM94.663111 533.845333a51.2 51.2 0 1 1-71.111111-73.671111L477.354667 22.186667a51.2 51.2 0 0 1 71.111111 0.056889l152.405333 147.626666a51.2 51.2 0 0 1-71.281778 73.500445L512.796444 130.275556l-418.133333 403.569777z m637.610667-190.976a51.2 51.2 0 0 1 71.281778-73.557333l197.063111 190.919111a51.2 51.2 0 0 1-71.281778 73.557333l-197.063111-190.919111z"
              fill="#ffffff" p-id="2785"></path>
        </svg>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="/likes" class="leftSection">
        <svg t="1680361000732" class="icon leftSectionInner" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="7295" width="13" height="13">
          <path
              d="M883.541333 211.114667C812.373333 148.821333 713.898667 128 620.202667 155.818667a33.911467 33.911467 0 0 0-23.04 42.325333 33.962667 33.962667 0 0 0 42.325333 23.04c71.168-20.992 145.578667-5.632 198.826667 41.301333 44.544 39.082667 70.144 93.184 72.192 152.234667 1.877333 59.050667-19.968 114.688-61.781334 156.330667L551.082667 868.693333c-19.626667 19.797333-54.101333 19.797333-73.728 0L179.712 571.050667c-41.813333-41.813333-63.658667-97.28-61.781333-156.330667 1.877333-59.050667 27.477333-113.152 72.192-152.234667 83.285333-73.216 212.650667-66.389333 294.570666 15.530667l92.16 97.621333c12.970667 13.653333 34.474667 14.336 48.298667 1.365334s14.336-34.474667 1.365333-48.298667l-88.064-93.525333-5.461333-5.632c-107.52-107.008-277.845333-115.2-388.096-18.432-58.88 51.712-92.842667 123.221333-95.232 201.386666-2.56 76.970667 27.306667 152.405333 81.749333 206.848l297.642667 297.642667a119.978667 119.978667 0 0 0 85.162667 35.328c32.085333 0 62.464-12.458667 85.162666-35.328l297.642667-297.642667c54.442667-54.442667 84.309333-129.877333 81.749333-206.848-2.56-77.994667-36.352-149.504-95.232-201.386666z"
              fill="#ffffff" p-id="7296"></path>
        </svg>
        <span slot="title">收藏</span>
      </el-menu-item>
      <el-menu-item index="/setting" class="leftSection">
        <svg t="1680361090482" class="icon leftSectionInner" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="10945" width="13" height="13">
          <path
              d="M944.48 552.458667l-182.357333 330.666666a73.792 73.792 0 0 1-64.565334 38.325334h-362.133333a73.792 73.792 0 0 1-64.565333-38.325334l-182.357334-330.666666a75.338667 75.338667 0 0 1 0-72.682667l182.357334-330.666667a73.792 73.792 0 0 1 64.565333-38.325333h362.133333a73.792 73.792 0 0 1 64.565334 38.325333l182.357333 330.666667a75.338667 75.338667 0 0 1 0 72.682667z m-55.989333-31.146667a10.773333 10.773333 0 0 0 0-10.378667l-182.037334-330.666666a10.517333 10.517333 0 0 0-9.205333-5.482667H335.733333a10.517333 10.517333 0 0 0-9.205333 5.482667l-182.037333 330.666666a10.773333 10.773333 0 0 0 0 10.378667l182.037333 330.666667a10.517333 10.517333 0 0 0 9.205333 5.472h361.514667a10.517333 10.517333 0 0 0 9.205333-5.472l182.037334-330.666667zM513.738667 682.666667c-94.261333 0-170.666667-76.405333-170.666667-170.666667s76.405333-170.666667 170.666667-170.666667c94.250667 0 170.666667 76.405333 170.666666 170.666667s-76.416 170.666667-170.666666 170.666667z m0-64c58.912 0 106.666667-47.754667 106.666666-106.666667s-47.754667-106.666667-106.666666-106.666667-106.666667 47.754667-106.666667 106.666667 47.754667 106.666667 106.666667 106.666667z"
              fill="#ffffff" p-id="10946"></path>
        </svg>
        <span slot="title">设置</span>
      </el-menu-item>
      <el-submenu class="musicList" index="1">
        <template slot="title">
          <span style="font-size: 14px">创建的歌单</span>
          <i class="el-icon-plus plus" @click="addMusicList"/>
        </template>
      </el-submenu>

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
    },
    //添加歌单
    addMusicList() {
      //阻止冒泡
      event.stopPropagation();

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
  font-size: 14px;
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
  margin-right: 15px;
  margin-left: 5px;
}

/deep/ .musicList .el-submenu__title:hover {
  background-color: var(--leftbg) !important;
}

/deep/ .el-submenu__icon-arrow {
  font-size: 15px !important;
}


/*创建歌单加号*/
.plus {
  margin-left: 80px;
  font-size: 14px;
  padding: 10px 5px;
}

.plus:hover {
  background-color: var(--leftBtnHover);
}


</style>