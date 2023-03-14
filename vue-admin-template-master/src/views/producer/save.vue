<template>
  <div>

    <div class="app-container">

      <el-form label-width="120px">

        <el-form-item label="创作者名称">

          <el-input v-model="producer.name"/>

        </el-form-item>

        <el-form-item label="创作者排序">

          <el-input-number v-model="producer.sort" controls-position="right" min="0"/>

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
  },
  methods:{
    //在点击按钮后，可以新增或修改创作者
    saveOrUpdate(){
      //添加创作者
      this.saveProducer()
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
    }

  }
}


</script>
