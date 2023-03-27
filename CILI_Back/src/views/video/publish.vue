<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <div class="ccInfo">
      <img :src="publishVideoInfoVO.cover">
      <div class="main">
        <h2>{{ publishVideoInfoVO.title }}</h2>
        <p class="gray"><span>共{{ publishVideoInfoVO.episodeNum }}集</span></p>
        <p><span>所属分类：{{ publishVideoInfoVO.classificationParentId }} — {{ publishVideoInfoVO.classificationId }}</span></p>
        <p>影视创作者：{{ publishVideoInfoVO.producerName }}</p>
        <h3 class="red">￥{{ publishVideoInfoVO.price }}</h3>
      </div>
    </div>
    <div>
      <el-button @click="previous">返回修改</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">发布影视</el-button>
    </div>
  </div>
</template>


<script>
import videoApi from "@/api/video.js"
export default {
  name: 'publish.vue',
  created() {
    //获得上一个页面传进来的id
    if(this.$route.params && this.$route.params.id){
      this.videoId = this.$route.params.id;
    }
    //查询数据
    videoApi.getPublishVideoInfoVOById(this.videoId)
      .then(result => {
        this.publishVideoInfoVO = result.data.publishVideoInfo
      })
  },
  data(){
    return{
      saveBthDisabled:false,
      publishVideoInfoVO:{},
      videoId: "",
    }
  },
  methods:{
    previous(){
      this.$router.push({path:"/video/season/"+this.videoId})
    },
    next(){
      //跳转到第二个步骤
      this.$router.push({path:'/video/publish/'+this.videoId})
    },
    publish(){
      videoApi.publishVideo(this.videoId)
        .then(result => {
          //提示信息
          this.$message({
            type:"success",
            message:'课程发布成功!'
          })
          //跳转
          this.$router.push({path:'/video/list'})
        })

    }
  }
}
</script>

<style scoped>
.ccInfo {
  background: #f5f5f5;
  padding: 20px;
  overflow: hidden;
  border: 1px dashed #DDD;
  margin-bottom: 40px;
  position: relative;
}
.ccInfo img {
  background: #d6d6d6;
  width: 500px;
  height: 278px;
  display: block;
  float: left;
  border: none;
}
.ccInfo .main {
  margin-left: 520px;
}
.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 1;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;
}
</style>
