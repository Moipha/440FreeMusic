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
            <span style="height: 25px">{{tableData.length}}</span>
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
        <el-form style="font-weight: bold;margin-top: 20px">
          <el-form-item label="退出账号">
            <span class="lightText">退出当前账号并返回至登录界面。</span>
            <el-button style="float: right" class="mineBtn" @click="logout">退出账号</el-button>
          </el-form-item>
          <el-form-item label="登录密码">
            <span class="lightText">已设置。密码至少六位字符，支持数字、字母和除空格以外的特殊字符。</span>
            <el-button style="float: right" class="mineBtn" @click="changePassword">修改密码</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="上传管理" name="music">
        <el-button-group>
          <el-tooltip content="上传">
            <el-button @click="showDialog=true" class="btnInGroup btnGroup" icon="el-icon-plus"></el-button>
          </el-tooltip>
          <el-tooltip content="批量操作">
            <el-button @click="mulEdit" class="btnInGroup btnGroup" icon="el-icon-s-grid"></el-button>
          </el-tooltip>
          <template v-if="showSelection">
            <el-tooltip content="批量启用">
              <el-button @click="mulEnable" class="btnInGroup btnGroup" icon="el-icon-open"></el-button>
            </el-tooltip>
            <el-tooltip content="批量禁用">
              <el-button @click="mulDisable" class="btnInGroup btnGroup" icon="el-icon-turn-off"></el-button>
            </el-tooltip>
            <el-tooltip content="批量删除">
              <el-button @click="mulDelete" class="btnInGroup btnGroup" icon="el-icon-delete"></el-button>
            </el-tooltip>
          </template>
        </el-button-group>
        <el-dialog :visible.sync="showDialog"
                   :show-close="false"
                   ref="dialog"
                   :limit="1"
                   :modal-append-to-body='false'
                   :append-to-body="false">
          <div v-loading="loading" style="height: 100%!important;">
            <el-upload
                ref="uploader"
                :limit="1"
                class="upload-demo"
                drag
                :action="'http://'+serverIp+'/music/getData'"
                :auto-upload="true"
                :on-success="handleGetDataSuccess"
                :before-upload="beforeGetData"
                :show-file-list="false"
                :disabled="!showDragger">
              <template v-if="showDragger">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将音频拖到此处，或<em>浏览本地音乐</em></div>
              </template>
              <template v-else>
                <h1 style="margin-top: 40px"><i class="el-icon-headset"></i> 音频文件已读取</h1>
                <h2>{{ fileName }}</h2>
              </template>
            </el-upload>
            <div style="display: flex;flex-direction: row;margin-top: 40px">
              <div style="display: flex;flex-direction: column;width: 40%">
                <span style="margin-left: 15px;color: var(--mineText)">点击上传封面：</span>
                <el-upload
                    ref="avatar"
                    class="avatar-uploader"
                    :limit="1"
                    :show-file-list="false"
                    style="margin-top: 10px"
                    :on-change="handleUploadAvatar"
                    :action="'http://'+serverIp+'/avatar/saveAvatar'"
                    :auto-upload="false"
                    list-type="picture"
                    :disabled="uploadAvatar!==''"
                    :on-success="handleAvatarUpload">
                  <img v-if="uploadAvatar" :src="uploadAvatar" alt="" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </div>
              <el-form
                  label-position="left"
                  label-width="120px"
                  style="width: 64%;display: flex;flex-direction: column">
                <el-form-item label="音乐标题：" style="margin-bottom: 25px">
                  <el-input v-model="newMusic.name" class="input"></el-input>
                </el-form-item>
                <el-form-item label="音乐作者：" style="margin-bottom: 25px">
                  <el-input v-model="newMusic.author" class="input"></el-input>
                </el-form-item>
                <el-form-item label="所属专辑：" style="margin-bottom: 25px">
                  <el-input v-model="newMusic.album" class="input"></el-input>
                </el-form-item>
                <el-form-item label="文件大小(KB)：" style="margin-bottom: 25px">
                  <el-input v-model="newMusic.size" class="input" disabled></el-input>
                </el-form-item>
              </el-form>
            </div>
            <div style="width: 30%;margin: 30px auto">
              <el-button @click="upload" class="btnGroup btnInGroup" style="float: left ;width: 80px;height: 40px">
                上传
              </el-button>
              <el-button @click="cancelUpload" class="btnGroup btnInGroup"
                         style="float: right;width: 80px;height: 40px">
                取消
              </el-button>
            </div>
          </div>
        </el-dialog>

        <el-table
            ref="singleTable"
            :data="tableData"
            style="width: 100%;margin: auto;height: calc(100vh - 450px);overflow-y: scroll;padding-right: 8px"
            @selection-change="handleSelectionChange"
            v-loading="loadingTable"
            @mouseenter.native="showScrollbar"
            @mouseleave.native="hideScrollbar">
          <el-table-column
              type="selection"
              width="50"
              v-if="showSelection">
          </el-table-column>
          <el-table-column
              width="70">
            <template slot-scope="scope">
              <el-avatar shape="square" size="medium" style="background-color: #00000000" :src="scope.row.avatar"></el-avatar>
