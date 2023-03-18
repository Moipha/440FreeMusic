<template>
  <div>
    <el-card style="background-color: var(--mineCardBg);border: none;border-radius: 10px;width: 80%;margin: 10px auto">
      <div style="display: inline-block;margin: 10px;vertical-align: top">
        <el-avatar style="width: 80px;height: 80px;">{{ initials }}</el-avatar>
      </div>
      <span style="color: var(--mineText);line-height: 100px;margin-left: 20px">{{ username.toUpperCase() }}</span>
      <div
          style="color: var(--mineText);display: inline-block;float: right;height: 100px;line-height: 80px;font-weight: bold">
        <div class="inlineContainer">
          <div class="flexContainer" @click="activeName = 'music'">
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
    <el-tabs style="width: 80%;margin: 20px auto;color: var(--mineText)" v-model="activeName">
      <el-tab-pane label="基本信息" name="data">

        <el-form label-position="left" label-width="80px" :model="user" style="font-weight: bold;margin-top: 20px">
          <el-form-item label="用户名">
            {{ user.username }}
          </el-form-item>
          <el-form-item label="邮箱">
            {{ user.email }}
          </el-form-item>
          <el-form-item label="昵称">
            <span v-if="!nowChanging">{{ user.nickname ? user.nickname : '暂无' }}</span>
            <el-input v-if="nowChanging" v-model="nickname" class="input" @blur="cancelEdit" ref="myInput"></el-input>
            <el-button :icon="currentIcon" class="mineBtn" @click="editNickname"></el-button>
          </el-form-item>
          <el-form-item label="注册日期">
            {{ user.createTime }}
          </el-form-item>
        </el-form>

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
      username: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).username : '',
      nickname: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).nickname : '',
      activeName: 'data',
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
      nowChanging: false,
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
    currentIcon() {
      return this.nowChanging ? 'el-icon-finished' : 'el-icon-edit'
    }
  },
  methods: {
    editNickname() {
      this.nowChanging = !this.nowChanging
      //如果正在修改时点击视为保存，提交更新
      if (!this.nowChanging) {
        this.user.nickname = this.nickname
        this.request.post("/user/edit", this.user).then(response => {
          if (response.code !== '200') {
            //更新失败
            this.$notify({
              title: '更新失败',
              message: response.msg,
              type: 'error'
            })
            this.user.nickname = ''
            this.nowChanging = true
          } else {
            //弹窗
            this.$notify({
              title: '成功',
              message: '个人信息更新成功',
              type: 'success'
            })
            localStorage.setItem('user', JSON.stringify(this.user))
          }
        }).catch(err => {
          this.user.nickname = ''
          this.nowChanging = true
          this.$notify({
            title: '更新失败',
            message: err,
            type: 'error'
          })
        })
      } else {
        //如果是刚打开那么获得焦点
        this.$nextTick(() => {
          this.$refs.myInput.focus()
        })
      }
    },
    cancelEdit() {
      //设置300ms
      setTimeout(() => {
        this.nowChanging = false
        this.nickname = this.user.nickname
      }, 200)   },
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
  align-items: center;
  cursor: pointer;
}

/*修改el-tabs*/
::v-deep .el-tabs__item {
  color: var(--mineTabText);
  padding: 0 15px;
}

/deep/ .el-tabs__item:hover {
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

.input {
  width: 300px;
}

/deep/ .el-input__inner {
  background-color: var(--mineInputBg);
  border: solid 1px var(--rightBg);
  color: var(--mineText);
}

/deep/ .el-input__inner:hover {
  border-color: var(--rightBg);
}

/deep/ .el-input__inner:focus {
  border-color: var(--loginInputActive);
}

/deep/ .el-form-item__label {
  color: var(--mineText);
}

.dataContainer {
  margin-top: 20px;
}

.mineBtn {
  margin-left: 20px;
  background-color: var(--mineBtnBg);
  color: var(--mineBtnText);
  font-size: 15px;
  padding: 8px 14px;
  border: none;
}

.mineBtn:hover {
  background-color: var(--mineBtnHover) !important;
  color: var(--mineBtnText);
}

.mineBtn:focus {
  background-color: var(--mineBtnBg);
  color: var(--mineBtnText);
}


</style>