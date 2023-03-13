<template>
  <div id="app" :class="$store.state.theme">
    <router-view/>
  </div>
</template>

<script>

import store from "@/store"

export default {
  name: 'App',
}

</script>
<style>
.dark {
  /*背景色*/
  --deepbg: orange;
  /*--deepbg: rgb(23, 23, 26);*/
  --bg: rgb(46, 46, 48);
  --deepbgRgba: rgba(23, 23, 26, 0.4);
  /*文字颜色*/
  --deeptext: #EEEEEE;
  --text: #DDDDDD;
  /*响应元素颜色*/
  --active: dodgerblue;
  --deepactive: royalblue;
  /*button背景色*/
  --button: rgb(38, 38, 41);
  /*主题色和文字颜色*/
  --color-primary: rgb(46, 46, 48);
  --color-text: #DDDDDD;
  /*覆盖默认的背景色和文字颜色*/
  background-color: var(--color-primary);
  color: var(--color-text);
  /*分割线颜色*/
  --hr: rgb(61, 61, 63);
  /*选项悬浮*/
  --hover: #444444;
}


</style>