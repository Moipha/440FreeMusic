<template>
  <div class="container">
    <!--对话框-->
    <el-dialog :visible.sync="showDialog"
               :show-close="false"
               ref="dialog"
               :modal-append-to-body='false'
               :append-to-body="false"
               :close-on-click-modal="false">
      <div style="color: var(--listText);padding-top: 15px">
        <div style="text-align: center;font-size: 15px;font-weight: bold">编辑歌单信息</div>
        <i class="el-icon-close close" @click="showDialog=false"></i>
      </div>
      <hr style="border: var(--hr) 1px solid;margin-top: 15px">
      <div class="avatarContainer">
        <span style="margin-bottom: 10px">封面</span>
        <el-avatar shape="square" size="large" :src="src"></el-avatar>
        <span class="dialogBtn">更改封面</span>
        <span style="margin-top: 20px">
          <span style="color: tomato;font-size: 16px;position: relative;top: 3px;right: 2px">*</span>
          歌单名
        </span>
        <el-input style="margin-top: 10px" show-word-limit maxlength="20"></el-input>
        <span style="margin-top: 20px;margin-bottom: 10px">歌单描述</span>
        <el-input type="textarea" :rows="4" show-word-limit maxlength="200"></el-input>
      </div>
      <hr style="border: var(--hr) 1px solid;margin-top: 15px">
      <span class="listBtn saveBtn" style="background-color: var(--listBtn);margin: 10px 20px 15px 10px">保存</span>
      <span class="listBtn cancelBtn" style="background-color: var(--listInput);margin: 10px 0 15px">取消</span>
    </el-dialog>
    <div class="background"></div>
    <div class="top">
      <el-avatar class="left" shape="square" :size="200" :src="src"></el-avatar>
      <div class="right">
        <div style="font-size: 30px;font-weight: normal;margin-top: 40px;margin-bottom: 20px">
          {{ listTitle }}
          <el-tooltip content="编辑" placement="top">
            <span class="el-icon-edit edit" @click="edit"></span>
          </el-tooltip>
        </div>
        <div style="font-size: 15px">{{ author.username }} · 创建于 {{ list.createTime }}</div>
        <div style="margin-top: 15px">
          <el-button class="btn"
                     style="width: 130px;height: 36px;border-radius: 18px 0 0 18px;font-size: 15px;line-height: 15px">
            <i style="margin-right: 5px;padding: 0;" class="el-icon-video-play"/> 播放全部
          </el-button>
          <el-button class="btn" style="width: 45px;height: 36px;border-radius: 0 18px 18px 0">
            <i style="font-size: 16px" class="el-icon-plus"/>
          </el-button>
        </div>
      </div>
    </div>
    <div class="bottom">
      <table>
        <tr style="color: var(--listTh);font-size: 14px">
          <th style="width: 5%" align="center"></th>
          <th align="left" style="width: 40%;">音乐标题</th>
          <th align="left" style="width: 20%">歌手</th>
          <th align="left" style="width: 20%">专辑</th>
          <th align="left" style="width: 5%">时长</th>
          <th align="left" style="width: 5%;"></th>
        </tr>
        <tr style="height: 20px"></tr>
        <!--TODO 这里的右击显示菜单还没有实现。可以新建另一个Menu组件，或者通过总线传参来生成不同的菜单-->
        <tr class="items" v-for="music in musics" @contextmenu.prevent="openMenu($event,music)">
          <td style="padding-top: 5px">
            <el-avatar :src="music.avatar" shape="square"></el-avatar>
          </td>
          <td>{{ music.name }}</td>
          <td>{{ music.author }}</td>
          <td>{{ music.album }}</td>
          <td>{{ music.time }}</td>
          <td><span class="el-icon-more" style="cursor: pointer;font-size: 20px"></span></td>
        </tr>

      </table>
      <div v-if="musics.length === 0">
        <div style="text-align: center;margin-top: 100px;font-size: 40px"><i class="el-icon-delete"></i></div>
        <div style="text-align: center;margin-top: 20px;font-size: 20px">暂 无 歌 曲</div>
        <div style="font-size: 10px;text-align: center;margin-top: 100px">通过 搜索歌曲 -> 右键点击歌曲 -> 添加到歌单 即可往歌单中添加歌曲</div>
      </div>
    </div>
  </div>
