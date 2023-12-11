<template>
  <el-container style="height: 100vh">
    <!--左边栏-->
    <LeftAside @mouseenter.native="showLeftScrollbar"
               @mouseleave.native="hideLeftScrollbar"/>
    <!--右侧内容-->
    <el-container
        style="flex-direction: column;background-color: var(--rightBg);position: relative;z-index: 1"
        @mouseenter.native="showScrollbar"
        @mouseleave.native="hideScrollbar"
    >

      <!--主体内容-->
      <el-main style="padding:0">
        <!--上边栏-->
        <Header/>
        <transition name="scale" mode="out-in" appear>
          <router-view style="padding: 80px 20px 20px 20px"></router-view>
        </transition>
        <!--右边栏-->
        <RightAside/>
      </el-main>
    </el-container>
    <!--下边栏-->
    <Footer/>
    <!--菜单栏-->
    <Menu></Menu>
    <!--搜索栏-->
    <SearchWindow/>
  </el-container>
</template>

<script>
import Header from "@/components/Header";
import LeftAside from "@/components/LeftAside";
import Footer from "@/components/Footer";
import RightAside from "@/components/RightAside";
import Menu from "@/components/Menu";
import SearchWindow from "@/components/SearchWindow";

export default {
  name: "Content",
  components: {Menu, LeftAside, Header, Footer, RightAside, SearchWindow},
  methods: {
    showScrollbar() {
      document.documentElement.style.setProperty('--test', 'visible');
    },
    hideScrollbar() {
      document.documentElement.style.setProperty('--test', 'hidden');

    },
    showLeftScrollbar() {
      document.documentElement.style.setProperty('--notTest', 'visible');
    },
    hideLeftScrollbar() {
      document.documentElement.style.setProperty('--notTest', 'hidden');
    }
  },

}
</script>

<style scoped>

.test {
  --test: visible;
  --notTest: hidden;
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


.scale-enter-active {
  transition: all 0.2s ease;
  opacity: 0;
  transform: scale(0.95);
}

.scale-leave-active {
  transition: all 0.2s ease;
  opacity: 0;
  transform: scale(0.95);
}


</style>