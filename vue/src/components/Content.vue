<template>
    <el-container style="height: 100vh" :class="activeClass">
      <!--左边栏-->
      <LeftAside/>
      <!--右侧内容-->
      <el-container
          style="flex-direction: column;background-color: var(--rightBg)"
          @mouseenter.native="showScrollbar"
          @mouseleave.native="hideScrollbar"
      >
        <!--上边栏-->
        <Header/>
        <!--主体内容-->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
      <!--下边栏-->
      <Footer/>
    </el-container>
</template>

<script>
import Header from "@/components/Header";
import LeftAside from "@/components/LeftAside";
import Footer from "@/components/Footer";

export default {
  name: "Content",
  components: {LeftAside, Header,Footer},
  data(){
    return{
      activeClass: 'show'
    }
  },
  methods:{
    showScrollbar(){
      document.documentElement.style.setProperty('--test', 'visible');
    },
    hideScrollbar(){
      document.documentElement.style.setProperty('--test', 'hidden');
    }
  }
}
</script>

<style scoped>

.test{
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



</style>