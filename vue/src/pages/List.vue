<template>
  <div class="container">
    <div class="background"></div>
    <div class="top">
      <el-avatar class="left" shape="square" :size="200"
                 src="http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg"></el-avatar>
      <div class="right">
        <div style="font-size: 30px;font-weight: normal;margin-top: 40px;margin-bottom: 20px">
          {{ listTitle }}
          <el-tooltip content="编辑" placement="top">
            <span class="el-icon-edit edit"></span>
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
      <table style="width: 100%;">
        <tr style="color: var(--listTh);font-size: 14px">
          <th style="width: 5%" align="center"></th>
          <th align="left" style="width: 40%;">音乐标题</th>
          <th align="left" style="width: 20%;padding-left: 50px">歌手</th>
          <th align="left" style="width: 20%;padding-left: 50px">专辑</th>
          <th align="left" style="width: 5%;">时长</th>
          <th align="left" style="width: 5%;"></th>
        </tr>
        <tr v-for="music in musics">
          <td>
            <el-avatar src="http://localhost:8080/avatar/72f648610357499382b34899ceb65d02.jpg"
                       shape="square"></el-avatar>
          </td>
          <td>{{ music.name }}</td>
          <td>{{ music.author }}</td>
          <td>{{ music.album }}</td>
          <td>{{ music.time }}</td>
          <td>{{}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "List",
  data() {
    return {
      listTitle: this.$route.query.listTitle,
      list: {},
      author: {},
      musics: [],
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
      this.request.post('/list/getMusics/' + this.list.id).then(res=>{
        if(res.code ==='200'){
          this.musics = res.data
        }else{
          this.$notify({
            type: 'error',
            title: '获取歌曲失败',
            message: res.msg
          })
        }
      }).catch(err=>{
        this.$notify({
          type: 'error',
          title: '获取歌曲失败',
          message: err
        })
      })
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
</style>