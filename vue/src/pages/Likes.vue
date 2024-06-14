<template>
  <div class="container">
    <div class="background"></div>
    <div class="top">
      <el-avatar class="left" shape="square" :size="200" :src="src"></el-avatar>
      <div class="right">
        <div style="font-size: 30px;font-weight: normal;margin-top: 40px;margin-bottom: 20px">
          我喜欢的音乐
          <span class="el-icon-headset"></span>
        </div>
        <div style="font-size: 15px;margin-left: 10px">共{{ musics.length }}首 · 创建于{{user.createTime}}</div>
        <div style="margin-top: 18px">
          <el-button @click="playAll" class="btn"
                     style="width: 130px;height: 36px;border-radius: 18px 0 0 18px;font-size: 15px;line-height: 15px">
            <i style="margin-right: 5px;padding: 0;" class="el-icon-video-play"/> 播放全部
          </el-button>
          <el-button @click="addAll" class="btn" style="width: 45px;height: 36px;border-radius: 0 18px 18px 0">
            <i style="font-size: 15px" class="el-icon-plus"/>
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
        <tr :id="music.id === currentPlayId ? 'light' : ''" class="items" v-for="music in musics"
            @contextmenu.prevent="openMenu($event,music)"
            @dblclick="dbClick(music)">
          <td style="padding-top: 5px">
            <el-avatar :src="music.avatar" shape="square"></el-avatar>
          </td>
          <td>{{ music.name }}</td>
          <td>{{ music.author }}</td>
          <td>{{ music.album }}</td>
          <td>{{ music.time }}</td>
          <td><span class="el-icon-more" style="cursor: pointer;font-size: 20px"
                    @click.stop="openMenu($event,music)"></span></td>
        </tr>
      </table>
      <div v-if="musics.length === 0">
        <div style="text-align: center;margin-top: 100px;font-size: 40px"><i class="el-icon-box"></i></div>
        <div style="text-align: center;margin-top: 20px;font-size: 20px">暂 无 歌 曲</div>
        <div style="font-size: 10px;text-align: center;margin-top: 100px">通过 搜索歌曲 -> 播放歌曲 -> 单击右下方红心 即可收藏歌曲</div>
      </div>
    </div>
  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "Likes",
  data() {
    return {
      //收藏的音乐
      musics: [],
      showDialog: false,
      //是否已有显示的菜单
      existMenu: false,
      //IP
      serverIp: serverIp,
      //正在播放
      currentPlayId: -1,
      //用户信息
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {id: 0}
    }
  },
  computed:{
    //封面路径
    src(){
      return this.musics[0] ? this.musics[0].avatar : require('@/assets/DefaultAvatar.png')
    }
  },
  methods: {
    //获取歌曲信息
    getStars() {
      this.request.post('/user/getStars', {"userId": this.user.id.toString()}).then(res => {
        if (res.code === '200') {
          this.musics = res.data
        } else {
          this.$notify({
            type: 'error',
            title: '获取收藏列表失败',
            message: res.msg
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          message: err,
          title: '获取收藏列表失败'
        })
      })
    },
    //打开右键菜单
    openMenu(e, music) {
      if (e.x > 1200) {
        if (e.y > 500) {
          this.$bus.$emit('showMenu', e.x - 230, e.y - 200, true, music, this.list, this.musics, 'star')
        } else {
          this.$bus.$emit('showMenu', e.x - 230, e.y, true, music, this.list, this.musics, 'star')
        }
      } else {
        if (e.y > 500) {
          this.$bus.$emit('showMenu', e.x, e.y - 230, true, music, this.list, this.musics, 'star')
        } else {
          this.$bus.$emit('showMenu', e.x, e.y, true, music, this.list, this.musics, 'star')
        }
      }
      this.existMenu = true
    },
    //获取当前正在播放的音乐
    getCurrentMusic() {
      this.currentPlayId = JSON.parse(localStorage.getItem('currentMusic')) ? JSON.parse(localStorage.getItem('currentMusic')).id : ''
    },
    //双击播放
    dbClick(music) {
      this.$bus.$emit('play', music, this.musics)
    },
    //播放全部
    playAll() {
      this.dbClick(this.musics[0])
    },
    //将歌单添加至播放列表
    addAll() {
      //获取内存中的播放列表并更新
      let playList = JSON.parse(localStorage.getItem('playList'))
      //将歌单中的内容与播放列表进行拼接
      playList = playList.concat(this.musics)
      //保存播放列表
      localStorage.setItem('playList', JSON.stringify(playList))
      //通知播放列表更新
      this.$bus.$emit('getPlayList')
      //提示一下
      this.$notify({
        type: 'success',
        title: '添加成功',
        message: '已将歌单中的内容添加至播放列表'
      })
    }
  },
  mounted() {
    this.getStars()
    //获取当前正在播放的音乐，显示正在播放的音乐
    this.getCurrentMusic()
    this.$bus.$on('getCurrentPlayMusic', () => {
      this.getCurrentMusic()
    })
    this.$bus.$on('changeStar',()=>{
      this.getStars()
    })
  },
  beforeDestroy() {
    this.$bus.$off('getCurrentPlayMusic')
    this.$bus.$off('changeStar')
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
  color: var(--headerTabText)
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
  color: var(--btnText);
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
  top: 0;
  left: 0;
  z-index: -1;
  filter: blur(10px) brightness(40%);
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
  padding: 8px 12px;
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

#light {
  background-color: var(--listActive);
  color: var(--btnText)
}
</style>