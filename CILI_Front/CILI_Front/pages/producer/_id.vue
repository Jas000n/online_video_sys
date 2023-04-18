<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 创作者介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">创作者介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- 创作者基本信息 -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="producer.avatar">
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">{{ producer.name }}</span>
            </h3>
            <section class="t-infor-txt">
              <p
                class="mt20"
              >{{ producer.intro }}</p>
            </section>
            <div class="clear"></div>
          </div>
        </section>
        <div class="clear"></div>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac">
              <span class="c-333">创作影视</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="videoList.length===0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">数据走丢了</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul class="of">
              <li v-for="video in videoList" :key="video.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="video.cover" class="img-responsive" >
                    <div class="cc-mask">
                      <a title="开始观看" target="_blank" class="comm-btn c-btn-1" @click="jump(video.id)">开始观看</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a  @click="jump(video.id)" :title="video.title" target="_blank" class="course-title fsize18 c-333">{{ video.title }}</a>
                  </h3>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>

        </div>
      </section>

    </section>

    <!-- /讲师介绍 结束 -->
  </div>

</template>

<script>
import producerApi from "@/api/producer.js"
export default {
  asyncData({params,error}){
    var id = params.id;
    return producerApi.getProducerAndHisVideos(id)
      .then(response =>{
        return {
          producer:response.data.data.producer,
          videoList:response.data.data.videoList
        }
      })

  },
  methods:{
    jump(id){
    this.$router.push("/video/"+id)
    }
  }
};

</script>
