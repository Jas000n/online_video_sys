<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 影视详情 开始 -->
    <section class="container">


      <!-- 影视所属分类 开始 -->
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="/video" title class="c-999 fsize14">影视列表</a>
        \
        <span class="c-333 fsize14">{{ video.classificationParent }}</span>
        \
        <span class="c-333 fsize14">{{ video.classification }}</span>
      </section>
      <!-- /影视所属分类 结束 -->


      <!-- 影视基本信息 开始 -->
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section id="videoPlay" class="p-h-video-box">
            <img :src="video.cover" :alt="video.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ video.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff">价格：</span>
              <b class="c-yellow" style="font-size:24px;">￥{{ video.price }}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">创作者： {{ video.producerName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                    <em class="icon18 scIcon"/>
                    <a class="c-fff vam" title="收藏" href="#" >收藏</a>
                </span>
            </section>
            <section class="c-attr-mt">
              <a href="#" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol clearfix">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ video.buyCount }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">总集数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ video.episodeNum }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">观看数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{video.viewCount }}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"/>
      </div>
      <!-- /影视基本信息 结束 -->


      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">影视详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">

                <!-- 影视详情介绍 开始 -->
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>影视介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <!-- 将内容中的html翻译过来 -->
                      <p v-html="video.description">{{ video.description }}</p>
                    </section>
                  </div>
                </div>
                <!-- /影视详情介绍 结束 -->


                <!-- 影视大纲 开始-->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>影视大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 影视章节目录 -->
                          <li v-for="season in seasonList" :key="season.id" class="lh-menu-stair">
                            <a :title="season.title" href="javascript: void(0)" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"/>{{ season.title }}
                            </a>
                            <ol class="lh-menu-ol" style="display: block;">
                              <li v-for="episode in season.children" :key="episode.id" class="lh-menu-second ml30">
                                <a :href="'/aliPlayer/'+episode.videoSourceId"
                                   :title="episode.title"
                                   target="_blank">>
                                  <span v-if="episode.isFree === 1" class="fr">
                                        <i class="free-icon vam mr10">免费试看</i>
                                    </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ episode.title }}
                                </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /影视大纲 结束 -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <!-- 创作者 开始-->
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">创作者</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a :href="'/producer/'+video.producerId" target="_blank">
                        <img :src="video.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a :href="'/producer/'+video.producerId" class="c-333 fsize16 fl" target="_blank">{{ video.producerName }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ video.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
            <!-- /创作者 结束 -->
          </div>
        </aside>
        <div class="clear"/>
      </div>
    </section>
    <!-- /影视详情 结束 -->
  </div>
</template>
<script>
import videoAPi from "@/api/video.js"
export default {
  asyncData({ params, error }) {
    return videoAPi.getVideoInfo(params.id).then(response => {
      console.log(response.data.data.seasons[0].children);
      return {
        video: response.data.data.videoWebVO,
        seasonList: response.data.data.seasons
      }
    })
  },
  methods:{

  }
}
</script>
