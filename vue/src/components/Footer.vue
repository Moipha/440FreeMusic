<template>
  <el-footer id="footer" class="container">
    <div class="expandBtn">
      <svg @click="hidePlay(false)" style="margin-left: 5px;margin-top: 5px" t="1702623457457"
           viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
           p-id="5754" width="40" height="40">
        <path
            d="M199.36 572.768a31.904 31.904 0 0 0 22.624-9.376l294.144-294.144 285.728 285.728a31.968 31.968 0 1 0 45.248-45.248l-308.352-308.352a32 32 0 0 0-45.28 0l-316.768 316.768a31.968 31.968 0 0 0 22.656 54.624z"
            p-id="5755" fill="currentColor"></path>
        <path
            d="M538.784 457.376a32 32 0 0 0-45.28 0l-316.768 316.768a31.968 31.968 0 1 0 45.248 45.248l294.144-294.144 285.728 285.728a31.968 31.968 0 1 0 45.248-45.248l-308.32-308.352z"
            p-id="5756" fill="currentColor"></path>
      </svg>
    </div>
    <!--footer-->
    <div class="footer">
      <!--左边-->
      <div class="leftContainer">
        <!--头像-->
        <div @click="showMusic" class="avatarContainer">
          <el-avatar shape="square" :size="70" :src="music.avatar"></el-avatar>
          <i class="el-icon-full-screen avatarIcon"></i>
        </div>
        <div class="musicDataContainer">
          <!--歌曲名-->
          <span class="musicName">{{ music.name }}</span>
          <!--专辑名-->
          <span style="font-size: 10px">{{ music.album }}</span>
        </div>
      </div>
      <!--中间-->
      <div class="middleContainer">
        <!--音频-->
        <audio ref="audio"
               @pause="onPause"
               @play="onPlay"
               :src="music.url"
               controls="controls"
               @timeupdate="onTimeupdate"
               @loadedmetadata="onLoadedmetadata"
               preload="auto"
               @ended="playEnd"
        />
        <!--播放控件-->
        <div style="margin: 10px auto 0">
          <!--上一首-->
          <svg @click="lastMusic" style="padding: 10px 10px 13px;" t="1679312986751" class="icon"
               viewBox="0 0 1024 1024"
               version="1.1"
               xmlns="http://www.w3.org/2000/svg" p-id="8273" width="30px" height="30px">
            <path
                d="M206 865h-38c-30.928 0-56-25.072-56-56V216c0-30.928 25.072-56 56-56h38c30.928 0 56 25.072 56 56v593c0 30.928-25.072 56-56 56z m168.686-386.191l422.304-303.4c32.294-23.201 77.282-15.83 100.484 16.464A72 72 0 0 1 911 233.883v559.053c0 39.764-32.235 72-72 72a72 72 0 0 1-39.95-12.1L376.288 570.877c-25.73-17.16-32.677-51.93-15.517-77.66a56 56 0 0 1 13.915-14.408z"
                fill="#ffffff" p-id="8274"></path>
          </svg>
          <!--播放-->
          <svg @click="handlePlayOrPauseClick" v-if="isPaused" style="padding: 10px 10px 10px 16px;" t="1679313068018"
               class="icon"
               viewBox="0 0 1024 1024" version="1.1"
               xmlns="http://www.w3.org/2000/svg" p-id="2267" width="36px" height="36px">
            <path
                d="M128 138.666667c0-47.232 33.322667-66.666667 74.176-43.562667l663.146667 374.954667c40.96 23.168 40.853333 60.8 0 83.882666L202.176 928.896C161.216 952.064 128 932.565333 128 885.333333v-746.666666z"
                fill="#ffffff" p-id="2268"></path>
          </svg>
          <!--暂停-->
          <svg @click="handlePlayOrPauseClick" v-if="!isPaused" t="1679408137661" style="padding: 10px 13px 10px"
               class="icon" viewBox="0 0 1024 1024"
               version="1.1" xmlns="http://www.w3.org/2000/svg"
               p-id="19674" width="36" height="36">
            <path
                d="M426.666667 138.666667v746.666666a53.393333 53.393333 0 0 1-53.333334 53.333334H266.666667a53.393333 53.393333 0 0 1-53.333334-53.333334V138.666667a53.393333 53.393333 0 0 1 53.333334-53.333334h106.666666a53.393333 53.393333 0 0 1 53.333334 53.333334z m330.666666-53.333334H650.666667a53.393333 53.393333 0 0 0-53.333334 53.333334v746.666666a53.393333 53.393333 0 0 0 53.333334 53.333334h106.666666a53.393333 53.393333 0 0 0 53.333334-53.333334V138.666667a53.393333 53.393333 0 0 0-53.333334-53.333334z"
                fill="#ffffff" p-id="19675"></path>
          </svg>
          <!--下一首-->
          <svg @click="nextMusic" style="padding: 10px 10px 13px;" t="1679312946491" class="icon"
               viewBox="0 0 1024 1024" version="1.1"
               xmlns="http://www.w3.org/2000/svg" p-id="7439" width="30px" height="30px">
            <path
                d="M817 160h38c30.928 0 56 25.072 56 56v593c0 30.928-25.072 56-56 56h-38c-30.928 0-56-25.072-56-56V216c0-30.928 25.072-56 56-56zM648.314 546.191l-422.304 303.4c-32.294 23.201-77.282 15.83-100.484-16.464A72 72 0 0 1 112 791.117V232.064c0-39.764 32.235-72 72-72a72 72 0 0 1 39.95 12.1l422.762 281.959c25.73 17.16 32.677 51.93 15.517 77.66a56 56 0 0 1-13.915 14.408z"
                fill="#ffffff" p-id="7440"></path>
          </svg>
        </div>
        <!--进度条-->
        <div style="display: flex;flex-direction: row">
          <span style="width: 10%;line-height: 20px">{{ currentTime | formatSecond }}</span>
          <el-slider
              v-model="currentTime"
              @change="changeCurrentTime"
              style="width: 80%;position: relative;bottom: 8px;transition: 0s"
              :show-tooltip="false"
              :max="this.maxTime"
              @mousedown.native="isDragging=true"
              @mouseup.native="isDragging=false"
          />
          <span style="width: 10%;line-height: 20px;text-align: right">{{ maxTime | formatSecond }}</span>
        </div>
      </div>
      <!--右边-->
      <div class="rightContainer">
        <!--like-->
        <div @click="like" style="margin-left: 40px;margin-bottom: 22px;margin-right: 10px">
          <svg t="1679376624033" v-if="!star" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
               xmlns="http://www.w3.org/2000/svg"
               p-id="4392" width="30" height="30">
            <path
                d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z m0 63.146667c-41.44 0-70.261333 15.189333-116.96 55.04-2.165333 1.845333-14.4 12.373333-17.941334 15.381333a32.32 32.32 0 0 1-41.770666 0c-3.541333-3.018667-15.776-13.536-17.941334-15.381333-46.698667-39.850667-75.52-55.04-116.96-55.04C230.186667 180.48 149.333333 281.258667 149.333333 426.698667 149.333333 537.6 262.858667 675.242667 493.632 834.826667a32.352 32.352 0 0 0 36.736 0C761.141333 675.253333 874.666667 537.6 874.666667 426.698667c0-145.44-80.853333-246.218667-206.88-246.218667z"
                fill="#d81e06" p-id="4393"></path>
          </svg>
          <svg t="1679376804796" v-else class="iconRight" viewBox="0 0 1024 1024" version="1.1"
               xmlns="http://www.w3.org/2000/svg"
               p-id="5075" width="30" height="30">
            <path
                d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z"
                fill="#d81e06" p-id="5076"></path>
          </svg>
        </div>
        <!--播放顺序-->
        <div style="margin-bottom: 25px;margin-right: 20px" @click="changePlayMode">
          <el-tooltip class="item" effect="dark" :content="playModeTooltip" placement="top">
            <svg v-if="playMode%3===0" t="1679377410315" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg"
                 p-id="1604" width="25" height="25">
              <path
                  d="M725.333333 170.666667V88.234667a21.333333 21.333333 0 0 1 34.986667-16.426667l175.786667 146.474667a21.333333 21.333333 0 0 1-13.696 37.717333H85.333333V170.666667h640zM85.333333 768h853.333334v85.333333H85.333333v-85.333333z m0-298.666667h853.333334v85.333334H85.333333v-85.333334z"
                  p-id="1605" fill="#ffffff"></path>
            </svg>
            <svg v-if="playMode%3===1" t="1679377258200" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg"
                 p-id="1203" width="25" height="25">
              <path
                  d="M768 763.008V682.666667l213.333333 128-213.333333 128v-89.173334a384 384 0 0 1-298.538667-228.906666L469.333333 620.373333l-0.128 0.256A384 384 0 0 1 116.266667 853.333333H85.333333v-85.333333h30.933334a298.666667 298.666667 0 0 0 274.517333-181.034667L422.912 512l-32.128-74.965333A298.666667 298.666667 0 0 0 116.266667 256H85.333333V170.666667h30.933334a384 384 0 0 1 352.938666 232.746666L469.333333 403.626667l0.128-0.256A384 384 0 0 1 768 174.506667V85.333333l213.333333 128-213.333333 128V260.992a298.666667 298.666667 0 0 0-220.117333 176.042667L515.754667 512l32.128 74.965333A298.666667 298.666667 0 0 0 768 763.008z"
                  p-id="1204" fill="#ffffff"></path>
            </svg>
            <svg v-if="playMode%3===2" t="1679377466782" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg"
                 p-id="2110" width="25" height="25">
              <path
                  d="M928 476.8c-19.2 0-32 12.8-32 32v86.4c0 108.8-86.4 198.4-198.4 198.4H201.6l41.6-38.4c6.4-6.4 12.8-16 12.8-25.6 0-19.2-16-35.2-35.2-35.2-9.6 0-22.4 3.2-28.8 9.6l-108.8 99.2c-16 12.8-12.8 35.2 0 48l108.8 96c6.4 6.4 19.2 12.8 28.8 12.8 19.2 0 35.2-12.8 38.4-32 0-12.8-6.4-22.4-16-28.8l-48-44.8h499.2c147.2 0 265.6-118.4 265.6-259.2v-86.4c0-19.2-12.8-32-32-32zM96 556.8c19.2 0 32-12.8 32-32v-89.6c0-112 89.6-201.6 198.4-204.8h496l-41.6 38.4c-6.4 6.4-12.8 16-12.8 25.6 0 19.2 16 35.2 35.2 35.2 9.6 0 22.4-3.2 28.8-9.6l105.6-99.2c16-12.8 12.8-35.2 0-48l-108.8-96c-6.4-6.4-19.2-12.8-28.8-12.8-19.2 0-35.2 12.8-38.4 32 0 12.8 6.4 22.4 16 28.8l48 44.8H329.6C182.4 169.6 64 288 64 438.4v86.4c0 19.2 12.8 32 32 32z"
                  p-id="2111" fill="#ffffff"></path>
              <path d="M544 672V352h-48L416 409.6l16 41.6 60.8-41.6V672z" p-id="2112" fill="#ffffff"></path>
            </svg>
          </el-tooltip>
        </div>

        <!--音量条-->
        <svg @click="cancelMute" t="1679406969198" v-if="isMuted" style="margin-right: 20px;cursor: pointer;"
             viewBox="0 0 1024 1024"
             version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="12113" width="25" height="25">
          <path
              d="M266.417804 400.877059l189.369133-150.280807v522.816527L266.417804 623.122941H92.161282V400.877059H266.417804zM51.208941 324.080355c-19.801549 0-35.842556 16.041007-35.842556 35.842556v304.159596c0 19.790712 16.041007 35.844362 35.842556 35.844362h188.44435c78.275995 62.115778 156.550183 124.231556 234.822566 186.359977 23.48623 18.632927 58.118621 1.907364 58.118621-28.075826V165.796204c0-29.992221-34.632391-46.71959-58.118621-28.074019-78.272382 62.115778-156.546571 124.242393-234.822566 186.35817H51.208941zM596.926483 330.382244c-13.59539-16.264978-11.433349-40.491756 4.831629-54.090759 16.279427-13.597196 40.493563-11.433349 54.090758 4.844272 47.637148 56.993348 95.279715 113.988503 142.916863 170.992689 47.638954-57.004186 95.279715-113.99934 142.929507-170.992689 13.59539-16.277621 37.813138-18.441468 54.079921-4.844272 16.274009 13.597196 18.437856 37.823975 4.842466 54.090759-50.597537 60.542563-101.20591 121.083321-151.801641 181.624078 50.59573 60.540757 101.204104 121.079708 151.801641 181.611434 13.59539 16.277621 11.431543 40.493563-4.842466 54.090759-16.26859 13.608033-40.484532 11.44238-54.079921-4.835241-47.649792-56.993348-95.290552-113.997534-142.929507-170.990883l-142.916863 170.990883c-13.597196 16.277621-37.813138 18.443274-54.090758 4.835241-16.264978-13.597196-18.427018-37.814944-4.831629-54.090759 50.599343-60.531726 101.191461-121.070677 151.799835-181.611434-50.606568-60.542563-101.200492-121.083321-151.799835-181.624078z"
              fill="#ffffff" p-id="12114"></path>
        </svg>
        <svg @click="mute" t="1679378699030" v-if="!isMuted" style="margin-right: 20px;cursor: pointer;"
             viewBox="0 0 1024 1024"
             version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="9152" width="25" height="25">
          <path
              d="M128 420.576v200.864h149.12l175.456 140.064V284.288l-169.792 136.288H128z m132.256-64l204.288-163.968a32 32 0 0 1 52.032 24.96v610.432a32 32 0 0 1-51.968 24.992l-209.92-167.552H96a32 32 0 0 1-32-32v-264.864a32 32 0 0 1 32-32h164.256zM670.784 720.128a32 32 0 0 1-44.832-45.664 214.08 214.08 0 0 0 64.32-153.312 213.92 213.92 0 0 0-55.776-144.448 32 32 0 1 1 47.36-43.04 277.92 277.92 0 0 1 72.416 187.488 278.08 278.08 0 0 1-83.488 198.976zM822.912 858.88a32 32 0 1 1-45.888-44.608A419.008 419.008 0 0 0 896 521.152c0-108.704-41.376-210.848-114.432-288.384a32 32 0 0 1 46.592-43.872c84.16 89.28 131.84 207.04 131.84 332.256 0 127.84-49.76 247.904-137.088 337.728z"
              fill="#ffffff" p-id="9153"></path>
        </svg>
        <el-slider @change="changeVolume" v-model="currentVolume" style="width: 100px;margin-right: 20px"></el-slider>
        <!--弹出侧边栏-->
        <svg @click.stop="changeRightAside" t="1679378020715" class="iconRight" style="margin-right: 10px"
             viewBox="0 0 1024 1024"
             version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="4069" width="25" height="25">
          <path
              d="M740.57 683.43c-7.68 0-15.35-2.93-21.21-8.79-11.72-11.72-11.72-30.7 0-42.42l141.43-141.43c11.72-11.72 30.7-11.72 42.42 0 11.72 11.71 11.72 30.71 0 42.42L761.79 674.64c-5.86 5.86-13.54 8.79-21.22 8.79z"
              p-id="4070" fill="#ffffff"></path>
          <path
              d="M882 542c-7.68 0-15.35-2.93-21.21-8.79L719.36 391.79c-11.72-11.71-11.72-30.71 0-42.42 11.72-11.72 30.7-11.72 42.42 0L903.21 490.8c11.72 11.71 11.72 30.71 0 42.42A29.924 29.924 0 0 1 882 542zM682 542H142c-16.57 0-30-13.43-30-30s13.43-30 30-30h540c16.56 0 30 13.43 30 30s-13.44 30-30 30z"
              p-id="4071" fill="#ffffff"></path>
          <path d="M912 182H112c-16.57 0-30-13.43-30-30s13.43-30 30-30h800c16.56 0 30 13.43 30 30s-13.44 30-30 30z"
                p-id="4072" fill="#ffffff"></path>
          <path d="M912 902H112c-16.57 0-30-13.44-30-30s13.43-30 30-30h800c16.56 0 30 13.44 30 30s-13.44 30-30 30z"
                p-id="4073" fill="#ffffff"></path>
        </svg>
        <!--收起底栏-->
        <svg @click.stop="hidePlay(true)" t="1702263137737" class="icon iconRight" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="5857" width="25" height="25">
          <path
              d="M511.609097 961.619254M511.906879 662.759609 511.906879 662.759609 129.831974 280.679587c-14.788821-14.762215-38.777165-14.762215-53.585429 0-14.788821 14.812357-14.788821 38.799678 0 53.607942l405.851425 405.805376c0.867764 1.107217 1.824555 2.190899 2.843768 3.206018 14.808264 14.788821 38.795585 14.788821 53.585429 0l408.230612-408.226518c14.807241-14.808264 14.807241-38.795585 0-53.584406-14.767332-14.785751-38.754652-14.785751-53.562916 0L511.906879 662.759609 511.906879 662.759609zM511.906879 662.759609"
              fill="#ffffff" p-id="5858"></path>
        </svg>
      </div>
    </div>

    <!--底部-->
    <div>
      <div :class="bottomClass"
           style="left: 0;width: 100%;height: 100%;
           position: fixed!important;transition: 0.5s;display: flex;
           flex-direction: row"
           ref="bottom">
        <div class="leftDiv">
          <!--封面-->
          <div class="avatar">
            <el-avatar :src="music.avatar" size="large" style="width: 100%;height: 100%;border-radius: 20px;"
                       shape="square"/>
          </div>
          <!--标题-->
          <div style="display: flex;flex-direction: column;margin-left: 28vh;margin-top: 80px">
            <span style="padding: 5px 5px 5px 0;font-size: 22px;font-weight: bold">{{ music.name }}</span>
            <span style="padding: 5px 5px 5px 0;font-size: 12px">{{ music.author }}</span>
          </div>
          <!--进度条-->
          <div style="display: flex;flex-direction: row;margin-left: 28vh;margin-top: 30px">
            <span style="width: 8vh;line-height: 20px">{{ currentTime | formatSecond }}</span>
            <el-slider
                v-model="currentTime"
                @change="changeCurrentTime"
                style="width: 40vh;position: relative;bottom: 8px"
                :show-tooltip="false"
                :max="this.maxTime"
                @mousedown="isDragging=true"
                @mouseup="isDragging=false"
            />

            <span style="width:8vh;line-height: 20px;text-align: right">{{
                maxTime | formatSecond
              }}</span>
          </div>
          <!--控件-->
          <div class="bottomIcons">
            <!--播放顺序-->
            <div style="margin: 7px 10px 0 80px" @click="changePlayMode">
              <el-tooltip class="item" effect="dark" :content="playModeTooltip" placement="top">
                <svg v-if="playMode%3===0" t="1679377410315" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg"
                     p-id="1604" width="30" height="30">
                  <path
                      d="M725.333333 170.666667V88.234667a21.333333 21.333333 0 0 1 34.986667-16.426667l175.786667 146.474667a21.333333 21.333333 0 0 1-13.696 37.717333H85.333333V170.666667h640zM85.333333 768h853.333334v85.333333H85.333333v-85.333333z m0-298.666667h853.333334v85.333334H85.333333v-85.333334z"
                      p-id="1605" fill="#ffffff"></path>
                </svg>
                <svg v-if="playMode%3===1" t="1679377258200" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg"
                     p-id="1203" width="30" height="30">
                  <path
                      d="M768 763.008V682.666667l213.333333 128-213.333333 128v-89.173334a384 384 0 0 1-298.538667-228.906666L469.333333 620.373333l-0.128 0.256A384 384 0 0 1 116.266667 853.333333H85.333333v-85.333333h30.933334a298.666667 298.666667 0 0 0 274.517333-181.034667L422.912 512l-32.128-74.965333A298.666667 298.666667 0 0 0 116.266667 256H85.333333V170.666667h30.933334a384 384 0 0 1 352.938666 232.746666L469.333333 403.626667l0.128-0.256A384 384 0 0 1 768 174.506667V85.333333l213.333333 128-213.333333 128V260.992a298.666667 298.666667 0 0 0-220.117333 176.042667L515.754667 512l32.128 74.965333A298.666667 298.666667 0 0 0 768 763.008z"
                      p-id="1204" fill="#ffffff"></path>
                </svg>
                <svg v-if="playMode%3===2" t="1679377466782" class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg"
                     p-id="2110" width="30" height="30">
                  <path
                      d="M928 476.8c-19.2 0-32 12.8-32 32v86.4c0 108.8-86.4 198.4-198.4 198.4H201.6l41.6-38.4c6.4-6.4 12.8-16 12.8-25.6 0-19.2-16-35.2-35.2-35.2-9.6 0-22.4 3.2-28.8 9.6l-108.8 99.2c-16 12.8-12.8 35.2 0 48l108.8 96c6.4 6.4 19.2 12.8 28.8 12.8 19.2 0 35.2-12.8 38.4-32 0-12.8-6.4-22.4-16-28.8l-48-44.8h499.2c147.2 0 265.6-118.4 265.6-259.2v-86.4c0-19.2-12.8-32-32-32zM96 556.8c19.2 0 32-12.8 32-32v-89.6c0-112 89.6-201.6 198.4-204.8h496l-41.6 38.4c-6.4 6.4-12.8 16-12.8 25.6 0 19.2 16 35.2 35.2 35.2 9.6 0 22.4-3.2 28.8-9.6l105.6-99.2c16-12.8 12.8-35.2 0-48l-108.8-96c-6.4-6.4-19.2-12.8-28.8-12.8-19.2 0-35.2 12.8-38.4 32 0 12.8 6.4 22.4 16 28.8l48 44.8H329.6C182.4 169.6 64 288 64 438.4v86.4c0 19.2 12.8 32 32 32z"
                      p-id="2111" fill="#ffffff"></path>
                  <path d="M544 672V352h-48L416 409.6l16 41.6 60.8-41.6V672z" p-id="2112" fill="#ffffff"></path>
                </svg>
              </el-tooltip>
            </div>
            <!--上一首-->
            <svg @click="lastMusic" style="padding: 10px;margin: 5px 10px" t="1679312986751" class="icon"
                 viewBox="0 0 1024 1024"
                 version="1.1"
                 xmlns="http://www.w3.org/2000/svg" p-id="8273" width="30px" height="30px">
              <path
                  d="M206 865h-38c-30.928 0-56-25.072-56-56V216c0-30.928 25.072-56 56-56h38c30.928 0 56 25.072 56 56v593c0 30.928-25.072 56-56 56z m168.686-386.191l422.304-303.4c32.294-23.201 77.282-15.83 100.484 16.464A72 72 0 0 1 911 233.883v559.053c0 39.764-32.235 72-72 72a72 72 0 0 1-39.95-12.1L376.288 570.877c-25.73-17.16-32.677-51.93-15.517-77.66a56 56 0 0 1 13.915-14.408z"
                  fill="#ffffff" p-id="8274"></path>
            </svg>
            <!--播放-->
            <svg @click="handlePlayOrPauseClick" v-if="isPaused" style="padding: 10px 10px 10px 16px;" t="1679313068018"
                 class="icon"
                 viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg" p-id="2267" width="36px" height="36px">
              <path
                  d="M128 138.666667c0-47.232 33.322667-66.666667 74.176-43.562667l663.146667 374.954667c40.96 23.168 40.853333 60.8 0 83.882666L202.176 928.896C161.216 952.064 128 932.565333 128 885.333333v-746.666666z"
                  fill="#ffffff" p-id="2268"></path>
            </svg>
            <!--暂停-->
            <svg @click="handlePlayOrPauseClick" v-if="!isPaused" t="1679408137661" style="padding: 10px 13px 10px"
                 class="icon" viewBox="0 0 1024 1024"
                 version="1.1" xmlns="http://www.w3.org/2000/svg"
                 p-id="19674" width="36" height="36">
              <path
                  d="M426.666667 138.666667v746.666666a53.393333 53.393333 0 0 1-53.333334 53.333334H266.666667a53.393333 53.393333 0 0 1-53.333334-53.333334V138.666667a53.393333 53.393333 0 0 1 53.333334-53.333334h106.666666a53.393333 53.393333 0 0 1 53.333334 53.333334z m330.666666-53.333334H650.666667a53.393333 53.393333 0 0 0-53.333334 53.333334v746.666666a53.393333 53.393333 0 0 0 53.333334 53.333334h106.666666a53.393333 53.393333 0 0 0 53.333334-53.333334V138.666667a53.393333 53.393333 0 0 0-53.333334-53.333334z"
                  fill="#ffffff" p-id="19675"></path>
            </svg>
            <!--下一首-->
            <svg @click="nextMusic" t="1679312946491" class="icon" style="padding: 10px;margin: auto 10px"
                 viewBox="0 0 1024 1024"
                 version="1.1"
                 xmlns="http://www.w3.org/2000/svg" p-id="7439" width="30px" height="30px">
              <path
                  d="M817 160h38c30.928 0 56 25.072 56 56v593c0 30.928-25.072 56-56 56h-38c-30.928 0-56-25.072-56-56V216c0-30.928 25.072-56 56-56zM648.314 546.191l-422.304 303.4c-32.294 23.201-77.282 15.83-100.484-16.464A72 72 0 0 1 112 791.117V232.064c0-39.764 32.235-72 72-72a72 72 0 0 1 39.95 12.1l422.762 281.959c25.73 17.16 32.677 51.93 15.517 77.66a56 56 0 0 1-13.915 14.408z"
                  fill="#ffffff" p-id="7440"></path>
            </svg>
            <!--like-->
            <div @click="like">
              <svg t="1679376624033" style="margin: 5px 5px 0 5px" v-if="!star" class="iconRight"
                   viewBox="0 0 1024 1024" version="1.1"
                   xmlns="http://www.w3.org/2000/svg"
                   p-id="4392" width="35" height="35">
                <path
                    d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z m0 63.146667c-41.44 0-70.261333 15.189333-116.96 55.04-2.165333 1.845333-14.4 12.373333-17.941334 15.381333a32.32 32.32 0 0 1-41.770666 0c-3.541333-3.018667-15.776-13.536-17.941334-15.381333-46.698667-39.850667-75.52-55.04-116.96-55.04C230.186667 180.48 149.333333 281.258667 149.333333 426.698667 149.333333 537.6 262.858667 675.242667 493.632 834.826667a32.352 32.352 0 0 0 36.736 0C761.141333 675.253333 874.666667 537.6 874.666667 426.698667c0-145.44-80.853333-246.218667-206.88-246.218667z"
                    fill="#d81e06" p-id="4393"></path>
              </svg>
              <svg t="1679376804796" style="margin:  5px" v-else class="iconRight" viewBox="0 0 1024 1024" version="1.1"
                   xmlns="http://www.w3.org/2000/svg"
                   p-id="5075" width="35" height="35">
                <path
                    d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z"
                    fill="#d81e06" p-id="5076"></path>
              </svg>
            </div>
          </div>
        </div>
        <div class="midDiv">
          <span style="color: var(--footerText);font-size: 22px;font-weight: bold;margin: auto">暂无歌词</span>
        </div>
        <div class="rightDiv">
          <i class="el-icon-arrow-down rightIcon" style="margin-top: 40px;" @click="bottomClass='hiddenMusic'"/>
          <i class="el-icon-rank rightIcon" @click="fullScreen"></i>
        </div>
      </div>
    </div>
  </el-footer>