</template>
<!--TODO 尝试一下步骤条-->
<script>
export default {
  name: "List",
  data() {
    return {
      listTitle: this.$route.query.listTitle,
      list: {},
      author: {},
      musics: [],
      showDialog: false,
    }
  },
  computed: {
    src() {
      return this.list.avatar ? this.list.avatar : require('@/assets/DefaultAvatar.png')
    }
  },
  methods: {
    //获取歌单信息
    getListData() {
      this.request.post('/list/getData', {
        "title": this.listTitle,
        "userId": JSON.parse(localStorage.getItem('user')).id.toString()
      }).then(res => {
        if (res.code === '200') {
          this.list = res.data
          this.getAuthorData()
          this.getMusics()
        } else {
          this.$notify({
            type: 'error',
            title: '获取歌单信息失败',
            message: res.msg
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          message: err,
          title: '获取歌单信息失败'
        })
      })
    },
    //获取创建者信息
    getAuthorData() {
      this.request.post('/list/getAuthor/' + this.list.authorId).then((res) => {
        if (res.code === '200') {
          this.author = res.data
        } else {
          this.$notify({
            type: 'error',
            message: res.msg,
            title: '获取创建者信息失败'
          })
        }
      }).catch((err) => {
        this.$notify({
          type: 'error',
          message: err,
          title: '获取创建者信息失败'
        })
      })
    },
    //获取歌单列表中的歌曲
    getMusics() {
      this.request.post('/list/getMusics/' + this.list.id).then(res => {
        if (res.code === '200') {
          this.musics = res.data
        } else {
          this.$notify({
            type: 'error',
            title: '获取歌曲失败',
            message: res.msg
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          title: '获取歌曲失败',
          message: err
        })
      })
    },
    //弹出编辑界面
    edit() {
      this.showDialog = true
    }
  },
  mounted() {
    this.getListData()
    this.$bus.$on('changeList', () => {
      this.listTitle = this.$route.query.listTitle
      //根据歌单标题获取相关内容
      this.getListData()
    })
  },
  beforeDestroy() {
    this.$bus.$off('changeList')
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  color: var(--listText);
}

.top {
  display: flex;
  flex-direction: row;
  padding-left: 9px;
}

.left {
  height: 200px;
  width: 200px;
  border-radius: 10px;
  margin-right: 20px;
}

.right {
  display: flex;
  flex-direction: column;
}

.bottom {
  padding-top: 20px;
}

.edit {
  padding: 5px;
  cursor: pointer;
  border-radius: 5px;
}

.edit:hover {
  background-color: var(--listHover);
}

.btn {
  color: #EEEEEE;
  background-color: var(--listBtn);
  transition: 0.2s;
  border: none;
  margin: 0;
  padding: 0;
}

.btn:hover {
  background-color: var(--listBtnHover);
}

.background {
  position: absolute;
  width: 100%;
  height: calc(3% + 270px);
  background-color: #006bad;
  top: 0;
  left: 0;
  z-index: -1;
}


.items {
  width: 100%;
  font-size: 14px;
  height: 54px;
  border: solid #00000000;
  border-width: 10px 10px 10px 20px;
}

.items * {
  letter-spacing: normal;
}

.items:hover {
  background: var(--searchHover);
}

table {
  width: 100%;
  color: var(--searchText);
  border-collapse: collapse;
  border: none;
}

tr td:first-child {
  border-bottom-left-radius: 12px;
  border-top-left-radius: 12px;
}

tr td:last-child {
  border-bottom-right-radius: 12px;
  border-top-right-radius: 12px;
}


/deep/ .el-dialog {
  background-color: var(--dialogBg);
  border-radius: 10px;
  width: 520px;
  display: flex;
  flex-direction: column;
}

/deep/ .el-dialog__body {
  padding: 0;
}

/deep/ .el-dialog__header {
  height: 0;
  padding: 0;
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

.close {
  position: absolute;
  right: 15px;
  top: 15px;
  padding: 5px;
  border-radius: 5px;
  cursor: pointer;
}

.close:hover {
  background-color: var(--listHover);
}

.dialogBtn {
  background-color: var(--listInput);
  width: 60px;
  text-align: center;
  padding: 2px 12px;
  margin-top: 10px;
  line-height: 30px;
  height: 30px;
  border-radius: 5px;
  cursor: pointer;
}

.dialogBtn:hover {
  background-color: var(--listHover);
}

.avatarContainer {
  display: flex;
  flex-direction: column;
  padding: 20px;
  color: var(--listText);
  font-size: 13px;
}

/deep/ .el-input__inner, /deep/ .el-textarea__inner {
  /*TODO 修改样式*/
  color: var(--dialogText);
  background-color: var(--listInput);
  border: var(--loginInputBorder) solid 1px;
  border-radius: 3px;
  transition: 0.2s;
}

/deep/ .el-input__inner:hover, /deep/ .el-textarea__inner:hover {
  background-color: var(--listHover);
}

/deep/ .el-input__inner:focus, /deep/ .el-textarea__inner:focus {
  border-color: var(--loginInputActive);
  background-color: var(--dialogInputActiveBg);
}

/deep/ .el-input__inner::placeholder, /deep/ .el-textarea__inner::placeholder {
  color: var(--loginInputText);
  font-size: 14px;
}

/deep/ .el-input__count-inner, /deep/ .el-input__count {
  background-color: #00000000 !important;
  color: var(--listText);
}

.listBtn {
  float: right;
  padding: 8px 15px;
  color: var(--listText);
  border-radius: 3px;
  font-weight: bold;
  cursor: pointer;
}

.saveBtn:hover {
  background-color: var(--listBtnHover) !important;
}

.cancelBtn:hover {
  background-color: var(--listHover) !important;
}
</style>