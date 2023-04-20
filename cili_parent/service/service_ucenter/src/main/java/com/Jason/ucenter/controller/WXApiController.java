package com.Jason.ucenter.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.Jason.ucenter.client.StatisticClient;
import com.Jason.ucenter.entity.UcenterMember;
import com.Jason.ucenter.service.UcenterMemberService;
import com.Jason.ucenter.utils.ConstWXUtils;
import com.Jason.ucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WXApiController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    @Autowired
    private StatisticClient statisticClient;
    @GetMapping("login")
    public String genQrConnect(HttpSession session) {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";

        // 回调地址
        String redirectUrl = ConstWXUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new CiliException(20001, e.getMessage());
        }
        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "imhelen";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        System.out.println("state = " + state);
        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstWXUtils.WX_OPEN_APP_ID,
                redirectUrl,
                state);
        return "redirect:" + qrcodeUrl;
    }

    //获取扫马人的信息,写进数据库
    @GetMapping("callback")
    public String callback(String code, String state) throws Exception {
        //取code

        //用code和appsecret,id等去请求微信固定的地址,得到accesstoken和openid'
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String format = String.format(baseAccessTokenUrl, ConstWXUtils.WX_OPEN_APP_ID, ConstWXUtils.WX_OPEN_APP_SECRET, code);

        //使用httpclient发请求
        String accessTokenInfo = HttpClientUtils.get(format);
        System.out.println("accessTokenInfo"+accessTokenInfo);

        //从返回的json里拿出需要的key value
        Gson gson = new Gson();
        HashMap map = gson.fromJson(accessTokenInfo,HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");

        //拿着access_token和openid再去请求微信的服务器,拿到用户的微信数据
        String baseUserInfoUrl = "http://api.weixin.qq.com/sns/userinfo"+
                    "?access_token=%s"+
                    "&openid=%s";
        String formatBaseUserUrl = String.format(baseUserInfoUrl, accessToken, openid);
        String userInfo = HttpClientUtils.get(formatBaseUserUrl);
        System.out.println("userInfo"+userInfo);

        //从返回json里拿到用户信息
        HashMap userInfoMap = gson.fromJson(userInfo,HashMap.class);
        String nickname = (String) userInfoMap.get("nickname");
        String headimgurl = (String) userInfoMap.get("headimgurl");

        //把用户存进数据库
        //注意如果数据库里已有用户就登录,没有用户就注册登录
        UcenterMember ucenterMember = ucenterMemberService.getMemberByOpenId(openid);
        if(ucenterMember ==null){
            //没有注册,现在写数据库
            ucenterMember = new UcenterMember();
            ucenterMember.setOpenid(openid);
            ucenterMember.setNickname(nickname);
            ucenterMember.setAvatar(headimgurl);
            ucenterMemberService.save(ucenterMember);
            statisticClient.addCountRegister();
        }

        //使用jwt根据用户生成token,写进请求地址
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());


        statisticClient.addCountLogin();
        return "redirect:http://localhost:3000?token="+jwtToken;
    }
}