<template>
  <el-container style="height: 100vh">
    <!--左边栏-->
    <LeftAside/>
    <!--右侧内容-->
    <el-container style="flex-direction: column;background-color: var(--rightBg)">
      <!--上边栏-->
      <Header/>
      <!--主体内容-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Header from "@/components/Header";
import LeftAside from "@/components/LeftAside";

export default {
  name: "Content",
  components: {LeftAside, Header},
}
</script>

<style scoped>

</style>