<template>
  <div>
    <div v-if="!$route.path.endsWith('recent')" style="color: var(--homeText);font-family: 华文宋体,serif">
      <div>
        <h1 style="font-size: 25px">歌单推荐</h1>
        <div style="display: flex;flex-direction: row">
          <div v-for="l in lists" style="display: flex;flex-direction: column;margin-right: 20px">
            <el-avatar :src="l.avatar?l.avatar:require('@/assets/DefaultAvatar.png')" shape="square"
                       style="height: 150px;width: 150px;margin-bottom: 10px"></el-avatar>
            <span style="font-weight: bold;font-size: 20px">{{ l.title }}</span>
            <span>创建于 {{ l.createTime }}</span>
          </div>
        </div>
      </div>
      <el-divider></el-divider>
      <div style="display: flex;flex-direction: row">
        <div style="width: 48%;margin-right: 4%">
          <h1 style="font-size: 25px">单曲推荐</h1>
          <table>
            <tr style="color: var(--listTh);font-size: 14px">
              <th style="width: 10%" align="center"></th>
              <th align="left" style="width: 30%;">音乐标题</th>
              <th align="left" style="width: 20%">歌手</th>
              <th align="left" style="width: 20%">专辑</th>
              <th align="left" style="width: 10%">时长</th>
              <th align="left" style="width: 10%;"></th>
            </tr>
            <tr style="height: 20px"></tr>
            <tr :id="music.id === currentPlayId ? 'light' : ''" class="items" v-for="music in tableData"
                @contextmenu.prevent="openMenu($event,music)"
                @dblclick="dbClick(music,tableData)">

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
        <div style="width: 48%;">
          <h1 style="font-size: 25px">
            <span @click="$router.push('/home/recent')" style="margin-right: 10px;cursor: pointer;">最新上传</span>
            <span @click="$router.push('/home/recent')" class="el-icon-arrow-right"
                  style="font-size: 18px;font-weight: bold;cursor: pointer;"></span>
          </h1>
          <table>
            <tr style="color: var(--listTh);font-size: 14px">
              <th style="width: 10%" align="center"></th>
              <th align="left" style="width: 35%;">音乐标题</th>
              <th align="left" style="width: 20%">歌手</th>
              <th align="left" style="width: 20%">专辑</th>
              <th align="left" style="width: 10%">时长</th>
              <th align="left" style="width: 10%;"></th>
            </tr>
            <tr style="height: 20px"></tr>
            <tr :id="music.id === currentPlayId ? 'light' : ''" class="items" v-for="music in recentTable"
                @contextmenu.prevent="openMenu($event,music)"
                @dblclick="dbClick(music,recentTable)">

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
      </div>
      <el-divider></el-divider>
      <div style="margin-bottom: 100px">
        <h3>更新日志</h3>
        <h5>2023/6/11</h5>
        <h6>基本实现目前所有功能，即将正式上线</h6>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      //推荐歌单
      lists: [],
      //表格数据
      tableData: [],
      recentTable: [],
      //是否已有显示的菜单
      existMenu: false,
      //正在播放
      currentPlayId: -1,
      //加载中
      loading: false,
    }
  },
  mounted() {
    //获取单曲推荐和最新上传的信息
    this.getTableData()
    //获取当前正在播放的音乐，显示正在播放的音乐
    this.getCurrentMusic()
    this.$bus.$on('getCurrentPlayMusic', () => {
      this.getCurrentMusic()
    })
  },
  methods: {
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
    dbClick(music, list) {
      this.$bus.$emit('play', music, list)
      this.getCurrentMusic()
    },
    // 获取表格数据
    getTableData() {
      this.loading = true
      //发送请求获取数据
      this.request.get('/music/getAll').then(res => {
        if (res.code !== '200') {
          this.$notify({
            title: '查询数据失败',
            message: res.msg,
            type: 'error'
          })
          this.loading = false
        } else {
          //截取一下，只显示后七个
          if (res.data.length <= 7) {
            this.recentTable = res.data.reverse()
          } else {
            this.recentTable = res.data.slice(-7).reverse();
          }
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

      //再获取一下单曲推荐
      this.request.get('/music/getRandom').then(res => {
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

      //再获取一下歌单推荐
      this.request.get('/list/getAll').then(res => {
        if (res.code !== '200') {
          this.$notify({
            title: '查询数据失败',
            message: res.msg,
            type: 'error'
          })
          this.loading = false
        } else {
          this.lists = res.data
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
  }
}
</script>

<style scoped>
/deep/ .el-divider {
  background-color: var(--settingHr);
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

.items * {
  letter-spacing: normal;
  font-family: Arial;
  font-size: 15px;
  margin: 2px 8px;
}

.items:hover {
  background: var(--searchHover);
}
</style>