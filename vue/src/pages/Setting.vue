<template>
  <div>
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
          <el-button @click="btnClick1" class="btnGroup btnInGroup" icon="el-icon-sunny"></el-button>
          <el-button @click="btnClick2" class="btnGroup btnInGroup" icon="el-icon-moon"></el-button>
          <el-button @click="btnClick3" class="btnGroup" icon="el-icon-orange"></el-button>
        </el-button-group>
      </div>
    </div>
    <el-divider></el-divider>
  </div>
</template>

<script>
export default {
  name: "Setting",
  data() {
    return {
      theme: localStorage.getItem('theme') === 'Dark' ? 'Dark' : localStorage.getItem('theme') === 'Light' ? 'Light' : 'Orange',
      existUser: localStorage.getItem('user') !== null
    }
  },
  methods: {
    btnClick1() {
      const themeName = 'Light'
      this.$bus.$emit('changeTheme', themeName)
      this.$store.commit('setTheme', themeName)
      this.$bus.$emit('changeIcon',themeName)
      localStorage.setItem('theme',themeName)
    },
    btnClick2() {
      const themeName = 'Dark'
      this.$bus.$emit('changeTheme', themeName)
      this.$store.commit('setTheme', themeName)
      this.$bus.$emit('changeIcon',themeName)
      localStorage.setItem('theme',themeName)
    },
    btnClick3() {
      const themeName = 'Orange'
      this.$bus.$emit('changeTheme', themeName)
      this.$store.commit('setTheme', themeName)
      this.$bus.$emit('changeIcon',themeName)
      localStorage.setItem('theme',themeName)
    },
    logout(){
      localStorage.removeItem('user')
      this.$bus.$emit('logout')
      this.$notify({
        title: '成功',
        message: '账号已登出',
        type: 'success'
      });
      this.existUser = false
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
}

.btnGroup:hover {
  background-color: var(--settingBtnHover);
}

.btnInGroup {
  border-right: 2px solid var(--rightBg);
  margin: 0;
}

</style>