<!--              <img :src="scope.row.avatar" alt="" style="width: 100%;height: 50px">-->
            </template>
          </el-table-column>
          <el-table-column
              property="name"
              label="音乐标题">
          </el-table-column>
          <el-table-column
              property="author"
              label="歌手">
          </el-table-column>
          <el-table-column
              property="album"
              label="专辑">
          </el-table-column>
          <el-table-column
              property="createTime"
              label="上传时间"/>
          <el-table-column
              property="time"
              label="时长"
              width="60">
          </el-table-column>

          <el-table-column
              property="enable"
              label="启用"
              width="60">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.enable" @change="changeEnable(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column
              property="edit"
              width="50">
            <span class="el-icon-more" style="cursor: pointer;font-size: 20px"></span>
          </el-table-column>
        </el-table>

      </el-tab-pane>
      <el-tab-pane label="关注用户" name="user">

      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<!--TODO 实现多选操作和启用等操作-->
<script>
import {serverIp} from "../../public/config";
import axios from "axios";

export default {
  name: "Mine",
  data() {
    return {
      username: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).username : '',
      nickname: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).nickname : '',
      activeName: 'data',
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
      nowChanging: false,
      //表格数据
      tableData: [],
      //是否显示多选框
      showSelection: false,
      //多选的选项
      multipleSelection: [],
      //显示添加对话框
      showDialog: false,
      //新增music
      newMusic: {},
      //加载中
      loading: false,
      //IP
      serverIp: serverIp,
      //upload-dragger样式修改
      showDragger: true,
      //即将上传的头像地址
      uploadAvatar: '',
      //上传的音频文件名
      fileName: '',
      //是否需要传入数据库
      needSaveDB: true,
      //tableLoading
      loadingTable: true
    }
  },
  mounted() {
    this.getUploadList()
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
      }, 200)
    },
    logout() {
      localStorage.removeItem('user')
      this.$bus.$emit('logout')
      this.$notify({
        title: '成功',
        message: '账号已登出',
        type: 'success'
      });
      this.$router.push('/login')
      this.$bus.$emit('changeLists', [])
    },
    //TODO 实现改密码的功能： 最好是弹窗，然后让你输入旧密码，验证成功后再输入两次新密码，然后确定两次输入一样
    changePassword() {

    },
    //多选框变动
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //更新enable状态
    changeEnable(scope) {
      // this.request.post('',scope).then(response => {
      //   console.log('更新成功',response)
      // }).catch( error => {
      //   console.log('更新失败',error)
      // })
    },
    //批量启用
    mulEnable() {
      this.multipleSelection.forEach((music) => {
        music.enable = true
      })
    },
    //批量禁用
    mulDisable() {
      this.multipleSelection.forEach((music) => {
        music.enable = false
      })
    },
    //批量删除
    mulDelete() {
      this.multipleSelection.forEach((music) => {
        music.isDelete = true
      })
    },
    //批量操作
    mulEdit() {
      this.showSelection = !this.showSelection
    },
    //上传音乐相关
    handleGetDataSuccess(res) {
      this.newMusic = res.data
      this.showDragger = false
      this.loading = false
    },
    beforeGetData(file) {
      //设置显示的文件名
      this.fileName = file.name
      const extension = file.name.split('.').pop().toLowerCase()
      const isMusic = extension === 'mp3' || extension === 'flac'
      const isLt50M = file.size / 1024 / 1024 < 50
      if (!isMusic) {
        this.$notify.error('上传音乐只能是 MP3/FLAC 格式!')
      } else if (!isLt50M) {
        this.$notify.error('上传音乐文件大小不能超过 50MB!')
      } else {
        //loading
        this.loading = true
        return true
      }
      return false
    },
    //取消上传后，关闭对话框并清除已上传的文件
    cancelUpload() {
      this.showDialog = false
      this.newMusic = {}
      this.showDragger = true
      this.$refs.uploader.clearFiles()
      this.uploadAvatar = ''
      this.$refs.avatar.clearFiles()
      this.fileName = ''
      this.loading = false
    },
    //显示上传的封面
    handleUploadAvatar(file) {
      const reader = new FileReader() // 创建FileReader对象
      reader.readAsDataURL(file.raw) // 读取选择的文件的数据
      reader.onload = () => {
        this.uploadAvatar = reader.result; // 将文件的本地路径保存到data属性中
      }
    },
    //上传完整的单曲信息以及封面和音频文件
    upload() {
      //loading
      this.loading = true

      if (!this.$refs.uploader.uploadFiles.length) {
        this.$notify({
          title: '上传失败',
          message: '未选择音频',
          type: 'error'
        })
        this.loading = false
        return
      }
      //上传音频
      let file = this.$refs.uploader.uploadFiles[0].raw
      let formData = new FormData()
      formData.append('file', file)
      //不能使用utils中配置的默认请求头，因为要使用multipart类型的contentType
      axios.post(`http://${serverIp}/music/saveMusic`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        }
      }).then(response => {
        if (response.data.code !== '200') {
          //上传失败
          this.$notify({
            title: '音频上传失败',
            message: response.data.msg,
            type: 'error'
          })
          this.loading = false
        } else {
          this.$notify({
            title: '音频上传成功',
            type: 'success'
          });
          //获取返回的数据
          this.newMusic.url = response.data.data.url
          this.newMusic.md5 = response.data.data.md5
          this.needSaveDB = response.data.data.enable
          if (!this.needSaveDB) {
            this.newMusic.id = response.data.data.id
          }
          if (this.uploadAvatar) {
            //上传封面文件
            this.$refs.avatar.submit()
          } else {
            //存入没有封面的对象
            this.newMusic.avatar = require('@/assets/DefaultAvatar.png')
            this.saveMusic()
          }
        }
      }).catch(err => {
        this.$notify({
          title: '音频上传失败',
          message: err,
          type: 'error'
        });
        this.loading = false
      })
    },
    handleAvatarUpload(res) {
      if (res.code !== '200') {
        this.$notify({
          title: '封面上传失败',
          message: res.msg,
          type: 'error'
        })
        this.loading = false
      } else {
        this.$notify({
          title: '封面上传成功',
          type: 'success'
        })
        //将返回的封面路径存入对象
        this.newMusic.avatar = res.data.url
        //将完整的对象存入
        this.saveMusic()
      }
    },
    saveMusic() {
      //最后再上传前收拾一下对象
      this.newMusic.uploader = JSON.parse(localStorage.getItem('user')).id
      if(this.newMusic.album === ''){
        this.newMusic.album = '未知专辑'
      }
      //然后，到这里封面和音频都上传成功了，此时将完整的Music对象存入数据库
      this.request.post('/music/saveDB', this.newMusic).then(res => {
        if (res.code !== '200') {
          this.$notify({
            title: '音乐信息保存失败',
            message: res.msg,
            type: 'error'
          })
          this.loading = false
        } else {
          if (!this.needSaveDB) {
            this.$notify({
              title: '音乐信息已更新',
              type: 'info'
            })
          } else {
            this.$notify({
              title: '音乐信息已存入数据库',
              type: 'success'
            })
          }
          this.getUploadList()
          //最后，清除各种信息并关闭窗口
          this.cancelUpload()
        }
      })
    },
    //获取当前用户上传过的音乐列表
    getUploadList() {
      this.loadingTable = true
      this.request.get('/user/getUploadList/' + JSON.parse(localStorage.getItem('user')).id).then(res => {
        if (res.code !== '200') {
          this.$notify({
            title: '获取数据失败',
            message: res.msg,
            type: 'error'
          })
        } else {
          this.tableData = res.data
        }
      })
      this.loadingTable = false
    },
    //切换滚动条状态
    showScrollbar() {
      document.documentElement.style.setProperty('--test2', 'visible');
    },
    hideScrollbar() {
      document.documentElement.style.setProperty('--test2', 'hidden');
    }
  },
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
  z-index: 0;
}