</template>

<script>
import ColorThief from "colorthief"

/**
 * 格式化时间
 * @param second 传入总秒数
 * @returns {string} 返回 mm:ss 格式的时间字符串
 */
function realFormatSecond(second) {
  let secondType = typeof second;
  if (secondType === 'number' || secondType === 'string') {
    second = parseInt(second);
    let hours = Math.floor(second / 3600);
    second = second - hours * 3600;
    let minute = Math.floor(second / 60);
    second = second - minute * 60;
    return ('0' + minute).slice(-2) + ':' + ('0' + second).slice(-2)
  } else {
    return '00:00'
  }

}

export default {
  name: "Footer",
  data() {
    return {
      //播放模式：0顺序 1随机 2单曲
      playMode: 0,
      //是否静音
      isMuted: false,
      //是否暂停
      isPaused: true,
      //最大时间
      maxTime: 0,
      //当前时间
      currentTime: 0,
      //当前音量
      currentVolume: 50,
      //加载是否就绪
      isLoaded: false,
      //音乐对象
      music: {
        avatar: require('@/assets/DefaultAvatar.png')
      },
      //音乐界面的类名
      bottomClass: 'hiddenMusic',
      //图片主题色
      themeColor: [],
      //是否正在拖动进度条
      isDragging: false,
      //是否收藏
      star: false
    }
  },
  methods: {
    //音频播放后调用方法
    onPlay() {
      this.isPaused = false
    },
    //音频暂停后调用方法
    onPause() {
      this.isPaused = true
    },
    //控制音频播放或暂停的方法
    play() {
      this.$refs.audio.play()
    },
    pause() {
      this.$refs.audio.pause()
    },
    //播放或暂停按钮被点击后
    handlePlayOrPauseClick() {
      if (this.isLoaded) {
        if (this.isPaused) {
          this.play()
        } else {
          this.pause()
        }
      }
    },
    // 当timeupdate事件大概每秒一次，用来更新音频流的当前播放时间
    onTimeupdate(res) {
      if (!this.isDragging) {
        this.currentTime = res.target.currentTime
      }
    },
    // 当加载语音流元数据完成后，会触发该事件的回调函数
    // 语音元数据主要是语音的长度之类的数据
    onLoadedmetadata(res) {
      this.isLoaded = true
      this.maxTime = parseInt(res.target.duration)
    },
    // 拖动进度条，改变当前时间
    changeCurrentTime(index) {
      //如果在暂停状态拖动那直接播放
      if (this.isPaused) {
        this.play()
      }
      this.$refs.audio.currentTime = index
    },
    //改变当前音量：要将音量转成0~1的小数
    changeVolume() {
      this.$refs.audio.volume = parseFloat(this.currentVolume) / 100
    },
    //喜欢该歌曲
    like() {
      this.request.post('/music/starMusic', {
        userId: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).id.toString() : '',
        musicId: this.music.id
      }).then(res => {
        if (res.code === '200') {
          if (res.data) {
            this.$notify({
              type: 'success',
              message: "歌曲收藏成功",
              title: '成功'
            })
            this.star = true
          } else {
            this.$notify({
              type: 'info',
              message: "歌曲已取消收藏",
              title: '成功'
            })
            this.star = false
          }
          //通过总线通知收藏页面更新
          this.$bus.$emit('changeStar')
        } else {
          this.$notify({
            type: 'error',
            message: res.msg,
            title: '操作失败'
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          message: err,
          title: '操作失败'
        })
      })
    },
    //静音和取消
    mute() {
      this.isMuted = true
      this.$refs.audio.muted = true
    },
    cancelMute() {
      this.isMuted = false
      this.$refs.audio.muted = false
    },
    //改变播放顺序
    changePlayMode() {
      if (this.playMode < 2) {
        this.playMode++
      } else {
        this.playMode = 0
      }
    },
    //显示音乐界面
    showMusic() {
      if (this.bottomClass === 'hiddenMusic') {
        this.bottomClass = 'showMusic'
      } else {
        this.bottomClass = 'hiddenMusic'
      }
    },
    //打开侧边栏
    changeRightAside() {
      this.$bus.$emit('changeRightAside')
    },
    //隐藏或者显示播放栏
    hidePlay(b) {
      let footer = document.getElementsByClassName('footer')[0]
      if (b) {
        footer.style.bottom = '-100px'
      } else {
        footer.style.bottom = '0'
      }
    },
    //获取基于封面主题色的背景
    getBackground() {
      if (this.music !== undefined && this.music !== null) {
        const colorThief = new ColorThief()
        const image = new Image()
        // 加载图像
        image.src = this.music.avatar + '?' + new Date().getTime();
        image.setAttribute('crossOrigin', '');
        image.addEventListener('load', () => {
          // 获取图像的颜色
          const palette = colorThief.getPalette(image, 2)
          this.themeColor[0] = `rgb(${palette[0][0]}, ${palette[0][1]}, ${palette[0][2]})`
          this.themeColor[1] = `rgb(${palette[1][0]}, ${palette[1][1]}, ${palette[1][2]})`
          this.$refs.bottom.style.backgroundImage = `linear-gradient(to bottom right, ${this.themeColor[1]}, ${this.themeColor[0]})`
        })
      }
    },
    //播放结束，播放下一首
    playEnd() {
      //获取接下来要播放的音乐，将当前播放的音乐放置在数组末
      let list = JSON.parse(localStorage.getItem('playList'))
      list.push(list.shift())
      this.$bus.$emit('play', list[0], list)
    },
    //开始时或者切换曲子时
    startOrChange() {
      //检测播放列表
      let listIsEmpty = (JSON.parse(localStorage.getItem('playList')) === null)
      this.hidePlay(listIsEmpty)
      //获取要播放的音乐
      if (!listIsEmpty) {
        this.music = JSON.parse(localStorage.getItem('playList'))[0]
      }
      //获取音乐界面背景
      this.getBackground()
      //查询是否收藏
      this.searchStar()
    },
    //全屏和取消
    fullScreen() {
      let element = document.querySelector('#footer')
      if (document.fullscreenElement) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
      }
    },
    //本地内存中的内容改变
    localStorageChange() {
      this.$bus.$emit('getCurrentPlayMusic')
      this.$bus.$emit('getPlayList')
    },
    //上一首
    lastMusic() {
      //获取播放列表末尾的音乐，放置到列表首位
      let list = JSON.parse(localStorage.getItem('playList'))
      list.unshift(list.pop())
      this.$bus.$emit('play', list[0], list)
    },
    //下一首
    nextMusic() {
      this.playEnd()
    },
    //查询该音乐是否被收藏
    searchStar() {
      this.request.post('/music/searchStar', {
        userId: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).id.toString() : '',
        musicId: this.music.id
      }).then(res => {
        if (res.code === '200') {
          this.star = res.data
        } else {
          this.$notify({
            type: 'error',
            message: res.msg,
            title: '查询音乐是否被收藏错误'
          })
        }
      }).catch(err => {
        this.$notify({
          type: 'error',
          message: err,
          title: '查询音乐是否被收藏错误'
        })
      })
    },
  },
  filters: {
    // 将整数转化成时分秒
    formatSecond(second = 0) {
      return realFormatSecond(second)
    }
  },
  watch: {
    //检测播放音乐变化后改变背景
    music() {
      this.getBackground()
    }
  },
  computed: {
    //播放顺序悬浮提示
    playModeTooltip() {
      return this.playMode === 0 ? '顺序播放' : this.playMode === 1 ? '随机播放' : '单曲循环'
    }
  },
  mounted() {
    this.startOrChange()
    this.changeVolume()
    //播放
    this.$bus.$on('play', (music, musics) => {
      // 复制原数组以免修改原始数据
      const list = musics.slice()
      //获取并添加播放列表
      if (musics[0] !== music) {
        //按当前音乐为首的方式排序
        // 自定义比较函数
        list.sort((a, b) => {
          if (a === music) {
            return -1; // 将 music 移至数组首位
          } else if (b === music) {
            return 1; // 将 music 移至数组首位
          } else {
            return 0; // 保持原有顺序
          }
        })
      } else {
        //播放同一首音乐
        this.changeCurrentTime(0)
        this.play(false)
      }
      //在本地内存中保存列表
      localStorage.setItem('playList', JSON.stringify(list))
      //设置当前播放的音乐
      localStorage.setItem('currentMusic', JSON.stringify(music))
      //当前音乐修改，添加至历史播放中
      const h = localStorage.getItem('historyList')
      let history = h ? JSON.parse(h) : [music]
      if (h) {
        //判断，如果数组首位是相同的歌曲则直接替换而不是重复添加
        if (history[0].id === music.id) {
          history.shift()
        }
        history.unshift(music)
      }
      localStorage.setItem('historyList', JSON.stringify(history))
      this.localStorageChange()
      this.startOrChange()
      //加载完成后播放
      this.$refs.audio.addEventListener('canplaythrough', () => {
        this.$refs.audio.volume = parseFloat(this.currentVolume) / 150
        this.$refs.audio.play()
      })
    })
    //改变音量
    this.$bus.$on('changeVolume', (b) => {
      if (b !== undefined) {
        if (b) {
          this.currentVolume += 10
        } else {
          this.currentVolume -= 10
        }
        this.changeVolume()
      } else {
        if (this.isMuted) {
          this.cancelMute()
        } else {
          this.mute()
        }
      }
    })
    //播放或者暂停
    this.$bus.$on('changePlay', () => {
      this.handlePlayOrPauseClick()
    })
    //上一首和下一首
    this.$bus.$on('lastMusic', () => {
      this.lastMusic()
    })
    this.$bus.$on('nextMusic', () => {
      this.nextMusic()
    })
  },
  beforeDestroy() {
    this.$bus.$off('play')
    this.$bus.$off('changeVolume')
    this.$bus.$off('changePlay')
    this.$bus.$off('lastMusic')
    this.$bus.$off('nextMusic')
  }
}
</script>

