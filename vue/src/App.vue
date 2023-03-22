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

//禁止鼠标右键菜单
// document.oncontextmenu = function(e){
//   return false;
// }

</script>
<style>

.Dark {
  /*总体*/
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --scrollbar: #666666;
  --scrollbarHover: #999999;

  /*右部背景*/
  --rightBg: rgb(23, 23, 26);
  /*左边栏*/
  --leftbg: rgb(46, 46, 48);
  --leftText: var(--whiteText);
  --leftBtnActive: royalblue;
  --leftBtnHover: #444444;
  /*上边栏*/
  --headerBg: rgba(23, 23, 26, 0.4);
  --headerBtn: var(--leftbg);
  --headerBtnHover: #444444;
  --headerText: var(--whiteText);
  --headerSearchBg: var(--headerBtn);
  --headerTabText: var(--leftText);
  --headerTabHover: var(--leftBtnActive);
  --headerInputText: var(--lightBlackText);
  /*搜索框*/
  --hr: rgb(61, 61, 63);
  --dialogBg: var(--leftbg);
  --dialogText: var(--headerText);
  /*设置*/
  --settingText: var(--whiteText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: var(--mineBtnBg);
  --settingBtnText: var(--whiteText);
  --settingBtnHover: var(--mineBtnHover);
  --settingHr: var(--hr);
  /*个人中心*/
  --mineText: var(--whiteText);
  --mineCardBg: var(--leftBtnActive);
  --mineTabText: var(--mineText);
  --mineTabHover: var(--leftBtnActive);
  --mineAvatarBg: var(--whiteText);
  --mineAvatarText: var(--blackText);
  --mineBtnBg: var(--loginBtn);
  --mineBtnText: var(--loginText);
  --mineBtnHover: var(--loginBtnHover);
  --mineInputBg: var(--loginInputBg);
  /*登录注册*/
  --loginA: var(--mineCardBg);
  --loginBtn: var(--loginA);
  --loginBg: var(--mineTabHover);
  --loginInputBg: var(--leftbg);
  --loginInputText: var(--lightBlackText);
  --loginInputActive: var(--mineCardBg);
  --loginInputBorder: rgba(0, 0, 0, 0);
  --loginText: var(--whiteText);
  --loginBtnHover: dodgerblue;
  --loginAHover: var(--blackText);
  /*搜索界面*/
  --searchInputBg: var(--loginInputBg);
  --searchInputText: var(--settingText);
  --searchTabText: var(--mineTabText);
  --searchText: var(--whiteText);
  --searchHr: var(--settingHr);
  --searchHover: var(--leftBtnHover);
  --searchActive: var(--leftBtnActive);
  --searchLightText: var(--lightBlackText);
  /*底栏*/
  --footerText: var(--leftText);
  --footerBg: #00000022;
  --footerHover: var(--headerBtnHover);
  --footerActive: var(--leftBtnActive);
}

.Orange {
  /*总体*/
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --scrollbar: var(--headerBtnHover);
  /*右部背景*/
  --rightBg: rgb(58, 60, 63);
  /*左边栏*/
  --leftbg: #B56536;
  --leftText: var(--whiteText);
  --leftBtnActive: #863020;
  --leftBtnHover: rgb(115, 45, 98);
  /*header*/
  --headerBg: rgb(151, 88, 50);
  --headerBtn: rgba(31, 33, 36, 0.8);
  --headerBtnHover: rgb(187, 181, 172);
  --headerText: var(--whiteText);
  --headerSearchBg: rgb(103, 66, 44);
  --headerTabText: var(--leftText);
  --headerTabHover: rgb(134, 48, 32);
  --headerInputText: rgb(58, 60, 63);

  /*dialog*/
  --hr: rgb(88, 69, 59);
  --dialogBg: rgb(210, 128, 6);
  --dialogText: var(--headerText);
  /*setting*/
  --settingText: var(--whiteText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: rgb(134, 48, 32);
  --settingBtnText: var(--lightWhiteText);
  --settingBtnHover: rgb(119, 50, 39);
  --settingHr: rgb(151, 88, 50);
  /*个人中心*/
  --mineText: var(--whiteText);
  --mineCardBg: #862617;
  --mineTabText: var(--mineText);
  --mineTabHover: #fa5d19;
  --mineAvatarBg: var(--whiteText);
  --mineAvatarText: var(--blackText);
  --mineBtnBg: var(--loginBtn);
  --mineBtnText: var(--loginText);
  --mineBtnHover: var(--loginBtnHover);
  --mineInputBg: var(--loginInputBg);
  /*登录注册*/
  --loginA: rgb(181, 101, 54);
  --loginBtn: var(--loginA);
  --loginBg: var(--mineTabHover);
  --loginInputBg: rgb(141, 88, 58);
  --loginInputText: var(--lightBlackText);
  --loginInputActive: var(--mineCardBg);
  --loginInputBorder: rgba(0, 0, 0, 0);
  --loginText: var(--whiteText);
  --loginBtnHover: rgb(157, 93, 56);
  --loginAHover: rgba(181, 101, 54, 0.6);
}

.Light {
  /*总体*/
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --scrollbar: var(--leftBtnActive);
  --scrollbarHover: var(--loginBtnHover);
  /*右边栏*/
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
  --mineInputBg: var(--loginInputBg);
  --mineBtnBg: var(--settingBtn);
  --mineBtnText: var(--settingBtnText);
  --mineBtnHover: var(--settingBtnHover);
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
  /*搜索界面*/
  --searchInputBg: var(--loginInputBg);
  --searchInputText: var(--settingText);
  --searchTabText: var(--mineTabText);
  --searchText: var(--blackText);
  --searchHr: var(--settingHr);
  --searchHover: var(--leftBtnHover);
  --searchActive: var(--leftBtnActive);
  --searchLightText: var(--lightBlackText);
  /*底栏*/
  --footerText: var(--leftText);
  --footerBg: #00000022;
  --footerHover: var(--headerBtnHover);
  --footerActive: var(--leftBtnActive);
}



</style>