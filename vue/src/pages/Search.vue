<template>
  <div style="display: flex;flex-direction: column">
    <el-input
        class="input"
        suffix-icon="el-icon-search"
        v-model="searchString"
        clearable
    ></el-input>
    <el-tabs v-model="activeName" style="width: 1150px;margin: auto;">
      <el-tab-pane label="搜索结果" disabled class="dis"></el-tab-pane>
      <el-tab-pane label="单曲" name="music"/>
      <el-tab-pane label="专辑" name="album"/>
      <el-tab-pane label="歌手" name="author"/>
    </el-tabs>
    <el-table
        ref="singleTable"
        :data="tableData"
        highlight-current-row
        @current-change="handleCurrentChange"
        style="width: 1150px;margin: auto"
    >
      <el-table-column
          property="avatar"
          width="50"
      >
      </el-table-column>
      <el-table-column
          property="name"
          label="音乐标题">
      </el-table-column>
      <el-table-column
          property="author"
          label="歌手"
          width="250">
      </el-table-column>
      <el-table-column
          property="album"
          label="专辑"
          width="250">
      </el-table-column>
      <el-table-column
          property="time"
          label="时长"
          width="60">
      </el-table-column>
      <el-table-column
          property="edit"
          width="50">
        <span class="el-icon-more" style="cursor: pointer;font-size: 20px"></span>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      activeName: 'music',
      currentRow: null,
      //从header搜索框获取的数据
      searchString: '',
      tableData: [
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },
        {
          name: 'test1',
          author: '张三',
          album: '专辑1',
          time: '02:12'
        },

      ]
    }
  },
  mounted() {
    this.$bus.$on('changeSearchStr', (str) => {
      this.searchString = str
    })
    this.searchString = this.$route.query.searchStr
  },
  methods: {
    //表格所选行变动
    setCurrent(row) {
      this.$refs.singleTable.setCurrentRow(row);
    },
    handleCurrentChange(val) {
      this.currentRow = val;
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
/deep/ .el-tabs__nav{
  position: static;
}

/*表格样式修改：*/
/*表头背景*/
/deep/ .el-table th {
  background-color: var(--rightBg);
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
  padding: 15px 0;
}

/*滚动条*/
::v-deep .el-table--scrollable-x .el-table__body-wrapper {
  display: none !important;
}

/deep/ .el-table, .el-table__expanded-cell {
  background-color: var(--rightBg);
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



</style>