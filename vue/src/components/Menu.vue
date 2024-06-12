<template>
  <div class="menu">
    <!--公共部分-->
    <div style="display: flex;flex-direction: row">
      <el-avatar shape="square" :src="music.avatar"></el-avatar>
      <div style="display: flex;flex-direction: column;margin-left: 10px;font-size: 14px">
        <span style="letter-spacing: normal">{{ music.name }}</span>
        <span style="font-weight: normal;letter-spacing: normal">{{ music.author }} - {{ music.album }}</span>
      </div>
    </div>
    <hr class="hr">
    <!--不同部分-->
    <template v-if="mode==='star'">
      <div class="item" @mouseenter="hideSecond" @click="play">
        <i class="el-icon-caret-right icon"></i>
        <span>播放</span>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="nextPlay">
        <i class="el-icon-d-arrow-right icon"></i>
        <span>下一首播放</span>
      </div>
      <hr class="hr" @mouseenter="hideSecond">
      <div class="item" @mouseenter="showSecond">
        <i class="el-icon-star-off icon"></i>
        <span>收藏到歌单</span>
        <span style="float: right"> <i class="el-icon-arrow-right" style="font-weight: bold"></i> </span>
        <div class="secondMenu" @mouseenter="showSecond">
          <div class="listItem" v-for="list in lists" @click="star(list)">
            {{ list.title }}
          </div>
        </div>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="copy">
        <i class="el-icon-copy-document icon"></i>
        <span>复制歌名</span>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="download">
        <i class="el-icon-download icon"></i>
        <span>下载</span>
      </div>
    </template>
    <template v-if="mode==='search'">
      <div class="item" @mouseenter="hideSecond" @click="play">
        <i class="el-icon-caret-right icon"></i>
        <span>播放</span>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="nextPlay">
        <i class="el-icon-d-arrow-right icon"></i>
        <span>下一首播放</span>
      </div>
      <hr class="hr" @mouseenter="hideSecond">
      <div class="item" @mouseenter="showSecond">
        <i class="el-icon-star-off icon"></i>
        <span>收藏到歌单</span>
        <span style="float: right"> <i class="el-icon-arrow-right" style="font-weight: bold"></i> </span>
        <div class="secondMenu" @mouseenter="showSecond">
          <div class="listItem" v-for="list in lists" @click="star(list)">
            {{ list.title }}
          </div>
        </div>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="copy">
        <i class="el-icon-copy-document icon"></i>
        <span>复制歌名</span>
      </div>
      <div class="item" @mouseenter="hideSecond" @click="download">
        <i class="el-icon-download icon"></i>
        <span>下载</span>
      </div>
    </template>
    <template v-else-if="mode==='list'">
      <div class="item" @click="play">
        <i class="el-icon-caret-right icon"></i>
        <span>播放</span>
      </div>
      <div class="item" @click="nextPlay">
        <i class="el-icon-d-arrow-right icon"></i>
        <span>下一首播放</span>
      </div>
      <hr class="hr">
      <div class="item" @click="deleteStar">
        <i class="el-icon-delete icon"></i>
        <span>取消收藏</span>
      </div>
      <div class="item" @click="copy">
        <i class="el-icon-copy-document icon"></i>
        <span>复制歌名</span>
      </div>
      <div class="item" @click="download">
        <i class="el-icon-download icon"></i>
        <span>下载</span>
      </div>
    </template>
    <template v-else>

    </template>
  </div>
