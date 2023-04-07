<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 创作者列表 开始 -->
    <section class="container">
      <header class="comm-title all-teacher-title">
        <h2 class="fl tac">
          <span class="c-333">全部创作者</span>
        </h2>
        <section class="c-tab-title">

          <a id="subjectAll" title="全部" href="#">全部</a>

        </section>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total ===0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，数据神秘失踪了...</span>
          </section>
          <!-- 无数据提示 结束-->
          <article class="i-teacher-list">
            <ul class="of">
              <li v-for="producer in data.items" :key="producer.id">
                <section class="i-teach-wrap">
                  <div class="i-teach-pic">
                    <a :href="'/producer/'+producer.id" :title="producer.name" target="_blank">
                      <img :src="producer.avatar" :alt="producer.name">
                    </a>
                  </div>
                  <div class="mt10 hLh30 txtOf tac">
                    <a :href="'/producer/'+producer.id" :title="producer.name" target="_blank" class="fsize18 c-666">{{producer.name}}</a>
                  </div>
                  <div class="hLh30 txtOf tac">
                    <span class="fsize14 c-999">{{ producer.intro }}</span>
                  </div>
                </section>
              </li>
            </ul>
          </article>
        </div>

        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a

              :class="{undisable: !data.hasPrevious}"
              href="#"
              title="首页"
              @click.prevent="gotoPage(1)">首</a>
            <a
              v-if="data.hasPrevious"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a
              v-for="page in data.pages"
              :key="page"
              :class="{current: data.current == page, undisable: data.current == page}"
              :title="'第'+page+'页'"
              href="#"
              @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
              v-if="data.hasNext"
              href="#"
              title="后一页"
              @click.prevent="gotoPage(data.current+1)">&gt;</a>
            <a
              :class="{undisable: !data.hasNext}"
              href="#"
              title="末页"
              @click.prevent="gotoPage(data.pages)">末</a>
            <div class="clear"/>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!--创作者列表 结束 -->
  </div>
</template>
<script>

import producerAPi from "@/api/producer.js";


export default {
  computed: {
    producer() {
      return producer
    }
  },
  //异步调用,只会执行一次
  //params 相当于this.$route.params
  asyncData({params,error}){
    return producerAPi.getProducerFrontList(1,8)
      .then(response =>{
        return {
          data:response.data.data
        }

      })
  },
  methods:{
    //切换分页
    gotoPage(page){
      producerAPi.getProducerFrontList(page,8)
        .then(response =>{
          this.data = response.data.data;
        })
    }

  }

};
</script>
