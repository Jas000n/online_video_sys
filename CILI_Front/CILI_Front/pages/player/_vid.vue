<template>
  <div>
    <NPlayer
      ref="player"
      :options="{ src: 'https://v-cdn.zjol.com.cn/280443.mp4' }"
      :set="setPlayer"
    />
  </div>
</template>

<script>
  import vodApi from "@/api/vod"
  import Player from 'nplayer'

  export default{
    data(){
      return{
        URL:'',
        vSourceId:''
      }
    },
    created() {
      this.vSourceId = this.$route.params.vid;
    },
    mounted() {
      this.initPlayer();
    },
    initPlayer(){
      vodApi.getPlayURL(this.vSourceId)
        .then(response =>{
          var nplayer = new Player({
            src:response.data.data.URL
          })
          // let toMount = document.getElementById("toMountNPlayer")
          // nplayer.mount(toMount)
        })
    },

  }
</script>