::v-deep .el-tabs__active-bar {
  background-color: var(--mineTabHover);
}

/deep/ .el-tabs__nav {
  position: static;
}

.input {
  width: 300px;
}

/deep/ .el-input__inner {
  background-color: var(--mineInputBg) !important;
  border: solid 1px var(--rightBg) !important;
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
  transition: 0.2s
}

.mineBtn:hover {
  background-color: var(--mineBtnHover) !important;
  color: var(--mineBtnText);
}

.mineBtn:focus {
  background-color: var(--mineBtnBg);
  color: var(--mineBtnText);
}

.lightText {
  font-weight: 500;
  margin-left: 20px;
  color: var(--settingLightText)

}


/*表格样式修改：*/
/*表头背景*/
/deep/ .el-table th {
  background-color: var(--rightBg) !important;
  padding-bottom: 40px;
  color: var(--searchLightText);
  font-size: 14px;
}

/*表头文字*/
/deep/ .el-table thead {
  color: var(--searchText)
}

/*背景*/
/deep/ .el-table tr {
  background-color: var(--rightBg);
  color: var(--searchText);
  font-weight: bold;
}

/*行间线*/
/deep/ .el-table td, .building-top .el-table th.is-leaf {
  border-bottom: 0 !important;
}

/*表格末尾*/
::v-deep .el-table::before {
  border-bottom: 0;
  height: 0;
}