<style scoped>
.container {
  position: fixed;
  bottom: -100%;
  width: 100%;
  height: 100%;
  z-index: 2;
  transition: 1s ease-in-out;
}

.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  height: 100px !important;
  width: 100%;
  background-color: var(--footerBg);
  display: flex;
  flex-direction: row;
  padding: 0;
  backdrop-filter: blur(25px);
  color: var(--footerText);
  border-top: 1px var(--footerHover) solid;
}

/deep/ .icon {
  border-radius: 10px;
  margin: 0 10px;
  cursor: pointer;
}

/deep/ .icon:hover {
  background-color: var(--footerHover) !important;
}

/deep/ .iconRight {
  cursor: pointer;
  padding: 8px;
  border-radius: 10px;
}

.iconRight:hover {
  background-color: var(--footerHover) !important;
}

.rightContainer {
  width: 30%;
  background-color: var(--footerBg);
  display: flex;
  flex-direction: row;
  justify-content: right;
  padding-right: 20px;
}

.rightContainer * {
  margin: auto 0;

}

.leftContainer {
  width: 30%;
  background-color: var(--footerBg);
  display: flex;
  flex-direction: row
}

.middleContainer {
  display: flex;
  flex-direction: column;
  width: 40%;
  margin: auto;
  background-color: var(--footerBg);
  height: 100%;
}

