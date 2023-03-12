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
.black {
  /*背景色*/
  --deepbg: #111111;
  --bg: #333333;
  /*文字颜色*/
  --deeptext: #EEEEEE;
  --text: #DDDDDD;
  /*响应元素颜色*/
  --active: dodgerblue;
  --deepactive: royalblue;
  /*主题色和文字颜色*/
  --color-primary: #333333;
  --color-text: #DDDDDD;
  /*覆盖默认的背景色和文字颜色*/
  background-color: var(--color-primary);

  color: var(--color-text);
}


</style>