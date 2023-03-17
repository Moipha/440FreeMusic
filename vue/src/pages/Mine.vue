<template>
  <div>
    <el-card style="background-color: var(--mineCardBg);border: none;border-radius: 10px;width: 80%;margin: 10px auto">
      <div style="display: inline-block;margin: 10px;vertical-align: top">
        <el-avatar style="width: 80px;height: 80px;">{{ initials }}</el-avatar>
      </div>
      <span style="color: var(--mineText);line-height: 100px;margin-left: 20px">{{ username.toUpperCase() }}</span>
      <div style="color: var(--mineText);display: inline-block;float: right;height: 100px;line-height: 80px;font-weight: bold">
        <div class="inlineContainer">
          <div class="flexContainer">
            <span style="height: 25px">23</span>
            <span>上传</span>
          </div>
        </div>
        <div class="inlineContainer">
          <div class="flexContainer">
            <span style="height: 25px">2</span>
            <span>收藏</span>
          </div>
        </div>
        <div class="inlineContainer">
          <div class="flexContainer">
            <span style="height: 25px">1</span>
            <span>动态</span>
          </div>
        </div>
      </div>
    </el-card>
    <el-tabs style="width: 80%;margin: 20px auto;" v-model="activeName">
      <el-tab-pane label="基本信息" name="data">
        <div></div>
      </el-tab-pane>
      <el-tab-pane label="账号设置" name="setting">

      </el-tab-pane>
      <el-tab-pane label="单曲上传" name="music">

      </el-tab-pane>
      <el-tab-pane label="关注用户" name="user">

      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "Mine",
  data() {
    return {
      username: JSON.parse(localStorage.getItem('user')).username,
      activeName: 'data'
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
    }
  }
}
</script>

<style scoped>
.el-avatar {
  background-color: var(--mineAvatarBg);
  color: var(--mineAvatarText);
  font-size: 40px;
  line-height: 80px;
}

.inlineContainer {
  display: inline-block;
  margin: auto 40px;
  color: var(--mineText)
}

.flexContainer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center
}

/*修改el-tabs*/
::v-deep .el-tabs__item {
  color: var(--mineTabText);
  padding: 0 15px;
}
/deep/ .el-tabs__item:hover{
  color: var(--mineTabHover);
}
::v-deep .el-tabs__item.is-active {
  color: var(--mineTabHover);
}
::v-deep .el-tabs__nav-wrap::after {
  height: 1px;
  background-color: var(--blackText);
}
::v-deep .el-tabs__active-bar {
  background-color: var(--mineTabHover);
}

</style>