.musicName {
  padding-top: 20px;
  font-weight: bold;
  font-size: 20px;
  line-height: 40px;
  text-align: left
}

.musicDataContainer {
  flex-direction: column;
  display: flex;
  width: 300px;
  margin-left: 8px;
}

/deep/ .el-slider__bar {
  background-color: var(--footerActive);
}

.avatarContainer {
  margin: 15px 10px 15px 20px;
  cursor: pointer;
}

.avatarContainer:hover::after {
  opacity: 1;
}

.avatarContainer::after {
  transition: 0.3s;
  content: "";
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  left: 20px;
  height: 70px;
  top: 15px;
  border-radius: 4px;
  width: 70px;
  opacity: 0;
}

.avatarContainer:hover i {
  opacity: 1;
}

.avatarIcon {
  font-size: 50px;
  margin-top: 10px;
  position: fixed;
  left: 30px;
  opacity: 0;
  transition: 0.3s;
  color: white;
  z-index: 1;
}


/*原music中的样式*/
.hiddenMusic {
  bottom: -100%;
}

.showMusic {
  bottom: 0;
}

.leftDiv {
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 100%;
  color: var(--footerText) !important;
}

.avatar {
  margin: 15vh 0 0 33vh;
  width: 44vh;
  height: 44vh;
  border-radius: 20px;
  transition: 0.2s ease-in-out;
  background-color: white;
}

.midDiv {
  width: 42%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.rightDiv {
  height: 100%;
  width: 8%;
  position: absolute;
  right: 0;
  display: flex;
  flex-direction: column;
}

.rightIcon {
  font-size: 40px;
  color: var(--footerText);
  margin: 5px auto;
  padding: 13px;
  border-radius: 22px;
  cursor: pointer;
}

.rightIcon:hover {
  background-color: var(--lightBlackText);
}

.bottomIcons {
  display: flex;
  flex-direction: row;
  margin-left: 28%
}

.bottomIcons * {
  margin: auto 0;
}

.expandBtn {
  position: fixed;
  width: 50px;
  height: 50px;
  bottom: 0;
  right: 20px;
  background-color: var(--footerBg);
  border-radius: 10px 10px 0 0;
  backdrop-filter: blur(25px);
  color: var(--footerText);
  border: 1px var(--footerHover) solid;
  cursor: pointer;
}
.expandBtn:hover{
  background-color: var(--footerHover) !important;
}
</style>