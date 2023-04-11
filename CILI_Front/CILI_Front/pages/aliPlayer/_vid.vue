<template>
  <div>
    <div>
      <!-- 阿里云视频播放器样式 -->
      <link
        rel="stylesheet"
        href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css"
      />
      <!-- 阿里云视频播放器脚本 -->
      <script
        charset="utf-8"
        type="text/javascript"
        src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js"
      />
      <!-- 定义播放器dom -->
      <div id="J_prismPlayer" class="prism-player" />
    </div>


    <!--    影视评论-->
        <div class="mt50 commentHtml">
          <div>
            <h6 class="c-c-content c-infor-title" id="i-art-comment">
              <span class="commentTitle">影视评论</span>
            </h6>
            <section class="lh-bj-list pr mt20 replyhtml">
              <ul>
                <li class="unBr">
                  <aside class="noter-pic">
                    <img
                      width="50"
                      height="50"
                      class="picImg"
                      src="@/assets/img/avatar-boy.gif"
                    />
                  </aside>
                  <div class="of">
                    <section class="n-reply-wrap">
                      <fieldset>
                        <textarea
                          name=""
                          v-model="comment.content"
                          placeholder="输入您要评论的文字"
                          id="commentContent"
                        ></textarea>
                      </fieldset>
                      <p class="of mt5 tar pl10 pr10">
                        <span class="fl"
                          ><tt
                            class="c-red commentContentmeg"
                            style="display: none"
                          ></tt
                        ></span>
                        <input
                          type="button"
                          @click="addComment()"
                          value="回复"
                          class="lh-reply-btn"
                        />
                      </p>
                    </section>
                  </div>
                </li>
              </ul>
            </section>
            <section class="">
              <section class="question-list lh-bj-list pr">
                <ul class="pr10">
                  <li v-for="(comment, index) in data.items" v-bind:key="index">
                    <aside class="noter-pic">
                      <img
                        width="50"
                        height="50"
                        class="picImg"
                        :src="comment.avatar"
                      />
                    </aside>
                    <div class="of">
                      <span class="fl">
                        <font class="fsize12 c-blue"> {{ comment.nickname }}</font>
                        <font class="fsize12 c-999 ml5">评论：</font></span
                      >
                    </div>
                    <div class="noter-txt mt5">
                      <p>{{ comment.content }}</p>
                    </div>
                    <div class="of mt5">
                      <span class="fr"
                        ><font class="fsize12 c-999 ml5">{{
                          comment.gmtCreate
                        }}</font></span
                      >
                    </div>
                  </li>
                </ul>
              </section>
            </section>
            <!-- 公共分页 开始 -->
            <div class="paging">
              <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
              <a
                :class="{ undisable: !data.hasPrevious }"
                href="#"
                title="首页"
                @click.prevent="gotoPage(1)"
                >首</a
              >
              <a
                :class="{ undisable: !data.hasPrevious }"
                href="#"
                title="前一页"
                @click.prevent="gotoPage(data.current - 1)"
                >&lt;</a
              >
              <a
                v-for="page in data.pages"
                :key="page"
                :class="{
                  current: data.current == page,
                  undisable: data.current == page,
                }"
                :title="'第' + page + '页'"
                href="#"
                @click.prevent="gotoPage(page)"
                >{{ page }}</a
              >
              <a
                :class="{ undisable: !data.hasNext }"
                href="#"
                title="后一页"
                @click.prevent="gotoPage(data.current + 1)"
                >&gt;</a
              >
              <a
                :class="{ undisable: !data.hasNext }"
                href="#"
                title="末页"
                @click.prevent="gotoPage(data.pages)"
                >末</a
              >
              <div class="clear" />
            </div>
            <!-- 公共分页 结束 -->
          </div>
        </div>

  </div>
</template>

<script>
import vodApi from "@/api/vod";
import commentApi from "@/api/comment";
export default {
  layout: "video", //应用video布局

  created() {
    this.vid = this.$route.params.vid
    this.initComment(this.vid,this.page,this.limit)

  },
  mounted() {
    this.vid = this.$route.params.vid
    this.initPlayer()




  },
  methods:{
    initPlayer(){
      vodApi.getVideoSourceID(this.vid)
        .then(response =>{
          console.log(response)
          this.videoSourceID = response.data.data.videoSourceID
          vodApi.getPlayURL(this.videoSourceID)
            .then(result =>{
              this.URL = result.data.data.URL
              console.log(this.URL)
              new Aliplayer(
                {
                  id: "J_prismPlayer",

                  source: this.URL,

                  width: "100%",
                  height: "500px",
                },
                function (player) {
                  console.log("播放器创建成功");
                }
              );
            })

        })
    },
    initComment(a,b,c){
      commentApi.getPageList(a,b,c).then(response => {
        console.log(response)
        this.data = response.data.data
      })
    }
  },
  data(){
    return{
      vid:"",
      videoSourceID:"",
      URL:"",
      data:{},
      page:1,
      limit:4,
      total:10,
      comment:{
        content:'',
        episodeId:''
      },
      isbuyCourse:false
    }
  }
};
</script>