/*无数据*/
::v-deep .el-table__empty-block {
  background-color: var(--rightBg);
}

::v-deep .el-table__empty-text {
  color: var(--searchText);
}

/deep/ th {
  border-bottom: 0 !important;
}

/*选中行的背景*/
::v-deep .el-table__body tr.current-row > td {
  background: var(--searchActive) !important;
  color: var(--searchText);
}

::v-deep .el-table__body tr.current-row:hover > td {
  background: var(--searchActive) !important;
}

::v-deep .el-table--enable-row-transition .el-table__body td, .el-table .cell {
  background-color: var(--rightBg)
}

/*行高*/
::v-deep .el-table td {
  padding: 8px 0 0;
}

/*滚动条*/
::v-deep .el-table--scrollable-x .el-table__body-wrapper {
  display: none !important;
}

/deep/ .el-table, .el-table__expanded-cell {
  background-color: var(--rightBg) !important;
}

/*鼠标悬浮*/
/deep/ .el-table tbody tr:hover > td {
  background-color: var(--searchHover) !important;
}

tr.el-table__row {
  border-radius: 50% !important;
}

/deep/ .el-table__row:hover {
  border-radius: 50% !important;
}


/deep/ .el-table_21_column_121 {
  -moz-border-radius-topleft: 50% !important;
  -moz-border-radius-bottomleft: 50% !important;
}


.btnGroup {
  background-color: var(--settingBtn) !important;
  border: none;
  color: var(--settingBtnText) !important;
  transition: 0.2s;
}

.btnGroup:hover {
  background-color: var(--settingBtnHover) !important;
}

.btnInGroup {
  width: 60px;
  height: 30px;
  font-size: 16px;
  border-right: 2px solid var(--rightBg) !important;
  margin-bottom: 10px;
  padding: 5px;
}


/deep/ .el-dialog {
  background-color: var(--dialogBg);
  border-radius: 20px;
  height: 570px;
  width: 700px;
}

/deep/ .el-dialog__body {
  height: 100%;
  padding: 0;
}

/deep/ .el-dialog__header {
  height: 0;
  padding: 0;
}

/deep/ .el-upload {
  width: 100% !important;
}

/deep/ .el-upload-dragger {
  margin: 15px auto;
  width: 670px;
  background-color: var(--dialogBg);
  color: var(--dialogText);
  border: 1px dashed var(--dialogText) !important;
}

/deep/ .el-upload-dragger:hover {
  border-color: var(--leftBtnActive) !important;
}

/*封面上传*/
.avatar-uploader .el-upload {
  border: 1px dashed var(--dialogText) !important;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader {
  margin-left: 15px;
  width: 200px !important;
  border: 1px dashed var(--dialogText) !important;
  border-radius: 5px;
}

.avatar-uploader:hover {
  border-color: var(--leftBtnActive) !important;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: var(--dialogText);
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 100%;
  height: auto;
  display: block;
}

/*单选框*/
/deep/ .el-switch.is-checked .el-switch__core {
  background-color: var(--mineBtnBg);
  border-color: var(--mineBtnBg);
}

/deep/ .el-checkbox__input.is-checked .el-checkbox__inner {
  background-color: var(--mineBtnBg) !important;
  border-color: var(--mineBtnBg) !important;
}

/deep/ .el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: var(--mineBtnBg) !important;
  border-color: var(--mineBtnBg) !important;
}

/deep/ .el-loading-mask {
  background-color: var(--maskBg);
  height: 100%;
  border-radius: 15px;
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


/*滚动条*/

.test2 {
  --test2: visible;
}

::-webkit-scrollbar {
  overflow: auto;
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background-color: var(--scrollbar);
  border-radius: 5px;
  visibility: var(--test2);

}

::-webkit-scrollbar-thumb:hover {
  background-color: var(--scrollbarHover);
}

::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background-color: var(--rightBg);
  border-radius: 5px;
}
</style>