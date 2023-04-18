<template>

  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div v-for="banner in bannerList" :key="banner.id" class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" :href="banner.linkUrl">
            <img width="100%"
                 :src="banner.imageUrl"
                 :alt="banner.title"/>
          </a>
        </div>

      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 影视综合 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门影视</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="(video, index) in videoList" v-bind:key="index">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="video.cover"
                        class="img-responsive"
                        :alt="video.title">
                      <div class="cc-mask">
                        <a :href="'/video/'+video.id" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" :title="video.title" class="course-title fsize18 c-333">{{video.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green" v-if="Number(video.price) === 0">
                    <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                      <span class="fr jgTag bg-green" v-else>
                    <i class="c-fff fsize12 f-fA"> ￥{{video.price}}</i>
                    </span>
                      <span class="fl jgAttr c-ccc f-fA">
                    <i class="c-999 f-fA">{{video.buyCount}} 人学习</i>
                                            |
                    <i class="c-999 f-fA">{{video.viewCount}} 人浏览</i>
                    </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/video" title="全部影视" class="comm-btn c-btn-2">全部影视</a>
            </section>
          </div>
        </section>
      </div>
      <!-- 影视 结束 -->
      <!-- 创作者 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">知名创作者</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="(producer,index) in producerList" v-bind:key="index">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :href='"/producer/"+producer.id' :title="producer.name">
                        <img :alt="producer.name" :src="producer.avatar">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href='"/producer/"+producer.id' :title="producer.name" class="fsize18 c-666">{{producer.name}}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{producer.intro}}</span>
                    </div>
<!--                    <div class="mt15 i-q-txt">-->
<!--                      <p-->
<!--                        class="c-999 f-fA"-->
<!--                      >{{teacher.career}}</p>-->
<!--                    </div>-->
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/producer" title="全部创作者" class="comm-btn c-btn-2">全部创作者</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import bannerApi from "@/api/banner"
import indexApi from "@/api/index"
export default {
  data () {
    return {
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        },
      },
      //banners
      bannerList:[],
      //8个热门影视和4个随机创作者
      videoList:[],
      producerList:[]
    }
  },
  created() {
    this.getBannerList();
    this.getVideoListAndProducerList()

  },
  methods:{
    //获得所有轮播图
    getBannerList(){
      bannerApi.getBanners()
        .then(response =>{
          this.bannerList = response.data.data.list
          // console.log( response.data.data.list)
        })
    },
    //查询8格热门影视和4个创作者
    getVideoListAndProducerList(){
      indexApi.getIndexVideosAndProducers()
        .then(response =>{
          this.videoList = response.data.data.videos
          this.producerList = response.data.data.producers
          // console.log(response)
        })
    }
  }
}
</script>
