<template>
  <div>
    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css" >
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js" />
    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player" />
  </div>
</template>

<script>
import vodApi from '@/api/vod'
export default {
  layout: 'video',//应用video布局
  asyncData({ params, error }) {
    return vodApi.getPlayURL(params.vid).then(response => {
      console.log(response.data.data)
      return {
        vid: params.vid,
        URL: response.data.data.URL
      }
    })
  },

  mounted() {
    new Aliplayer({
      id: 'J_prismPlayer',

      source:this.URL,

      width: '100%',
      height: '500px'
    }, function(player) {
      console.log('播放器创建成功')
    })
  }
}
</script>
