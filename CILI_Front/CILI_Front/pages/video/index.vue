<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /影视列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部影视</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">影视类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="#" @click="reset()">全部</a>
                </li>
                <li v-for="(item,index) in classificationNestedList" :key="index" :class="{active:oneIndex===index}">
                  <a :title="item.name" href="#" @click="searchLv2Class(item.id,index)">{{item.title}}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(lv2Item,index) in subClassificationList" :key="index" :class="{active:twoIndex===index}">
                  <a :title="lv2Item.title" href="#" @click="clickLv2Class(lv2Item.id,index)">{{ lv2Item.title }}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">销量
                  <span :class="{hide:buyCountSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate()">最新
                  <span :class="{hide:gmtCreateSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice()">价格&nbsp;
                  <span :class="{hide:priceSort==''}">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total===0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article v-if="data.total>0" class="comm-course-list">
            <ul class="of" id="bna">
              <li v-for="item in data.items" :key="item.id">
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
                      <i class="c-999 f-fA">0评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>

        </div>

        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <div>当前第{{data.current}}页,共{{data.pages}}页</div>
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
<!--            <a-->
<!--              v-for="page in data.pages"-->
<!--              :key="page"-->
<!--              :class="{current: data.current == page, undisable: data.current == page}"-->
<!--              :title="'第'+page+'页'"-->
<!--              href="#"-->
<!--              @click.prevent="gotoPage(page)">{{ page }}</a>-->
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
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import videoApi from "@/api/video.js"
export default {
  data(){
    return{
      page:1,
      data:[],//影视列表
      classificationNestedList: [], // 一级分类列表
      subClassificationList: [], // 二级分类列表
      searchObj: {}, // 查询表单对象
      oneIndex:-1,
      twoIndex:-1,
      buyCountSort:"",
      gmtCreateSort:"",
      priceSort:""
    }
  },
  created() {
    this.initVideoFirst();
    this.initClassification()
  },
  methods:{
    //查询第一页数据
    initVideoFirst(){
      videoApi.getVideoFrontList(1,8,this.searchObj)
        .then(response =>{
          this.data = response.data.data;
          console.log(this.data)
        })
    },

    //查询所有分类,用于显示
    initClassification(){
      videoApi.getAllClassification()
        .then(response =>{
          this.classificationNestedList = response.data.data.list
        })
    },

    //分页切换方法
    gotoPage(page){
      videoApi.getVideoFrontList(page,8,this.searchObj)
        .then(response =>{
          this.data = response.data.data;
        })
    },
    //点击一级分类,展开二级分类
    searchLv2Class(id,index){
      //点击影视分类后,给searchObj赋值
      this.searchObj.classificationParentId = id
      //更新页面
      this.gotoPage(1)
      //给index赋值,让样式生效
      this.oneIndex = index
      this.searchObj.classificationId =""
      this.subClassificationList=[]

      for(let i=0;i<this.classificationNestedList.length;i++){
        var Lv1Class = this.classificationNestedList[i]
        if(Lv1Class.id === id){
          this.subClassificationList = Lv1Class.children
        }
      }
    },
    //点击二级分类
    clickLv2Class(id,index){
      this.twoIndex = index;
      this.searchObj.classificationId = id
      this.gotoPage(1)
    },
    //清空所有检索条件
    reset(){
      this.searchObj={}
      this.oneIndex=-1
      this.twoIndex=-1
      this.buyCountSort=""
      this.gmtCreateSort=""
      this.priceSort=""
      this.subClassificationList=[]
      this.gotoPage(1)
    },
    //根据销量排序
    searchBuyCount(){
      this.buyCountSort ="1";
      this.gmtCreateSort=""
      this.priceSort=""
      //赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.priceSort = this.priceSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort

      //刷新数据
      this.gotoPage(1)
    },
    //根据创建时间排序
    searchGmtCreate(){
      this.buyCountSort ="";
      this.gmtCreateSort="1"
      this.priceSort=""
      //赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.priceSort = this.priceSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort

      //刷新数据
      this.gotoPage(1)
    },
    //根据价格排序
    searchPrice(){
      this.buyCountSort ="";
      this.gmtCreateSort=""
      this.priceSort="1"
      //赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.priceSort = this.priceSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort

      //刷新数据
      this.gotoPage(1)
    }

  }
}
</script>
<style scoped>
.active{
  background: #bdbdbd;
}
.hide{
  display: none;
}
.show{
  display:block;
}

</style>
