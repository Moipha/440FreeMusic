<template>
  <div id="app" :class="$store.state.theme">
    <router-view/>
  </div>
</template>

<script>

//testxiesonghao

import store from "@/store"

export default {
  name: 'App',
}

</script>
<style>

.Dark {
  /*总体*/
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --rightBg: rgb(23, 23, 26);
  /*左边栏*/
  --leftbg: rgb(46, 46, 48);
  --leftText: var(--whiteText);
  --leftBtnActive: royalblue;
  --leftBtnHover: #444444;
  /*header*/
  --headerBg: rgba(23, 23, 26, 0.4);
  --headerBtn: var(--leftbg);
  --headerBtnHover: #9f9f9f;
  --headerText: var(--whiteText);
  --headerSearchBg: var(--headerBtn);
  --headerTabText: var(--leftText);
  --headerTabHover: var(--leftBtnActive);
  --headerInputText: var(--lightBlackText);
  /*dialog*/
  --hr: rgb(61, 61, 63);
  --dialogBg: var(--leftbg);
  --dialogText: var(--headerText);
  /*setting*/
  --settingText: var(--whiteText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: var(--leftBtnHover);
  --settingBtnText: var(--lightWhiteText);
  --settingBtnHover: #535355;
  --settingHr: var(--hr);
  /*个人中心*/
  --mineText: var(--whiteText);
  --mineCardBg: var(--leftBtnActive);
  --mineTabText: var(--mineText);
  --mineTabHover: var(--leftBtnActive);
  --mineAvatarBg: var(--whiteText);
  --mineAvatarText: var(--blackText);
  /*登录注册*/
  --loginA: var(--mineCardBg);
  --loginBtn: var(--loginA);
  --loginBg: var(--mineTabHover);
  --loginInputBg: var(--leftbg);
  --loginInputText: var(--lightBlackText);
  --loginInputActive: var(--mineCardBg);
  --loginInputBorder: rgba(0,0,0,0);
  --loginText: var(--whiteText);
  --loginBtnHover: dodgerblue;
  --loginAHover: var(--blackText);
}

.Orange {
  /*背景色*/
  --leftbg: black;
  --deepbg: orange;
  --bg: rgb(46, 46, 48);
  --deepbgRgba: rgba(23, 23, 26, 0.4);
  /*文字颜色*/
  --deeptext: #EEEEEE;
  --text: #DDDDDD;
  /*响应元素颜色*/
  --active: #FFAA00;
  --deepactive: orange;
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
  /*输入框字体颜色*/
  --input: #AAAAAA;
  /*左边栏字体*/
  --leftText: var(--color-text)
}

.Light {
  /*总体*/
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --rightBg: #E9DABB;
  /*左边栏*/
  --leftbg: #001345;
  --leftText: #EEEEEE;
  --leftBtnActive: #B4A117;
  --leftBtnHover: #005283;
  /*header*/
  --headerBg: rgba(0, 19, 69, 0.6);
  --headerBtn: #DBD7D6;
  --headerBtnHover: #9f9f9f;
  --headerText: #333333;
  --headerSearchBg: var(--headerBtn);
  --headerTabText: var(--leftText);
  --headerTabHover: var(--leftBtnActive);
  --headerInputText: var(--lightBlackText);
  /*dialog*/
  --hr: #FFFFFF;
  --dialogBg: var(--headerBtn);
  --dialogText: var(--headerText);
  /*setting*/
  --settingText: var(--blackText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: var(--leftBtnHover);
  --settingBtnText: var(--whiteText);
  --settingBtnHover: #006bad;
  --settingHr: var(--leftbg);
  /*个人中心*/
  --mineText: var(--blackText);
  --mineCardBg: var(--leftBtnActive);
  --mineTabText: var(--mineText);
  --mineTabHover: var(--settingBtnHover);
  --mineAvatarBg: var(--blackText);
  --mineAvatarText: var(--whiteText);
  /*登录注册*/
  --loginA: var(--mineCardBg);
  --loginBtn: var(--loginA);
  --loginBg: var(--mineTabHover);
  --loginInputBg: var(--headerBtn);
  --loginInputText: var(--lightBlackText);
  --loginInputActive: var(--mineCardBg);
  --loginInputBorder: var(--blackText);
  --loginText: var(--blackText);
  --loginBtnHover: #beae2b;
  --loginAHover: var(--settingBtnHover);
}


/*.Light {*/
/*  !*背景色*!*/
/*  --leftbg: var(--deepbg);*/
/*  --deepbg: #F3F2F1;*/
/*  --bg: #DBD7D6;*/
/*  --deepbgRgba: rgba(40, 40, 43, 0.4);*/
/*  !*文字颜色*!*/
/*  --deeptext: #333333;*/
/*  --text: #333333;*/
/*  !*响应元素颜色*!*/
/*  --active: dodgerblue;*/
/*  --deepactive: royalblue;*/
/*  !*button背景色*!*/
/*  --button: #DBD7D6;*/
/*  !*主题色和文字颜色*!*/
/*  --color-primary: #DBD7D6;*/
/*  --color-text: #333333;*/
/*  !*覆盖默认的背景色和文字颜色*!*/
/*  background-color: var(--color-primary);*/
/*  color: var(--color-text);*/
/*  !*分割线颜色*!*/
/*  --hr: #FFFFFF;*/
/*  !*选项悬浮*!*/
/*  --hover: #BBBBBB;*/
/*  !*输入框字体颜色*!*/
/*  --input: #777777*/
/*}*/


</style>