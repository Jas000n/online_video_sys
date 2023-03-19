<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBthDisabled" type="primary" @click="next">保存&下一步</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>


<script>
export default {
  name: 'publish.vue',
  created() {
  },
  data(){
    return{
      saveBthDisabled:false,
    }
  },
  methods:{
    previous(){
      this.$router.push({path:"/video/season/1"})
    },
    next(){
      //跳转到第二个步骤
      this.$router.push({path:'/video/publish/1'})
    }
  }
}
</script>

<style scoped>

</style>
