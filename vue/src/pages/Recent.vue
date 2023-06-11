<template>
  <div style="overflow-y: scroll;height: 624px"
       @mouseenter="showScrollbar"
       @mouseleave="hideScrollbar">
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
      <tr :id="music.id === currentPlayId ? 'light' : ''" class="items" v-for="music in tableData"
          @contextmenu.prevent="openMenu($event,music)"
          @dblclick="dbClick(music)">

        <td style="padding-top: 5px">
          <el-avatar :src="music.avatar" shape="square"></el-avatar>
        </td>
        <td>{{ music.name }}</td>
        <td>{{ music.author }}</td>
        <td>{{ music.album }}</td>
        <td>{{ music.time }}</td>
        <td>
          <span @click.stop="openMenu($event,music)" class="el-icon-more"
                style="cursor: pointer;font-size: 20px"></span>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "recent",
  data() {
    return {
      //表格数据
      tableData: [],
      //是否已有显示的菜单
      existMenu: false,
      //正在播放
      currentPlayId: -1,
      //加载中
      loading: false,
    }
  },
  methods: {
    //切换滚动条状态
    showScrollbar() {
      document.documentElement.style.setProperty('--test4', 'visible');
    },
    hideScrollbar() {
      document.documentElement.style.setProperty('--test4', 'hidden');
    },
    //打开右键菜单
    openMenu(e, music) {
      if (e.x > 1200) {
        if (e.y > 500) {
          this.$bus.$emit('showMenu', e.x - 230, e.y - 200, true, music, {}, this.tableData, 'search')
        } else {
          this.$bus.$emit('showMenu', e.x - 230, e.y, true, music, {}, this.tableData, 'search')
        }
      } else {
        if (e.y > 500) {
          this.$bus.$emit('showMenu', e.x, e.y - 230, true, music, {}, this.tableData, 'search')
        } else {
          this.$bus.$emit('showMenu', e.x, e.y, true, music, {}, this.tableData, 'search')
        }
      }
      this.existMenu = true
    },
    //获取当前正在播放的音乐
    getCurrentMusic() {
      this.currentPlayId = JSON.parse(localStorage.getItem('currentMusic')).id
    },
    //双击播放
    dbClick(music) {
      this.$bus.$emit('play', music, this.tableData)
    },
    // 获取表格数据
    getTableData() {
      this.loading = true
      //发送请求获取数据
      this.request.get('/music/getAll' ).then(res => {
        if (res.code !== '200') {
          this.$notify({
            title: '查询数据失败',
            message: res.msg,
            type: 'error'
          })
          this.loading = false
        } else {
          this.tableData = res.data
          this.loading = false
        }
      }).catch(err => {
        this.$notify({
          title: '查询数据失败',
          message: err,
          type: 'error'
        })
        this.loading = false
      })
    },
  },
  mounted() {
    this.getTableData()
    //获取当前正在播放的音乐，显示正在播放的音乐
    this.getCurrentMusic()
    this.$bus.$on('getCurrentPlayMusic', () => {
      this.getCurrentMusic()
    })
  },
  beforeDestroy() {
    this.$bus.$off('getCurrentPlayMusic')
  }
}
</script>

<style scoped>
/*滚动条*/

.test4 {
  --test4: visible;
}

::-webkit-scrollbar {
  overflow: auto;
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background-color: var(--scrollbar);
  border-radius: 5px;
  visibility: var(--test4);

}

::-webkit-scrollbar-thumb:hover {
  background-color: var(--scrollbarHover);
}

::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background-color: var(--rightBg);
  border-radius: 5px;
}


/deep/ .el-loading-mask {
  background-color: var(--maskBg);
  height: 100%;
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

#light {
  background-color: var(--listActive);
}

</style>