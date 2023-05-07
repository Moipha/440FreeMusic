<template>
  <div class="menu">
    <div style="display: flex;flex-direction: row" @mouseenter="hideSecond">
      <el-avatar shape="square" :src="music.avatar"></el-avatar>
      <div style="display: flex;flex-direction: column;margin-left: 10px;font-size: 14px">
        <span style="letter-spacing: normal">{{ music.name }}</span>
        <span style="font-weight: normal;letter-spacing: normal">{{ music.author }} - {{ music.album }}</span>
      </div>
    </div>
    <hr class="hr" @mouseenter="hideSecond">
    <div class="item" @mouseenter="hideSecond">
      <i class="el-icon-caret-right icon"></i>
      <span>播放</span>
    </div>
    <div class="item" @mouseenter="hideSecond">
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
  </div>
</template>
<!--TODO 优化一下菜单的显示效果，添加动画或者淡入淡出；在边缘生成的菜单应该显示完全-->
<script>
export default {
  name: "Menu",
  data() {
    return {
      music: {},
      lists: [],
    }
  },
  methods: {
    showSecond() {
      let menu = document.getElementsByClassName('secondMenu')[0]
      menu.style.display = 'flex'
    },
    hideSecond() {
      let menu = document.getElementsByClassName('secondMenu')[0]
      menu.style.display = 'none'
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
    download(){
      window.open(this.music.url)
    },
    //复制歌名
    copy(){
      this.$copyText(this.music.name).then(()=>{
        this.$notify({
          title: '成功',
          message: '复制成功',
          type: 'success'
        })
      },()=>{
        this.$notify({
          title: '失败',
          message: '复制失败',
          type: 'error'
        })
      })
    }
  },
  mounted() {
    this.$bus.$on('showMenu', (x, y, b, music) => {
      let container = document.getElementsByClassName('menu')[0]
      if (b) {
        container.style.display = 'flex'
        container.style.left = x + 'px'
        container.style.top = y + 'px'
        this.music = music
        this.getLists()
      } else {
        container.style.display = 'none'
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
  transition: 0s;
  border-radius: 10px;
  border: 1px var(--menuBorder) solid;
  color: var(--menuText);
  backdrop-filter: blur(8px);
  flex-direction: column;
  font-size: 14px;
  font-weight: bold;
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
  background-color: var(--menuBg);
  border: solid 1px var(--menuBorder);
  left: 98%;
  top: 55%;
  border-radius: 10px;
  display: none;
  flex-direction: column;
  padding: 5px;
}

.listItem {
  padding: 10px;
  border-radius: 5px;
}

.listItem:hover {
  background-color: var(--menuHover);
}

</style>