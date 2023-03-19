<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="影视标题">
        <el-input v-model="videoInfo.title" placeholder="实例:猫和老鼠" />
      </el-form-item>
<!--      所属分类-->
<!--      视频创作者-->
      <el-form-item label="视频创作者">
        <el-select v-model="videoInfo.producerId"
                   placeholder="请选择">
                   <el-option
                     v-for="producer in producerList"
                     :key="producer.id"
                     :label="producer.name"
                     :value="producer.id"/>
        </el-select>

      </el-form-item>


      <el-form-item label="总集数">
        <el-input-number :min="0" v-model="videoInfo.episodeNum" controls-position="right" placeholder="请填写影视的总集数"/>
      </el-form-item>
<!--      影视简介-->
<!--      影视封面-->
      <el-form-item label="影视价格">
        <el-input-number :min="0" v-model="videoInfo.price" controls-position="right" placeholder="免费视频请设置为0元"/> 元
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBthDisabled" type="primary" @click="saveOrUpdate">保存&下一步</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
import videoApi from "@/api/video"
export default {
  name: 'info.vue',
  created() {
    //获取数据用于下拉列表选择
    this.getListProducer();
  },
  data(){
    return{
      saveBthDisabled:false,
      videoInfo:{
        title:"",
        classificationId:"",
        producerId:"",
        episodeNum:0,
        description:"",
        cover:"",
        price:0
      },
      producerList:{}
    }
  },
  methods:{
    //获取所有创作者
    getListProducer(){
      videoApi.getListProducer()
        .then(result =>{
          this.producerList = result.data.items
        } )
    },


    //点击下一步按钮
    saveOrUpdate(){
      videoApi.addVideoInfo(this.videoInfo)
        .then(response =>{


          //提示信息
          this.$message({
            type:'success',
            message:"添加影视信息成功!"
          })
          //跳转到第二个步骤
          this.$router.push({path:'/video/season/'+response.data.videoId})
        })

    }
  }
}
</script>

<style scoped>

</style>
