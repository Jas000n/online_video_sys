<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <el-button type="text" @click="dialogSeasonFormVisible=true">添加影视季</el-button>
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
    <!-- 添加和修改影视季表单 -->
    <el-dialog :visible.sync="dialogSeasonFormVisible" title="添加季">
      <el-form :model="season" label-width="120px">
        <el-form-item label="本季标题">
          <el-input v-model="season.title"/>
        </el-form-item>
        <el-form-item label="本季排序">
          <el-input-number v-model="season.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSeasonFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
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
      videoID:"",
      dialogSeasonFormVisible:false,
      season:{
        title:"",
        sort:0
      },
    }
  },
  methods:{
    //添加season或修改season
    saveOrUpdate(){
      //设计videoid进来
      this.season.videoId = this.videoID;
      seasonApi.addSeason(this.season)
        .then(result => {
          //关闭弹窗
          this.dialogSeasonFormVisible=false;
          //提示成功
          this.$message({
            type:"success",
            message:"添加成功!"
          })
          //刷新页面
          this.getSeasonsAndEpisode();
        })
    },

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