</template>
<script>
export default {
  name: "Menu",
  data() {
    return {
      //具体为哪个音乐的菜单
      music: {},
      //该用户的歌单列表
      lists: [],
      //具体显示哪个菜单项，search为搜索页面，list为歌单页面，mine为上传页面。
      mode: '',
      //目前操作的歌单
      list: {},
      //目前歌单中的内容
      musics: []
    }
  },
  methods: {
    showSecond() {
      this.$nextTick(() => {
        let menu = document.getElementsByClassName('secondMenu')[0]
        if (menu.style !== null) {
          menu.style.display = 'flex'
          menu.style.animationDuration = '0.2s'
          menu.style.animationName = 'in'
          menu.style.backdropFilter = 'blur(10px)'
        }
      })
    },
    hideSecond() {
      this.$nextTick(() => {
        let menu = document.getElementsByClassName('secondMenu')[0]
        if (menu.style !== null) {
          menu.style.display = 'none'
          menu.style.animationDuration = '0.2s'
          menu.style.animationName = 'out'
        }
      })
    },
    //获取该用户的所有歌单列表
    getLists() {
      this.request.get('/user/getLists/' + JSON.parse(localStorage.getItem('user')).id,).then(res => {
        if (res.code === '200') {
          this.lists = res.data
        } else {
          this.$notify({
            title: '获取歌单列表失败',
            message: res.msg,
            type: 'error'
          })
        }
      }).catch(err => {
        this.$notify({
          title: '获取歌单列表失败',
          message: err,
          type: 'error'
        })
      })
    },
    //收藏至指定歌单
    star(list) {
      this.request.post('/list/saveMusic', {"listId": list.id, "musicId": this.music.id}).then(res => {
        if (res.code === '200') {
          this.$notify({
            title: '添加成功',
            message: `${this.music.name}已收藏至${list.title}`,
            type: 'success'
          })
        } else {
          this.$notify({
            type: 'error',
            title: '添加失败',
            message: res.msg
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          title: '添加失败',
          message: err
        })
      })
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    },
    //下载歌曲
    download() {
      window.open(this.music.url)
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    },
    //复制歌名
    copy() {
      this.$copyText(this.music.name).then(() => {
        this.$notify({
          title: '成功',
          message: '复制成功',
          type: 'success'
        })
      }, () => {
        this.$notify({
          title: '失败',
          message: '复制失败',
          type: 'error'
        })
      })
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    },
    //播放
    play() {
      this.$bus.$emit('play', this.music, this.musics)
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    },
    //删除收藏
    deleteStar() {
      this.request.post('/list/deleteMusic', {"listId": this.list.id, "musicId": this.music.id}).then(res => {
        if (res.code === '200') {
          this.$notify({
            title: '已取消收藏',
            message: `${this.music.name}已于${this.list.title}中删除`,
            type: 'success'
          })
          //刷新一下
          this.$bus.$emit('changeList')
        } else {
          this.$notify({
            type: 'error',
            title: '删除失败',
            message: res.msg
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          title: '删除失败',
          message: err
        })
      })
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    },
    //下一首播放
    nextPlay() {
      //获取当前播放列表
      const item = localStorage.getItem('playList')
      let list = item ? JSON.parse(item) : [this.music]
      if (item) {
        //不为空，将其添加至第二位
        //先检查列表中是否有该曲，有的话先删了
        list = list.filter(m => {
          return m.id !== this.music.id
        })
        list.splice(1, 0, this.music)
      }
      //保存
      localStorage.setItem('playList', JSON.stringify(list))
      //修改内存后需要调用
      this.$bus.$emit('getCurrentPlayMusic')
      this.$bus.$emit('getPlayList')
      //操作结束后关闭菜单
      this.$bus.$emit('showMenu', 0, 0, false, {})
    }
  },
  mounted() {
    this.$bus.$on('showMenu', (x, y, b, music, list, musics, mode) => {
      let container = document.querySelector('.menu')
      if (container.style !== null) {
        if (b) {
          //如果菜单已经打开，那就先关闭再打开
          if (container.style.display === 'flex') {
            container.style.animationDuration = '0.1s'
            container.style.animationName = 'out'
            setTimeout(() => {
              container.style.display = 'none'
              container.style.display = 'flex'
              //打开
              container.style.left = x + 'px'
              container.style.top = y + 'px'
              container.style.animationName = 'in'
              //设置数据
              this.music = music
              this.list = list
              this.mode = mode
              this.musics = musics
              this.getLists()
            }, 100)
          } else {
            container.style.animationDuration = '0.2s'
            container.style.display = 'flex'
            //打开
            container.style.left = x + 'px'
            container.style.top = y + 'px'
            container.style.animationName = 'in'
            //设置数据
            this.music = music
            this.list = list
            this.mode = mode
            this.musics = musics
            this.getLists()
          }
        } else {
          container.style.animationDuration = '0.2s'
          container.style.animationName = 'out'
          setTimeout(() => {
            container.style.display = 'none'
          }, 200)
        }
      }
    })
  },
  beforeDestroy() {
    this.$bus.$off('showMenu')
  }
}
</script>

<style scoped>
.menu {
  display: none;
  position: absolute;
  width: 240px;
  padding: 10px;
  background: var(--menuBg);
  z-index: 1;
  border-radius: 10px;
  border: 1px var(--menuBorder) solid;
  color: var(--menuText);
  backdrop-filter: blur(8px);
  flex-direction: column;
  font-size: 14px;
  font-weight: bold;
  animation: in 0.2s forwards;
  transition: 0s;
}

.item {
  padding: 8px;
  border-radius: 3px;
  font-size: 13px;
  cursor: pointer;
}

.item:hover {
  background-color: var(--menuHover);
}

.icon {
  font-size: 15px;
  margin-right: 15px;
  font-weight: bold;
}

.hr {
  width: 100%;
  border: var(--menuBorder) solid 1px;
  margin-top: 10px;
}

.secondMenu {
  position: absolute;
  width: 160px;
  /*height: 200px;*/
  /*overflow-y: scroll;*/
  background-color: var(--menuBg);
  border: solid 1px var(--menuBorder);
  left: 103%;
  top: 55%;
  border-radius: 10px;
  display: none;
  flex-direction: column;
  padding: 5px;
  backdrop-filter: blur(10px);
}

.listItem {
  padding: 10px;
  border-radius: 5px;
}

.listItem:hover {
  background-color: var(--menuHover);
}

</style>
<style>
@keyframes in {
  from {
    opacity: 0
  }
  to {
    opacity: 1
  }
}

@keyframes out {
  from {
    opacity: 1
  }
  to {
    opacity: 0
  }
}

::-webkit-scrollbar {
  overflow: auto;
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background-color: var(--scrollbar);
  border-radius: 5px;
}

::-webkit-scrollbar-thumb:hover {
  background-color: var(--scrollbarHover);
}

::-webkit-scrollbar-track {
  background-color: rgba(0,0,0,0);
  border-radius: 5px;
}
</style>