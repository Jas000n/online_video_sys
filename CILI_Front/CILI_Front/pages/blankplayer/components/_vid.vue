<template>
  <div id="app">
    <template>
      <!--      <vue-aliplayer-v2-->
      <!--        :source="source"-->
      <!--        ref="VueAliplayerV2"-->
      <!--        :options="options"-->
      <!--      />-->
      <div id="toMountNPlayer"></div>
      <!--      <NPlayer-->
      <!--        id="NPlayer"-->
      <!--        :options="{ src: this.source, plugins: [danmaku,],-->

      <!--        controls: [['play', 'danmaku-send', 'danmaku-settings'], ['progress']]}"-->

      <!--      />-->
    </template>
  </div>
</template>
<script>
import Player from "nplayer";
export default {
  props:{
    src:{required:true
    }
  },
  data() {
    return {};
  },
  created() {},
  methods: {
    initDanmaku() {
      var nplayer = new Player({
        src: "https://outin-fcdce230ca1d11eda50000163e1c94a4.oss-cn-shanghai.aliyuncs.com/sv/32e31d00-1876141dafd/32e31d00-1876141dafd.mp4?Expires=1683430613&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=DDFUPxI13uheI9Titegua1ydt4M%3D",
        controls: [
          [
            "play",
            "volume",
            "time",
            "spacer",
            "airplay",
            "settings",
            "web-fullscreen",
            "fullscreen",
          ],
          ["progress"],
        ],
      });

      let toMountDom = document.getElementById("toMountNPlayer");
      console.log(nplayer);
      console.log(123);
      nplayer.mount(toMountDom);
    },
  },
  mounted() {
    this.initDanmaku();
  },
};
</script>
