<template>
  <el-main style="overflow-y: scroll;height: 100%">
    <div class="optionContainer" v-if="existUser">
      <div class="container">
        <span class="title">账号</span>
      </div>
      <div class="rightContainer">
        <el-button class="btn deepText" @click="logout">退出账号登录</el-button>
      </div>
      <el-divider></el-divider>
    </div>

    <div class="optionContainer">
      <div class="container">
        <span class="title">主题</span><br>
        <span class="deepText">自定义您的应用程序主题</span>
      </div>
      <div class="rightContainer">
        <h4 style="margin: 0 0 5px 0">主题颜色模式</h4>
        <span class="deepText">{{ theme }}</span><br>
        <el-button-group style="margin-top: 15px">
          <el-button @click="btnClick('Light')" class="btnGroup btnInGroup" icon="el-icon-sunny"></el-button>
          <el-button @click="btnClick('Dark')" class="btnGroup btnInGroup" icon="el-icon-moon"></el-button>
        </el-button-group>
      </div>
    </div>
    <el-divider></el-divider>
    <div class="optionContainer">
      <div class="container">
        <span class="title">快捷键</span><br>
      </div>
      <div class="rightContainer">
        <div class="option">
          <span class="smallerTitle">播放/暂停</span>
          <div class="keySet">
            <span class="keys">Space</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">上一首</span>
          <div class="keySet">
            <span class="keys">⌘/Ctrl</span>
            <span class="keys">←</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">下一首</span>
          <div class="keySet">
            <span class="keys">⌘/Ctrl</span>
            <span class="keys">→</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">增加音量</span>
          <div class="keySet">
            <span class="keys">⌘/Ctrl</span>
            <span class="keys">↑</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">减少音量</span>
          <div class="keySet">
            <span class="keys">⌘/Ctrl</span>
            <span class="keys">↓</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">静音/取消</span>
          <div class="keySet">
            <span class="keys">⌘/Ctrl</span>
            <span class="keys">M</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">打开搜索</span>
          <div class="keySet">
            <span class="keys">Alt</span>
            <span class="keys">S</span>
          </div>
        </div>
        <div class="option">
          <span class="smallerTitle">关闭窗口</span>
          <div class="keySet">
            <span class="keys">Esc</span>
          </div>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div class="optionContainer" style="flex-direction: column;display: flex">
      <div class="container" style="width: 300px;margin-bottom: 10px">
        <span class="title" style="font-size: 14px">node: 8.11.0</span>
      </div>
      <div class="container" style="width: 300px;margin-bottom: 10px">
        <span class="title" style="font-size: 14px">vue: 5.0.8</span>
      </div>
      <div class="container" style="width: 300px;">
        <span class="title" style="font-size: 14px">spring starter: 2.7.7</span>
      </div>
    </div>
  </el-main>
</template>

<script>
export default {
  name: "Setting",
  data() {
    return {
      theme: localStorage.getItem('theme') === 'Light' ? 'Light' : 'Dark',
      existUser: localStorage.getItem('user') !== null
    }
  },
  methods: {
    btnClick(themeName) {
      this.$bus.$emit('changeTheme', themeName)
      this.$store.commit('setTheme', themeName)
      this.$bus.$emit('changeIcon', themeName)
      localStorage.setItem('theme', themeName)
    },
    logout() {
      localStorage.removeItem('user')
      this.$bus.$emit('logout')
      this.$notify({
        title: '成功',
        message: '账号已登出',
        type: 'success'
      });
      this.existUser = false
      this.$bus.$emit('changeLists', [])
    }
  },
  mounted() {
    this.$bus.$on('changeTheme', (theme) => {
      this.theme = theme
    })
  },
  beforeDestroy() {
    this.$bus.$off('changeTheme')
  }
}
</script>

<style scoped>
.btn {
  background-color: var(--settingBtn);
  border: none;
  color: var(--settingBtnText) !important;
  font-weight: bold;
  transition: 0.2s;
}

.btn:hover {
  background-color: var(--settingBtnHover);
}

.title {
  font-size: 18px;
  font-weight: bold;
  color: var(--settingText);
  width: 400px;
  margin-right: 30%;
}

/deep/ .el-divider {
  background-color: var(--settingHr);
}

.container {
  width: 200px;
  display: inline-block;
  margin-right: 20%;
}

.optionContainer {
  margin: 10px 0;
  padding: 10px 0
}

.deepText {
  font-size: 14px;
  font-weight: bold;
  color: var(--settingLightText);
}

.rightContainer {
  display: inline-block;
  vertical-align: top;
  color: var(--settingText);
}

.btnGroup {
  background-color: var(--settingBtn);
  border: none;
  color: var(--settingBtnText);
  transition: 0.2s;
}

.btnGroup:hover {
  background-color: var(--settingBtnHover);
}

.btnInGroup {
  border-right: 2px solid var(--rightBg);
  margin: 0;
}

.smallerTitle {
  font-size: 14px;
  font-weight: bold;
  color: var(--settingText);

}

.keys {
  float: left;
  letter-spacing: normal;
  color: var(--settingLightText);
  border: var(--settingBorder) solid;
  border-width: 1px 1px 2px 1px;
  padding: 2px 4px;
  border-radius: 5px;
  font-size: 12px;
  font-weight: bold;
  margin-right: 5px;
}

.option {
  width: 240px;
  margin-bottom: 10px;
}

.keySet {
  width: 90px;
  float: right;
}

</style>