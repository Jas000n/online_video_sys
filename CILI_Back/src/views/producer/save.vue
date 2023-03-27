<template>
  <div>

    <div class="app-container">

      <el-form label-width="120px">

        <el-form-item label="创作者名称">

          <el-input v-model="producer.name"/>

        </el-form-item>

        <el-form-item label="创作者排序">

          <el-input-number v-model="producer.sort" controls-position="right" :min="0"/>

        </el-form-item>

        <el-form-item label="创作者简介">

          <el-input v-model="producer.intro" :rows="10" type="textarea"/>

        </el-form-item>



        <!-- 创作者头像 -->
        <el-form-item label="创作者头像">
          <!-- 头衔缩略图 -->
          <pan-thumb :image="producer.avatar"/>
          <!-- 文件上传按钮 -->
          <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
          </el-button>

          <!--
      v-show：是否显示上传组件
      :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      :url：后台上传的url地址
      @close：关闭上传组件
      @crop-upload-success：上传成功后的回调 -->

          <image-cropper
            v-show="imagecropperShow"
            :width="300"
            :height="300"
            :key="imagecropperKey"
            :url="BASE_API+'/oss/fileoss'"
            field="file"
            @close="close"
            @crop-upload-success="cropSuccess"/>

        </el-form-item>

        <el-form-item>

          <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>

        </el-form-item>

      </el-form>

    </div>
  </div>


</template>
<script>

import producerApi from "@/api/producer.js"
//以下两个文件是上传头像的组件
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
  components:{ImageCropper,PanThumb},
  data(){
    return{
      producer:{
        name:"默认名字",
        sort:0,
        intro:"这个人很懒，没有留下简介",
        avatar:"https://jason-cili.oss-cn-beijing.aliyuncs.com/2023/03/16/15345aba7eae4ec3bb5ed69ae9e74710_file.png"
      },
      imagecropperShow:false,//是否显示组件
      imagecropperKey:0,//上传组件key值
      BASE_API:process.env.BASE_API,//获取dev.env.js里面的地址
    }
  },
  created() {
    //判断路径是否含有id值,这里的作用是新增创作者和修改创作者用的同一个页面
    //在渲染前需要检查一下路径里时候否存在id这个参数，是否需要根据路由里的参数回显创作者用户信息
    // if(this.$route.params && this.$route.params.id) {
    //   //从路径处获取id值
    //   const id = this.$route.params.id;
    //   //调用方法，根据id找到创作者
    //   this.getProducerByID(id);
    // }
    // // }else{
    // //   //请空表单，因为此时不是修改创作者，表单应该是空的，而直接把producer置空时不行的，created没有第二次执行
    // //   this.producer = {}
    // // }
    this.init()
  },
  //路由切换问题，当两个路由渲染同一个组件，组件不会被第二次created，导致生命周期的函数不会被执行第二次
  watch:{//监听，这里监听路由变化，每次路由发生变化就会执行
    $route(to,from){
        this.init()
    }
  },
  methods:{
    init(){
      //初始化方法，来判断是修改还是添加，决定是否将表单清空，还是根据参数id去回显信息
      if(this.$route.params && this.$route.params.id) {
        //从路径处获取id值
        const id = this.$route.params.id;
        //调用方法，根据id找到创作者
        this.getProducerByID(id);

      }else{
        //请空表单，因为此时不是修改创作者，表单应该是空的，而直接把producer置空时不行的，created没有第二次执行
        this.producer = {
          name:"默认名字",
          sort:0,
          intro:"这个人很懒，没有留下简介",
          avatar:"https://jason-cili.oss-cn-beijing.aliyuncs.com/2023/03/16/15345aba7eae4ec3bb5ed69ae9e74710_file.png"
        }
      }
    },

    //关闭上传弹框的办法
    close(){
      this.imagecropperShow = false;
      //上传组件初始化
      this.imagecropperKey = this.imagecropperKey+1
    },

    //上传图像成功后的方法
    cropSuccess(data){
      this.producer.avatar = data.url;
      this.imagecropperShow = false;
      //上传组件初始化
      this.imagecropperKey = this.imagecropperKey+1

    },
    //根据id获取创作者信息,赋值
    getProducerByID(id) {
      producerApi.getProducerByID(id)
        .then(result => {
          this.producer = result.data.Producer;
        })
    },


    //在点击按钮后，可以新增或修改创作者
    saveOrUpdate(){
      //判断是添加创作者还是修改创作者，方法是看有没有id
      if(this.producer.id){
        //修改
        this.updateProducer()
      }else{
        //添加创作者
        this.saveProducer()
      }
    },

    //添加创作者
    saveProducer(){
      producerApi.addProducer(this.producer)
        .then(response =>{//添加成功
          //提示信息
          this.$message({
            type:'success',
            message:"添加成功！"
          })
          //回到列表页面，路由跳转
          this.$router.push({path:"/producer/table"})
        })
    },

    //修改创作者
    updateProducer() {
      producerApi.updateProducer(this.producer)
        .then(result => {
          //提示信息
          this.$message({
            type:'success',
            message:"修改成功！"
          })
          //回到列表页面，路由跳转
          this.$router.push({path:"/producer/table"})

        })
    }

  }
}


</script>
