<template>
  <div class="app-container">

    <h2 style="text-align: center">发布新影视</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写影视基本信息" icon="el-icon-edit"></el-step>
      <el-step title="创建影视信息" icon="el-icon-upload2"></el-step>
      <el-step title="发布影视" icon="el-icon-upload"></el-step>

    </el-steps>

    <el-button type="text" @click="OpenSeasonDialog()">添加影视季</el-button>
<!--    展示季和集-->
    <ul class="seasonList" >
      <li v-for="season in SeasonsAndEpisodes" :key="season.id">
        <p>
          {{ season.title }}

          <span class="acts">
                <el-button type="text" @click="openEpisodeDialogue(season.id)">添加影视集</el-button>
                <el-button type="text" @click="openEdit(season.id)">编辑</el-button>
                <el-button type="text" @click="removeSeason(season.id,season.title)">删除</el-button>
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
    <!-- 添加和修改episode表单 -->
    <el-dialog :visible.sync="dialogEpisodeFormVisible" title="添加集">
      <el-form :model="episode" label-width="120px">
        <el-form-item label="单集标题">
          <el-input v-model="episode.title"/>
        </el-form-item>
        <el-form-item label="单集排序">
          <el-input-number v-model="episode.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="episode.free">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEpisodeFormVisible = false">取 消</el-button>
        <el-button :disabled="saveEpisodeBtnDisabled" type="primary" @click="saveOrUpdateEpisode">确 定</el-button>
      </div>
    </el-dialog>


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
import episodeApi from "@/api/episode"
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
      dialogEpisodeFormVisible:false,
      episode:{
        title:'',
        sort:'',
        free:0,
        videoSourceId:'',
      },
      season:{
        title:"",
        sort:0
      },
    }
  },
  methods:{
    //删除season
    removeSeason(seasonId,seasonTitle){
      this.$confirm('此操作将删除影视季:'+seasonTitle+', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        seasonApi.deleteSeason(seasonId)
          .then(response =>{
            //删除成功
            this.getSeasonsAndEpisode();
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          })
          .catch(error =>{
            this.$message({
              type: 'error',
              message: '删除失败!'
            });
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    //修改season
    openEdit(seasonId){
      console.log(123);
      seasonApi.getSeasonById(seasonId)
        .then(result => {
          this.season = result.data.season
          this.dialogSeasonFormVisible = true;
        })
    },
    //点击添加的方法
    OpenSeasonDialog(){
      //打开弹窗
      this.dialogSeasonFormVisible=true;
      //将表单数据清空
      this.season.title =""
      this.season.sort=0;
    },
    //添加season
    addSeason(){
      //设置videoid进来
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
    //修改season
    updateSeason(){
      seasonApi.updateSeason(this.season)
        .then(result => {
          //关闭弹窗
          this.dialogSeasonFormVisible=false;
          //提示成功
          this.$message({
            type:"success",
            message:"修改成功!"
          })
          //刷新页面
          this.getSeasonsAndEpisode();
        })
    },
    //添加season或修改season
    saveOrUpdate(){
      //有id说明是根据id回显的数据,这时候修改
      if(this.season.id){
        this.updateSeason()
      }else{
        //没有id的时候,属于添加,mybatisPlus写数据库的时候会生成id
        this.addSeason()
      }

    },

    //根据影视id获得季和集
    getSeasonsAndEpisode() {
      seasonApi.getSeasonsAndEpisodes(this.videoID)
        .then(result => {
          // console.log(result)
          this.SeasonsAndEpisodes = result.data.seasons;
        })
    },
    //跳到前一个页面
    previous(){
       // console.log("previous")
      this.$router.push({path:"/video/info/"+this.videoID})
    },
    next(){
      //跳转到第二个步骤
      this.$router.push({path:'/video/publish/'+this.videoID})
    },
    //以下所有函数是为episode服务的*******************************************
    //打开集信息编辑弹窗
    openEpisodeDialogue(seasonId){
      this.dialogEpisodeFormVisible = true;
      this.episode.seasonId = seasonId;
    },
    //添加或修改episode
    addEpisode(){
      console.log(456)
      this.episode.videoId = this.videoID;
      episodeApi.addEpisode(this.episode)
        .then(result => {
          //关闭弹窗
          this.dialogEpisodeFormVisible=false;
          //提示成功
          this.$message({
            type:"success",
            message:"添加成功!"
          })
          //刷新页面
          this.getSeasonsAndEpisode();
        })
    },
    //添加或修改episode
    saveOrUpdateEpisode(){
      console.log(123)
      this.addEpisode()
    },

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
