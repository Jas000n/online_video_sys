<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>


    <ul>
      <li v-for="season in SeasonsAndEpisodes" :key="season.id">s
        {{season.title}}
        <ul>
          <li v-for="episode in season.children" :key="episode.key">
            {{episode.title}}
          </li>
        </ul>
      </li>
    </ul>
    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBthDisabled" type="primary" @click="next">保存&下一步</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>


<script>
import seasonApi from "@/api/season"
export default {

  name: 'season.vue',
  created() {
    //获取路由里的id值
    if(this.$route.params && this.$route.params.id){
      this.videoID = this.$route.params.id;
    }
    //调用方法,获得影视的所有季和集
    this.getSeasonsAndEpisode(this.videoID)

  },
  data(){
    return{
      saveBthDisabled:false,
      SeasonsAndEpisodes:{},
      videoID:""
    }
  },
  methods:{
    //根据影视id获得季和集
    getSeasonsAndEpisode() {
      seasonApi.getSeasonsAndEpisodes(this.videoID)
        .then(result => {
          console.log(result)
          this.SeasonsAndEpisodes = result.data.seasons;
        })
    },
    //跳到前一个页面
    previous(){
       console.log("previous")
      this.$router.push({path:"/video/info/1"})
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
