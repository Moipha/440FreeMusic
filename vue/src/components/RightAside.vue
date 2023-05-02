<template>
  <el-aside class="container"
            @mouseenter.native="showScrollbar"
            @mouseleave.native="hideScrollbar"
  >
    <div ref="test" class="right">
      <div style="flex-direction: row;display: flex">
        <div class="tabContainer">
          <span ref="tab1" @click="change1" class="tabChoice"
                style="float: left;margin-left: 5px;background-color: var(--rightTab)">当前播放</span>
          <span ref="tab2" @click="change2" class="tabChoice" style="float: right;margin-right: 5px">播放历史</span>
        </div>
        <div class="close" @click="closeRight"><i class="el-icon-close"></i></div>
      </div>
      <div style="padding: 5px;font-size: 13px;font-weight: bold;color: var(--rightLightText)">
        <span style="padding: 5px 10px;float: left">总 {{ tableData.length }} 首</span>
        <span slot="reference" class="clear" @click="dialogVisible=true">清空列表</span>
        <el-dialog :visible.sync="dialogVisible"
                   :show-close="false"
                   :append-to-body="false"
                   :modal="false">
          <div style="display: flex;flex-direction: row">
            <i class="el-icon-warning" style="color: red;font-size: 20px;line-height: 20px;margin-right: 5px"></i>
            <span style="color: var(--rightLightText);line-height: 20px">确认清除列表？</span>
          </div>
          <div style="display: flex;flex-direction: row;color: var(--rightAsideText)">
            <span class="dialogBtn" @click="clear">确认</span>
            <span class="dialogBtn" @click="dialogVisible=false">取消</span>
          </div>
        </el-dialog>
      </div>
      <div style="overflow-y: hidden">
        <!--当前播放-->
        <div ref="content1" class="content">
          <div v-for="item in tableData" class="item">
            <el-avatar style="margin-right: 10px" size="medium" shape="square" :src="item.avatar"/>
            <div style="display: flex;flex-direction: column">
              <span style="line-height: 18px;height: 18px;font-weight: bold;font-size: 15px">{{ item.name }}</span>
              <span style="line-height: 18px;height: 18px;font-size: 13px">{{ item.author }} - {{ item.album }}</span>
            </div>
            <span class="el-icon-more more"></span>
          </div>
        </div>
        <!--播放历史-->
        <div ref="content2" class="content" style="display: none">
          播放历史
        </div>
      </div>
    </div>
  </el-aside>
</template>

<script>
export default {
  name: "RightAside",
  data() {
    return {
      tableData: [
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
        {
          avatar: 'http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg',
          name: 'testName',
          album: 'testAlbum',
          author: 'testAuthor'
        },
      ],
      dialogVisible: false,
    }
  },
  mounted() {
    this.$bus.$on('changeRightAside', () => {
      this.$nextTick(() => {
        //弹出和隐藏右边栏
        if (this.$refs.test.style.right === '0%') {
          this.$refs.test.style.right = '-400px'
        } else {
          this.$refs.test.style.right = '0%'
        }
      })
    })
    //点击其他区域关闭右边栏
    document.addEventListener('click', e => {
      if (!this.$el.contains(e.target)) {
        this.$refs.test.style.right = '-400px'
      }
    })
  },
  beforeDestroy() {
    this.$bus.$off('changeRightAside')
  },
  methods: {
    //切换为当前播放列表
    change1() {
      this.$refs.tab1.style.backgroundColor = 'var(--rightTab)'
      this.$refs.tab2.style.backgroundColor = 'var(--rightTabBg)'
      this.$refs.content1.style.display = 'block'
      this.$refs.content2.style.display = 'none'
    },
    //切换为播放历史列表
    change2() {
      this.$refs.tab2.style.backgroundColor = 'var(--rightTab)'
      this.$refs.tab1.style.backgroundColor = 'var(--rightTabBg)'
      this.$refs.content2.style.display = 'block'
      this.$refs.content1.style.display = 'none'
    },
    //关闭右边栏
    closeRight() {
      this.$refs.test.style.right = '-400px'
    },
    //滚动条相关
    showScrollbar() {
      document.documentElement.style.setProperty('--test', 'visible');
    },
    hideScrollbar() {
      document.documentElement.style.setProperty('--test', 'hidden');
    },
    //清除记录
    clear() {
      this.tableData = []

      this.dialogVisible = false
    }
  }
}
</script>

<style scoped>
.container {
  width: 0 !important;
  height: 0;
}

.right {
  width: 360px !important;
  height: calc(100% - 100px) !important;
  padding-top: 70px;
  position: absolute;
  right: -400px;
  /*right: 0;*/
  bottom: 100px;
  background-color: var(--rightAsideBg);
  transition: 0.3s ease-in-out;
  z-index: 0;
  color: var(--rightAsideText);
  display: flex;
  flex-direction: column;
}

.tabContainer {
  background-color: var(--rightTabBg);
  height: 40px;
  width: 80%;
  border-radius: 20px;
  margin-left: 10px;
}

.tabChoice {
  display: inline-block;
  width: 47%;
  margin-top: 5px;
  margin-bottom: 5px;
  height: 30px;
  border-radius: 18px;
  line-height: 30px;
  text-align: center;
  font-size: 12px;
  font-weight: bold;
  cursor: pointer;
}

.tabChoice:hover {
  background-color: var(--rightTab) !important;
}

.close {
  background-color: var(--rightTabBg);
  width: 40px;
  border-radius: 50%;
  height: 40px;
  padding: 10px;
  display: inline-block;
  margin-left: 5px;
  text-align: center;
  cursor: pointer;
}

.close:hover {
  background-color: var(--rightTab);
}

.content {
  height: 100%;
  padding: 10px;
  overflow-y: scroll;
}

/*滚动条相关*/
.test {
  --test: visible;
}

::-webkit-scrollbar {
  overflow: auto;
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background-color: var(--scrollbar);
  border-radius: 5px;
  visibility: var(--test);

}

::-webkit-scrollbar-thumb:hover {
  background-color: var(--scrollbarHover);
}

::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background-color: var(--rightBg);
  border-radius: 5px;
}

/*滚动条相关截止*/


.clear {
  float: right;
  padding: 5px 10px;
  cursor: pointer;
}

.clear:hover {
  background-color: var(--rightHover);
}

.more {
  height: 16px;
  margin-left: auto;
  cursor: pointer;
  padding: 10px;
  border-radius: 50%;
}

.more:hover {
  background-color: var(--rightHover);
}

.item {
  display: flex;
  flex-direction: row;
  height: 55px;
  padding: 10px;
  border-radius: 10px;
}

.item:hover {
  background-color: var(--rightTabBg);
}

/*弹出确认框相关*/
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

/deep/ .el-dialog {
  background-color: var(--dialogBg);
  height: 72px;
  width: 150px;
  position: absolute;
  right: 50px;
  display: flex;
  flex-direction: column;
  padding: 10px 10px 0;
}

/deep/ .el-dialog__body {
  padding: 0;
}

/deep/ .el-dialog__header {
  height: 0;
  padding: 0;
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

.dialogBtn {
  font-size: 12px !important;
  background-color: var(--rightHover);
  padding: 5px 10px;
  border-radius: 3px;
  margin: 8px auto 0;
  cursor: pointer;
}

.dialogBtn:hover {
  background-color: var(--rightTab);
}


</style>