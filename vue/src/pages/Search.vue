<template>
  <div style="display: flex;flex-direction: column">
    <el-input
        class="input"
        suffix-icon="el-icon-search"
        v-model="searchString"
        clearable
        @keydown.enter.native="search"
    ></el-input>
    <el-tabs v-model="activeName" style="width: 97%;margin: auto;">
      <el-tab-pane label="搜索结果" disabled class="dis"></el-tab-pane>
      <el-tab-pane label="单曲" name="music"/>
      <el-tab-pane label="专辑" name="album"/>
      <el-tab-pane label="歌手" name="author"/>
    </el-tabs>
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
      <tr class="items" v-for="music in tableData" @contextmenu.prevent="openMenu($event,music)">

        <td style="padding-top: 5px">
          <el-avatar :src="music.avatar" shape="square"></el-avatar>
        </td>
        <td>{{ music.name }}</td>
        <td>{{ music.author }}</td>
        <td>{{ music.album }}</td>
        <td>{{ music.time }}</td>
        <td>
          <span @click.stop="openMenu($event,music)" class="el-icon-more" style="cursor: pointer;font-size: 20px"></span>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "Search",
  data() {
    return {
      activeName: 'music',
      //从header搜索框获取的数据
      searchString: '',
      //表格数据
      tableData: [],
      //加载中
      loading: false,
      //是否已有显示的菜单
      existMenu: false,
    }
  },
  mounted() {
    this.$bus.$on('changeSearchStr', (str) => {
      this.searchString = str
      this.getTableData()
    })
    this.searchString = this.$route.query.searchStr
    this.getTableData()

    //点击其他区域关闭菜单
    document.addEventListener('click', e => {
      e.stopPropagation();
      const menu = document.querySelector('.menu');
      if (!menu.contains(e.target)) {
        this.$bus.$emit('showMenu', 0, 0, false, {})
        this.existMenu = false
      }
    })
  },
  methods: {
    // 获取表格数据
    getTableData() {
      this.loading = true
      //发送请求获取数据
      this.request.post('/music/searchByName/', {name: this.searchString}).then(res => {
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
    search() {
      this.getTableData()
      this.$router.push({path: '/search', query: {searchStr: this.searchString}})
    },
    openMenu(e, music) {
      this.$bus.$emit('showMenu', e.x, e.y, true, music)
      this.existMenu = true
    }

  },
  beforeDestroy() {
    this.$bus.$off('changeSearchStr')
  }
}
</script>

<style scoped>
.input {
  width: 400px;
  margin: 10px auto;
  justify-content: center;
  align-items: center;
  border: solid 1px var(--searchInputBorder);
}

/deep/ .el-input__inner {
  color: var(--searchInputText);
  background-color: var(--searchInputBg);
  border: none;
}

/deep/ .el-input__icon {
  color: var(--searchInputText);
}

/deep/ .el-tabs__item {
  color: var(--searchTabText);
}

/*搜索结果文字样式*/
/deep/ #tab-0 {
  font-size: 20px !important;
  font-weight: bold;
  padding: 0 40px 0 0;
}

/deep/ #tab-0:hover {
  color: var(--searchTabText);
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
  height: 3px;
  border-radius: 5px;
  background-color: var(--mineTabHover);
}

/deep/ .el-tabs__nav {
  position: static;
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

</style>