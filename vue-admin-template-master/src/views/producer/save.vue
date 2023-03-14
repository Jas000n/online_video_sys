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


        <!-- 创作者头像：TODO -->


        <el-form-item>

          <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>

        </el-form-item>

      </el-form>

    </div>
  </div>


</template>
<script>

import producerApi from "@/api/producer.js"
export default {
  data(){
    return{
      producer:{
        name:"默认名字",
        sort:0,
        intro:"这个人很懒，没有留下简介",
        avatar:""
      }
    }
  },
  created() {
    //判断路径是否含有id值,这里的作用是新增创作者和修改创作者用的同一个页面
    //在渲染前需要检查一下路径里时候否存在id这个参数，是否需要根据路由里的参数回显创作者用户信息
    if(this.$route.params && this.$route.params.id){
      //从路径处获取id值
      const id = this.$route.params.id;
      //调用方法，根据id找到创作者
      this.getProducerByID(id);
    }
  },
  methods:{
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
