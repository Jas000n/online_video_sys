<template>

  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" href="/">
            <img src="~/assets/photo/banner/1525939573202.jpg" alt="首页banner">
          </a>
        </div>
        <div class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" href="/">
            <img src="~/assets/photo/banner/1525939573203.jpg" alt="首页banner">
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
                <li>
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        src="~/assets/photo/video/1442295379715.jpg"
                        class="img-responsive"
                        alt="猫和老鼠"
                      >
                      <div class="cc-mask">
                        <a href="#" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" title="猫和老鼠" class="course-title fsize18 c-333">猫和老鼠</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">34人学习</i>
                        |
                        <i class="c-999 f-fA">34评论</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部影视" class="comm-btn c-btn-2">全部影视</a>
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
                <li>
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a href="/producer/1" title="周润发">
                        <img alt="周润发" src="~/assets/photo/producer/1442298121626.jpg">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a href="/teacher/1" title="周润发" class="fsize18 c-666">梁朝伟</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">梁朝伟，华语电影圈著名演员，代表作品包括《春光乍泄》、《无间道》和《花样年华》等，曾多次获得电影界最高奖项肯定。</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p
                        class="c-999 f-fA"
                      >梁朝伟是华语电影圈备受尊敬和崇拜的演员之一，他以其细腻、深刻和精湛的表演技巧赢得了广泛的赞誉和认可。他不断尝试不同类型的角色和电影风格，始终保持着独特的风格和艺术追求，他的代表作品《春光乍泄》、《无间道》和《花样年华》等也成为了华语电影经典之作。他不仅在华语电影圈中广受欢迎，在国际电影节上也屡获殊荣，是一位备受推崇的演员和电影人。</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部创作者" class="comm-btn c-btn-2">全部创作者</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
2
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
        }
      }
    }
  }
}
</script>
