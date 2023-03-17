<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="信息描述">
        <el-tag type="info">excel模版说明</el-tag>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="'/static/test.xlsx'">点击下载模版</a>
        </el-tag>
      </el-form-item>
      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API+'/service/classification/addClassification'"
          name="multipartFile"
          accept="application/vnd.ms-excel">
          <el-button slot="trigger" size="small" type="primary" >选取文件</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload">上传到服务器</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'save.vue',
  data(){
    return{
      BASE_API:process.env.BASE_API,
      importBtnDisabled:false,
      loading:false,
      permissionToUpload:false
    }
  },
  created() {
  },
  methods:{
    //点击按钮上传文件到接口里面
    submitUpload(){
      this.importBtnDisabled = true;
      this.loading = true;
      //js:document.getElementById("upload").submit()
      //而下面这种写法可以直接找到dom，用$去取值
      this.$refs.upload.submit();
      // this.permissionToUpload = true;
    },
    //上传成功
    fileUploadSuccess(){
      // if(this.permissionToUpload){
        this.loading = false;
        this.$message({
          type:'success',
          message:'添加影视分类成功！'
        })
        //跳转页面
        this.$router.push({path:'/classification/list'})
      // }else{
      //   this.$message({
      //     type:"error",
      //     message:'请先选择文件！'
      //     }
      //   )
      // }

    },
    //上传失败
    fileUploadError(){
      this.loading = false;
      this.$message({
        type:'error',
        message:'添加影视分类失败！'
      })
    },
  }
}
</script>

<style scoped>

</style>
