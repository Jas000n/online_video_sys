<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <el-button type="text">添加季和集</el-button>
<!--    展示季和集-->
    <ul class="seasonList" >
      <li v-for="season in SeasonsAndEpisodes" :key="season.id">
        <p>
          {{ season.title }}

          <span class="acts">
                <el-button type="text">添加影视季</el-button>
                <el-button style="" type="text">编辑</el-button>
                <el-button type="text">删除</el-button>
            </span>
        </p>
        <ul class="seasonList episodeList">
          <li v-for="episode in season.children" :key="episode.key">
            <p>{{ episode.title }}
              <span class="acts">
                        <el-button type="text">编辑</el-button>
                        <el-button type="text">删除</el-button>
              </span>
            </p>
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
      this.$router.push({path:"/video/info/"+this.videoID})
    },
    next(){
      //跳转到第二个步骤
      this.$router.push({path:'/video/publish/'+this.videoID})
    }
  }
}
</script>

<style scoped>
.seasonList{
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.seasonList li{
  position: relative;
}
.seasonList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.seasonList .acts {

  float: right;
  font-size: 14px;
}


.episodeList{

  padding-left: 50px;

}

.episodeList p{

  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>
