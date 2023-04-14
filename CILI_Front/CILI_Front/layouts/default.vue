<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="cilicili">
            <img src="@/assets/img/logo.png" width="100%" alt="cilicili">
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>
            <router-link to="/video" tag="li" active-class="current">
              <a>影视</a>
            </router-link>
            <router-link to="/producer" tag="li" active-class="current">
              <a>创作者</a>
            </router-link>
            <router-link to="/article" tag="li" active-class="current">
              <a>专栏</a>
            </router-link>
<!--            <router-link to="/qa" tag="li" active-class="current">-->
<!--              <a>问答</a>-->
<!--            </router-link>-->
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <li v-if="!loginInfo.id" id="no-login">
              <a href="/login" title="登录">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">登录</span>
              </a>
              |
              <a href="/register" title="注册">
                <span class="vam ml5">注册</span>
              </a>
            </li>
            <li v-if="loginInfo.id" id="is-login-one" class="mr10">
              <a id="headerMsgCountId" href="#" title="消息">
                <em class="icon18 news-icon">&nbsp;</em>
              </a>
              <q class="red-point" style="display: none">&nbsp;</q>
            </li>
            <li v-if="loginInfo.id" id="is-login-two" class="h-r-user">
              <a href="/ucenter" title>
                <img
                  :src="loginInfo.avatar"
                  width="30"
                  height="30"
                  class="vam picImg"
                  alt
                >
                <span id="userName" class="vam disIb">{{ loginInfo.nickname }}</span>
              </a>
              <a href="javascript:void(0);" title="退出" @click="logout()" class="ml5">退出</a>
            </li>
            <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>
          <aside class="h-r-search">
            <form action="#" method="post">
              <label class="h-r-s-box">
                <input type="text" placeholder="影视搜索" name="queryVideo.videoName" value>
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>
    <!-- /公共头引入 -->

    <nuxt/>

    <!-- 公共底引入 -->
    <footer id="footer">
      <section class="container">
        <div class>
          <h4 class="hLh30">
            <span class="fsize18 f-fM c-999">友情链接</span>
          </h4>
          <ul class="of flink-list">
            <li>
              <a href="https://www.bilibili.com/" title="比例比例" target="_blank">比例比例</a>
            </li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="b-foot">
          <section class="fl col-7">
            <section class="mr20">
              <section class="b-f-link">
                <a href="#" title="关于我们" target="_blank">关于我们</a>|
                <a href="#" title="联系我们" target="_blank">联系我们</a>|
                <a href="#" title="帮助中心" target="_blank">帮助中心</a>|
                <a href="#" title="资源下载" target="_blank">资源下载</a>|
                <span>服务热线：4008-517-517</span>
                <span>Email：ysyysy2001@126.com</span>
              </section>
              <section class="b-f-link mt10">
                <span>©麦当劳薯条,条条条</span>
              </section>
            </section>
          </section>
          <aside class="fl col-3 tac mt15">
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wx-icon.png" alt>
              </span>
            </section>
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wb-icon.png" alt>
              </span>
            </section>
          </aside>
          <div class="clear"></div>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>
<script>
import "~/assets/css/reset.css";
import "~/assets/css/theme.css";
import "~/assets/css/global.css";
import "~/assets/css/web.css";
import cookie from "js-cookie";
import loginApi from "@/api/login"
import '~/assets/css/base.css'
import '~/assets/css/activity_tab.css'
import '~/assets/css/bottom_rec.css'
import '~/assets/css/nice_select.css'
import '~/assets/css/order.css'
import '~/assets/css/swiper-3.3.1.min.css'
import "~/assets/css/pages-weixinpay.css"
export default {
  data(){
    return{
      token:"",
      loginInfo:{
        id:"",
        age:"",
        avatar:"",
        mobile:"",
        sex:"",
        nickname:""
      },

    }
  },
  created() {
    //获取路径里的token, 在用户用微信登陆时调用这个方法
    this.getTokenFromURL();
    if(this.token){//看路径里是否有token
      this.getWXUserInfo();

    }else{
      //右上角显示用户信息
      this.getUserInfo();
    }

  },
  methods:{
    //从cookie里取用户信息
    getUserInfo(){
      var UcenterStr = cookie.get("cili_ucenter");
      //把字符串转化为json对象
      if(UcenterStr){
        this.loginInfo = JSON.parse(UcenterStr)
      }

    },
    //退出登录
    logout(){
      cookie.set('cili_ucenter', "", { domain: 'localhost' })
      cookie.set('cili_token', "", { domain: 'localhost' })
      window.location.href = "/";
    },
    //获取路径里的token, 在用户用微信登陆时调用这个方法
    getTokenFromURL(){
      this.token=this.$route.query.token
    },
    //根据获得的wx token来查数据库
    getWXUserInfo(){
      cookie.set("cili_token",this.token,{domain:'localhost'})
      cookie.set("cili_ucenter","",{domain:'localhost'})

      //调接口查用户
      loginApi.getLoginInfo()
        .then(response =>{
          this.loginInfo = response.data.data.userInfo
          cookie.set("cili_ucenter",JSON.stringify(this.loginInfo),{domain:'localhost'})
        })
    }

  }
};
</script>
