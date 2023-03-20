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
      <el-form-item label="视频分类">

<!--        一级分类-->
        <el-select v-model="videoInfo.classificationParentId"
                   @change="renderLv2List"
                   placeholder="请选择一级分类">
          <el-option
            v-for="Lv1Class in Lv1ClassList"
            :key="Lv1Class.id"
            :label="Lv1Class.title"
            :value="Lv1Class.id"/>
        </el-select>
<!--      二级分类-->
        <el-select v-model="videoInfo.classificationId"
                   placeholder="请选择二级分类">
          <el-option
            v-for="Lv2Class in Lv2ClassList"
            :key="Lv2Class.id"
            :label="Lv2Class.title"
            :value="Lv2Class.id"
          />
        </el-select>

      </el-form-item>

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
      <el-form-item label="影视简介">
        <tinymce :height="300" v-model="videoInfo.description"/>
      </el-form-item>

<!--      影视封面-->
      <el-form-item label="影视封面">
        <el-upload
          :show-file-list="false"
          :auto-upload="true"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :action="BASE_API+'/oss/fileoss'"
          class="avatar-uploader">
          <img :src="videoInfo.cover">
        </el-upload>
      </el-form-item>

<!--      //价格-->
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
import classificationApi from '@/api/classification'
import Tinymce from '@/components/Tinymce'
export default {
  components:{Tinymce},
  name: 'info.vue',
  created() {
    //获取数据用于下拉列表选择
    this.getListProducer();
    this.getListClassification();
  },
  data(){
    return{
      saveBthDisabled:false,
      videoInfo:{
        title:"",
        classificationId:"",
        classificationParentId:"",
        producerId:"",
        episodeNum:0,
        description:"",
        cover:"/static/videoCover.png",
        price:0
      },
      producerList:{},
      Lv1ClassList:{},
      Lv2ClassList:{},
      BASE_API:process.env.BASE_API,
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
    //点击一级分类后渲填充对应的二级分类
    renderLv2List(Lv1Id){
      for(let i=0; i<this.Lv1ClassList.length;i++){
        if(this.Lv1ClassList[i].id===Lv1Id){
          this.Lv2ClassList = this.Lv1ClassList[i].children;
          //下面这行是切换一级分类不出bug
          this.videoInfo.classificationId=""
        }
      }

    },

    //获取所有分类
    getListClassification(){
      classificationApi.getClassificationList()
        .then(response =>{
          this.Lv1ClassList = response.data.list;
        })
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

    },

    //上传成功
    handleCoverSuccess(res, file) {
      console.log(res)// 上传响应
      console.log(URL.createObjectURL(file.raw))// base64编码
      this.videoInfo.cover = res.data.url
    },

    //上传前
    beforeCoverUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传封面图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传封面图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }

  }
}
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
