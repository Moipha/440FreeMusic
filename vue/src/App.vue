<template>
  <div id="app" :class="$store.state.theme">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}

// 禁止鼠标右键菜单
// document.oncontextmenu = function(e){
//   return false;
// }

</script>
<style>

.Dark {
  /*总体*/
  --btnText: white;
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --scrollbar: #666666;
  --scrollbarHover: #999999;
  --maskBg: #333333DD;
  /*主页*/
  --homeText: var(--whiteText);
  /*右部背景*/
  --rightBg: rgb(23, 23, 26);
  /*左边栏*/
  --leftbg: rgb(46, 46, 48);
  --leftText: var(--whiteText);
  --leftBtnActive: royalblue;
  --leftBtnHover: #444444;
  --leftListBg: var(--rightTabBg);
  /*上边栏*/
  /*--headerBg: rgba(23, 23, 26, 0.8);*/
  --headerBg: rgba(0, 0, 0, 0);
  --headerBgOp: #17171acc;
  --headerBtn: rgba(46, 46, 48, 0.7);
  --headerBtnHover: #444444;
  --headerText: var(--whiteText);
  --headerSearchBg: var(--headerBtn);
  --headerTabText: var(--leftText);
  --headerTabHover: var(--leftBtnActive);
  --headerInputText: var(--lightWhiteText);
  /*搜索框*/
  --hr: rgb(61, 61, 63);
  --dialogBg: var(--leftbg);
  --dialogText: var(--headerText);
  --dialogInputBg: var(--rightTabBg);
  --dialogInputActiveBg: var(--rightBg);
  /*设置*/
  --settingText: var(--whiteText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: var(--mineBtnBg);
  --settingBtnText: var(--whiteText);
  --settingBtnHover: var(--mineBtnHover);
  --settingHr: var(--hr);
  --settingBorder: #444444AA;
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
  --mineInputBg: var(--blackText);
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
  --searchInputBorder: rgba(0, 0, 0, 0);
  --searchTabText: var(--mineTabText);
  --searchText: var(--whiteText);
  --searchHr: var(--settingHr);
  --searchHover: var(--leftBtnHover);
  --searchActive: var(--leftBtnActive);
  --searchLightText: var(--lightBlackText);
  /*底栏*/
  --footerText: #FFFFFF;
  --footerBg: #00000055;
  --footerHover: var(--headerBtnHover);
  --footerActive: var(--leftBtnActive);
  /*右边栏*/
  --rightAsideBg: #222222;
  --rightAsideText: var(--whiteText);
  --rightTabBg: #333333;
  --rightTab: #555555;
  --rightLightText: #AAAAAA;
  --rightHover: #444444;
  /*歌单*/
  --listText: var(--whiteText);
  --listHover: #aaaaaa55;
  --listBtn: var(--loginBtn);
  --listBtnHover: var(--loginBtnHover);
  --listTh: rgb(134, 134, 136);
  --listInput: #55555577;
  --listActive: #006bad;
  --bright: 40%;
  /*菜单*/
  --menuBg: #222222bb;
  --menuText: var(--leftText);
  --menuBorder: #333333;
  --menuHover: var(--searchHover);
}

.Light {
  /*总体*/
  --btnText: white;
  --whiteText: #DDDDDD;
  --blackText: #333333;
  --lightWhiteText: #AAAAAA;
  --lightBlackText: #777777;
  --scrollbar: var(--leftBtnActive);
  --scrollbarHover: var(--loginBtnHover);
  /*右边栏*/
  --rightBg: white;
  /*左边栏*/
  --leftbg: whitesmoke;
  --leftText: black;
  --leftBtnActive: dodgerblue;
  --leftBtnHover: #eee;
  /*header*/
  --headerBg: rgba(0, 0, 0, 0);
  --headerBtn: whitesmoke;
  --headerBtnHover: #ddd;
  --headerText: var(--blackText);
  --headerSearchBg: whitesmoke;
  --headerTabText: var(--blackText);
  --headerTabHover: var(--leftBtnActive);
  --headerInputText: var(--lightBlackText);
  /*dialog*/
  --hr: #333;
  --dialogBg: ghostwhite;
  --dialogText: var(--headerText);
  /*setting*/
  --settingText: var(--blackText);
  --settingLightText: var(--lightBlackText);
  --settingBtn: dodgerblue;
  --settingBtnText: white;
  --settingBtnHover: royalblue;
  --settingHr: black;
  /*个人中心*/
  --mineText: var(--blackText);
  --mineCardBg: var(--leftBtnActive);
  --mineTabText: var(--mineText);
  --mineTabHover: var(--settingBtnHover);
  --mineAvatarBg: var(--blackText);
  --mineAvatarText: var(--whiteText);
  --mineInputBg: var(--whiteText);
  --mineBtnBg: var(--settingBtn);
  --mineBtnText: var(--settingBtnText);
  --mineBtnHover: var(--settingBtnHover);
  /*登录注册*/
  --loginA: var(--mineCardBg);
  --loginBtn: var(--loginA);
  --loginBg: var(--mineTabHover);
  --loginInputBg: var(--dialogBg);
  --loginInputText: var(--lightBlackText);
  --loginInputActive: var(--mineCardBg);
  --loginInputBorder: var(--blackText);
  --loginText: var(--blackText);
  --loginBtnHover: royalblue;
  --loginAHover: #ddd;
  /*搜索界面*/
  --searchInputBg: var(--loginInputBg);
  --searchInputText: var(--blackText);
  --searchInputBorder: var(--blackText);
  --searchTabText: var(--mineTabText);
  --searchText: var(--blackText);
  --searchHr: var(--settingHr);
  --searchHover: #eee;
  --searchActive: var(--leftBtnActive);
  --searchLightText: var(--lightBlackText);
  /*底栏*/
  --footerText: var(--leftText);
  --footerBg: #22222230;
  --footerHover: var(--lightBlackText);
  --footerActive: var(--mineTabHover);
  /*右边栏*/
  --rightAsideBg: whitesmoke;
  --rightAsideText: black;
  --rightTabBg: white;
  --rightTab: whitesmoke;
  --rightLightText: #AAAAAA;
  --rightHover: #ddd;
  /*歌单*/
  --listText: var(--whiteText);
  --listHover: #aaaaaa55;
  --listBtn: var(--loginBtn);
  --listBtnHover: var(--loginBtnHover);
  --listTh: rgb(134, 134, 136);
  --listInput: #55555577;
  --listActive: dodgerblue;
  --bright: 90%;
  /*菜单*/
  --menuBg: #ffffffbb;
  --menuText: var(--leftText);
  --menuBorder: #aaa;
  --menuHover: var(--searchHover);
}


</style>