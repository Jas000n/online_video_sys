<template>
  <div>
    <ul class="of" id="bna">
      <li v-for="item in items" :key="item.id">
        <div class="cc-l-wrap">
          <section class="course-img">
            <img :src="item.cover" class="img-responsive" :alt="item.title">
            <div class="cc-mask">
              <a :href="'/video/'+item.id" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
            </div>
          </section>
          <h3 class="hLh30 txtOf mt10">
            <a :href="'/video/'+item.id" :title="item.title" class="course-title fsize18 c-333">{{item.title}}</a>
          </h3>
          <section class="mt10 hLh20 of">
                    <span v-if="Number(item.price)===0" class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
            <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ item.viewCount }}人观看</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
          </section>
        </div>
      </li>
    </ul>

  </div>
</template>

<script>
import cookie from "js-cookie";
import recommendationApi from "../../api/recommendation";

export default {
  name: "index.vue",
  data() {
    return {
      items:[]
    };
  },
  mounted() {
    //如果没有登录,跳转到登录
    if(!cookie.get('cili_token')){
      this.$router.push("/login")
      this.$message({
        type: 'warning',
        message: "请先登录"
      })
    }
    //获取推荐影视
    recommendationApi.recKRecommendation(3)
      .then(response =>{
        console.log(response)
        this.items = response.data.data.item
      })
  },
};
</script>

<style scoped></style>
