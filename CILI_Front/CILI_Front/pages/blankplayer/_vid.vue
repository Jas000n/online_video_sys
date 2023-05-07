<template>
  <div>

    <no-ssr>
      <danmu-player :src="src">

      </danmu-player>
    </no-ssr>
  </div>
</template>
<script>
import DanmuPlayer from "./components/_vid.vue"
export default {

  components:{DanmuPlayer},
  data(){
    return{
      src:"https://outin-fcdce230ca1d11eda50000163e1c94a4.oss-cn-shanghai.aliyuncs.com/sv/32e31d00-1876141dafd/32e31d00-1876141dafd.mp4?Expires=1683430613&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=DDFUPxI13uheI9Titegua1ydt4M%3D"
    }
  }
}
</script>
